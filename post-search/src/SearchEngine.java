
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 텍스트 검색 엔진 클래스
 */
public class SearchEngine {

    /**
     * 단일 파일에서 특정 단어를 검색하는 메소드
     *
     * @param file 검색할 파일
     * @param searchWord 검색할 단어
     * @return 검색 결과 목록
     */
    public static List<SearchResult> searchInFile(File file, String searchWord) {
        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (containsWord(line, searchWord)) {
                    results.add(new SearchResult(
                            file.getName(),
                            file.getAbsolutePath(),
                            lineNumber,
                            line.trim(),
                            findWordPositions(line, searchWord)
                    ));
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 오류: " + file.getName() + " - " + e.getMessage());
        }

        return results;
    }

    /**
     * 여러 파일에서 단어를 검색하는 메소드
     *
     * @param files 검색할 파일 목록
     * @param searchWord 검색할 단어
     * @return 모든 파일의 검색 결과
     */
    public static List<SearchResult> searchInFiles(List<File> files, String searchWord) {
        return files.stream()
                .map(file -> searchInFile(file, searchWord))
                .flatMap(List::stream)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * 문자열에 특정 단어가 포함되어 있는지 확인 (대소문자 구분 없음, 한글 지원)
     *
     * @param text 검색할 텍스트
     * @param searchWord 찾을 단어
     * @return 포함 여부
     */
    private static boolean containsWord(String text, String searchWord) {
        // 한글의 경우 대소문자 변환이 의미가 없으므로, 
        // 영문의 경우만 소문자로 변환하여 비교
        if (isKoreanText(searchWord)) {
            return text.contains(searchWord);
        } else {
            return text.toLowerCase().contains(searchWord.toLowerCase());
        }
    }

    /**
     * 텍스트가 한글을 포함하는지 확인하는 메소드
     *
     * @param text 확인할 텍스트
     * @return 한글 포함 여부
     */
    private static boolean isKoreanText(String text) {
        for (char c : text.toCharArray()) {
            if ((c >= 0xAC00 && c <= 0xD7A3)
                    || // 한글 완성형
                    (c >= 0x1100 && c <= 0x11FF)
                    || // 한글 자음
                    (c >= 0x3130 && c <= 0x318F)
                    || // 한글 호환 자모
                    (c >= 0xA960 && c <= 0xA97F)
                    || // 한글 확장-A
                    (c >= 0xD7B0 && c <= 0xD7FF)) { // 한글 확장-B
                return true;
            }
        }
        return false;
    }

    /**
     * 텍스트에서 검색 단어의 위치를 찾는 메소드 (한글 지원)
     *
     * @param text 검색할 텍스트
     * @param searchWord 찾을 단어
     * @return 단어 위치 목록
     */
    private static List<Integer> findWordPositions(String text, String searchWord) throws UnsupportedEncodingException {
        List<Integer> positions = new ArrayList<>();
        int index = 0;

        if (isKoreanText(searchWord)) {
            // 한글의 경우 대소문자 변환 없이 직접 검색
            while ((index = text.indexOf(searchWord, index)) != -1) {
                positions.add(index);
                index += searchWord.length();
            }
        } else {
            // 영문의 경우 대소문자 구분 없이 검색
            String lowerText = text.toLowerCase();
            String lowerSearchWord = searchWord.toLowerCase();

            while ((index = lowerText.indexOf(lowerSearchWord, index)) != -1) {
                positions.add(index);
                index += lowerSearchWord.length();
            }
        }

        return positions;
    }

    /**
     * 검색 결과를 나타내는 클래스
     */
    public static class SearchResult {

        private final String fileName;
        private final String filePath;
        private final int lineNumber;
        private final String lineContent;
        private final List<Integer> wordPositions;

        public SearchResult(String fileName, String filePath, int lineNumber,
                String lineContent, List<Integer> wordPositions) {
            this.fileName = fileName;
            this.filePath = filePath;
            this.lineNumber = lineNumber;
            this.lineContent = lineContent;
            this.wordPositions = wordPositions != null ? wordPositions : new ArrayList<>();
        }

        // Getters
        public String getFileName() {
            return fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public String getLineContent() {
            return lineContent;
        }

        public List<Integer> getWordPositions() {
            return wordPositions;
        }

        @Override
        public String toString() {
            return String.format("[%s:%d] %s", fileName, lineNumber, lineContent);
        }
    }
}
