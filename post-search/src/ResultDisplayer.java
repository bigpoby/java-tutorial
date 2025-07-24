
import java.io.File;
import java.util.List;

/**
 * 검색 결과를 출력하는 클래스 (개선된 버전)
 */
public class ResultDisplayer {

    /**
     * 헤더 정보를 출력하는 메소드
     *
     * @param title 프로그램 제목
     */
    public static void displayHeader(String title) {
        System.out.println("=== " + title + " ===");
    }

    /**
     * 오류 메시지를 출력하는 메소드
     *
     * @param errorMessage 오류 메시지
     */
    public static void displayError(String errorMessage) {
        System.out.println("오류: " + errorMessage);
    }

    /**
     * 파일 목록을 출력하는 메소드 (개선된 버전)
     *
     * @param directoryPath 검색한 디렉토리 경로
     * @param files 파일 목록
     * @param recursive 재귀 검색 여부
     */
    public static void displayFileList(String directoryPath, List<File> files, boolean recursive) {
        System.out.println("\n=== 파일 검색 결과 ===");
        System.out.println("검색 디렉토리: " + directoryPath);
        System.out.println("검색 방식: " + (recursive ? "재귀 검색 (하위 디렉토리 포함)" : "현재 디렉토리만"));
        System.out.println("발견된 txt 파일 개수: " + files.size());
        System.out.println();

        if (files.isEmpty()) {
            System.out.println("txt 파일을 찾을 수 없습니다.");
        } else {
            System.out.println("txt 파일 목록:");
            for (int i = 0; i < files.size(); i++) {
                FileManager.FileInfo fileInfo = FileManager.getFileInfo(files.get(i));
                if (recursive) {
                    System.out.printf("%d. %s%n", i + 1, fileInfo.getAbsolutePath());
                    System.out.printf("   크기: %d bytes, 수정일: %s%n",
                            fileInfo.getSize(), fileInfo.getLastModified());
                } else {
                    System.out.printf("%d. %s (크기: %d bytes, 수정일: %s)%n",
                            i + 1, fileInfo.getName(), fileInfo.getSize(), fileInfo.getLastModified());
                }
                if (recursive && i < files.size() - 1) {
                    System.out.println();
                }
            }
        }
    }

    // 상수 정의
    private static final int SAMPLING_THRESHOLD = 100;
    private static final int SAMPLING_RATE = 100;
    private static final int MAX_DISPLAY_ITEMS = 50;

    /**
     * 단어 검색 결과를 화면에 출력하는 메소드 (리팩토링된 버전)
     *
     * @param results 검색 결과 목록
     * @param searchWord 검색한 단어
     * @param searchStats 검색 통계 정보
     */
    public static void displaySearchResults(List<SearchEngine.SearchResult> results,
            String searchWord, PerformanceMonitor.SearchStats searchStats) {
        displaySearchHeader(searchWord, results.size(), searchStats);

        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return;
        }

        displaySamplingNotice(results.size());
        displayResultItems(results);
    }

    /**
     * 검색 결과 헤더 정보 출력
     */
    private static void displaySearchHeader(String searchWord, int resultCount,
            PerformanceMonitor.SearchStats searchStats) {
        System.out.println("\n=== 단어 검색 결과 ===");
        System.out.println("검색어: " + searchWord);
        System.out.println("총 발견된 항목: " + resultCount);

        if (searchStats != null) {
            System.out.printf("검색 시간: %.2f ms%n", searchStats.getSearchTimeMs());
            System.out.printf("검색된 파일 수: %d개%n", searchStats.getFilesSearched());
        }

        System.out.println();
    }

    /**
     * 샘플링 안내 메시지 출력
     */
    private static void displaySamplingNotice(int resultCount) {
        if (resultCount > SAMPLING_THRESHOLD) {
            System.out.println("※ 결과가 많아 " + SAMPLING_RATE + "개 중 1개씩 샘플링하여 표시합니다.");
            System.out.println("※ 전체 결과는 저장된 파일에서 확인하세요.");
            System.out.println();
        }
    }

    /**
     * 검색 결과 항목들 출력
     */
    private static void displayResultItems(List<SearchEngine.SearchResult> results) {
        String currentFileName = "";
        int displayCount = 0;
        int samplingRate = getSamplingRate(results.size());

        for (int i = 0; i < results.size(); i += samplingRate) {
            SearchEngine.SearchResult result = results.get(i);

            // 파일 헤더 출력 (파일이 바뀔 때)
            if (shouldDisplayFileHeader(result.getFileName(), currentFileName)) {
                displayFileHeader(result.getFileName(), displayCount);
                currentFileName = result.getFileName();
            }

            // 결과 항목 출력
            displayResultItem(result);
            displayCount++;

            // 최대 출력 개수 제한
            if (hasReachedMaxDisplayLimit(displayCount)) {
                displayMoreResultsNotice();
                break;
            }
        }
    }

    /**
     * 샘플링 비율 계산
     */
    private static int getSamplingRate(int resultCount) {
        return resultCount > SAMPLING_THRESHOLD ? SAMPLING_RATE : 1;
    }

    /**
     * 파일 헤더 출력 여부 확인
     */
    private static boolean shouldDisplayFileHeader(String newFileName, String currentFileName) {
        return !currentFileName.equals(newFileName);
    }

    /**
     * 파일 헤더 출력
     */
    private static void displayFileHeader(String fileName, int displayCount) {
        if (displayCount > 0) {
            System.out.println();
        }
        System.out.println("파일: " + fileName);
        System.out.println("----------------------------------------");
    }

    /**
     * 개별 검색 결과 항목 출력
     */
    private static void displayResultItem(SearchEngine.SearchResult result) {
        System.out.printf("행 %d: %s%n", result.getLineNumber(), result.getLineContent());
    }

    /**
     * 최대 출력 개수 도달 여부 확인
     */
    private static boolean hasReachedMaxDisplayLimit(int displayCount) {
        return displayCount >= MAX_DISPLAY_ITEMS;
    }

    /**
     * 더 많은 결과 존재 안내 메시지 출력
     */
    private static void displayMoreResultsNotice() {
        System.out.println("\n... (더 많은 결과가 있습니다. 저장된 파일에서 전체 결과를 확인하세요)");
    }

    /**
     * 파일 저장 성공 메시지를 출력하는 메소드
     *
     * @param fileName 저장된 파일명
     * @param itemCount 저장된 항목 수
     */
    public static void displaySaveSuccess(String fileName, int itemCount) {
        System.out.println("\n=== 저장 완료 ===");
        System.out.println("저장된 파일: " + fileName);
        System.out.println("저장된 항목 수: " + itemCount);
    }

    /**
     * 성능 통계를 출력하는 메소드
     *
     * @param stats 성능 통계 정보
     */
    public static void displayPerformanceStats(PerformanceMonitor.SearchStats stats) {
        System.out.println("\n=== 성능 통계 ===");
        System.out.printf("전체 실행 시간: %.2f ms%n", stats.getTotalTimeMs());
        System.out.printf("파일 검색 시간: %.2f ms%n", stats.getFileSearchTimeMs());
        System.out.printf("단어 검색 시간: %.2f ms%n", stats.getSearchTimeMs());
        System.out.printf("검색된 파일 수: %d개%n", stats.getFilesSearched());
        System.out.printf("처리된 라인 수: %d줄%n", stats.getLinesProcessed());
    }
}
