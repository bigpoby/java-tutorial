
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * TXT 파일에서 특정 단어를 검색하고 결과를 저장하는 프로그램 (리팩토링된 버전)
 */
public class TxtWordSearcher {

    /**
     * 시스템 환경에 따른 적절한 문자 인코딩을 반환
     *
     * @return Windows: MS949, 기타: UTF-8
     */
    private static String getSystemEncoding() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            return "MS949";
        } else {
            return "UTF-8";
        }
    }

    public static void main(String[] args) {
        String encoding = getSystemEncoding();
        Scanner scanner = new Scanner(System.in, encoding);
        System.out.println("시스템 환경: " + System.getProperty("os.name"));
        System.out.println("사용 인코딩: " + encoding);
        PerformanceMonitor.SearchStats stats;

        try {
            ResultDisplayer.displayHeader("TXT 파일 단어 검색 프로그램 v2.0");

            // 1. 디렉토리 입력 및 검증
            System.out.print("검색할 디렉토리 경로를 입력하세요: ");
            String directoryPath = scanner.nextLine();

            File directory;
            try {
                directory = FileManager.validateDirectory(directoryPath);
            } catch (IllegalArgumentException e) {
                ResultDisplayer.displayError(e.getMessage());
                return;
            }

            // 2. 재귀 검색 여부 확인
            System.out.print("하위 디렉토리까지 검색하시겠습니까? (y/n): ");
            String searchSubdirs = scanner.nextLine().toLowerCase();
            boolean recursive = searchSubdirs.equals("y") || searchSubdirs.equals("yes");

            // 3. 검색할 단어 입력
            System.out.print("\n검색할 단어를 입력하세요: ");
            String searchWord = scanner.nextLine().trim();

            if (searchWord.isEmpty()) {
                ResultDisplayer.displayError("검색어를 입력해주세요.");
                return;
            }

            // 4. 성능 측정 시작
            stats = new PerformanceMonitor.SearchStats();

            // 5. 파일 검색
            stats.startFileSearch();
            List<File> txtFiles = recursive
                    ? FileManager.findTxtFilesRecursive(directory)
                    : FileManager.findTxtFiles(directory);
            stats.endFileSearch(txtFiles.size());

            if (txtFiles.isEmpty()) {
                System.out.println("\n검색할 txt 파일이 없습니다.");
                return;
            }

            // 6. 단어 검색 실행
            System.out.println("\n검색 중...");
            stats.startWordSearch();
            List<SearchEngine.SearchResult> searchResults
                    = SearchEngine.searchInFiles(txtFiles, searchWord);

            // 처리된 라인 수 계산
            int totalLines = calculateTotalLines(txtFiles);
            stats.endWordSearch(totalLines);

            // 7. 검색 결과 화면 출력
            ResultDisplayer.displaySearchResults(searchResults, searchWord, stats);

            // 8. 검색 결과 자동 저장
            if (!searchResults.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String timestamp = LocalDateTime.now().format(formatter);
                String outputFileName = "output/" + searchWord + "_" + timestamp + ".txt";

                stats.endTotal();
                boolean saveSuccess = ResultSaver.saveSearchResults(
                        searchResults, outputFileName, searchWord, stats);

                if (saveSuccess) {
                    ResultDisplayer.displaySaveSuccess(outputFileName, searchResults.size());
                } else {
                    ResultDisplayer.displayError("파일 저장에 실패했습니다.");
                }
            } else {
                System.out.println("\n검색 결과가 없어 저장할 내용이 없습니다.");
            }

            // 9. 성능 통계 출력 (선택사항)
            System.out.print("\n성능 통계를 표시하시겠습니까? (y/n): ");
            String showStats = scanner.nextLine().toLowerCase();
            if (showStats.equals("y") || showStats.equals("yes")) {
                if (!searchResults.isEmpty()) {
                    stats.endTotal();
                }
                ResultDisplayer.displayPerformanceStats(stats);
            }

        } catch (Exception e) {
            ResultDisplayer.displayError("오류가 발생했습니다: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * 파일들의 총 라인 수를 계산하는 헬퍼 메소드
     *
     * @param files 파일 목록
     * @return 총 라인 수
     */
    private static int calculateTotalLines(List<File> files) {
        String encoding = getSystemEncoding();

        return files.stream()
                .mapToInt(file -> {
                    try (java.io.BufferedReader reader = new java.io.BufferedReader(
                            new java.io.FileReader(file, java.nio.charset.Charset.forName(encoding)))) {
                        return (int) reader.lines().count();
                    } catch (java.io.IOException e) {
                        // 라인 수 계산 실패 시 0 반환
                        return 0;
                    }
                })
                .sum();
    }
}
