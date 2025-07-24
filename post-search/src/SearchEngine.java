import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 텍스트 검색 엔진 클래스
 */
public class SearchEngine {
    
    /**
     * 단일 파일에서 특정 단어를 검색하는 메소드
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
     * @param files 검색할 파일 목록
     * @param searchWord 검색할 단어
     * @return 모든 파일의 검색 결과
     */
    public static List<SearchResult> searchInFiles(List<File> files, String searchWord) {
        List<SearchResult> allResults = new ArrayList<>();
        
        for (File file : files) {
            List<SearchResult> fileResults = searchInFile(file, searchWord);
            allResults.addAll(fileResults);
        }
        
        return allResults;
    }
    
    /**
     * 문자열에 특정 단어가 포함되어 있는지 확인 (대소문자 구분 없음)
     * @param text 검색할 텍스트
     * @param searchWord 찾을 단어
     * @return 포함 여부
     */
    private static boolean containsWord(String text, String searchWord) {
        return text.toLowerCase().contains(searchWord.toLowerCase());
    }
    
    /**
     * 텍스트에서 검색 단어의 위치를 찾는 메소드
     * @param text 검색할 텍스트
     * @param searchWord 찾을 단어
     * @return 단어 위치 목록
     */
    private static List<Integer> findWordPositions(String text, String searchWord) {
        List<Integer> positions = new ArrayList<>();
        String lowerText = text.toLowerCase();
        String lowerSearchWord = searchWord.toLowerCase();
        int index = 0;
        
        while ((index = lowerText.indexOf(lowerSearchWord, index)) != -1) {
            positions.add(index);
            index += lowerSearchWord.length();
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
        public String getFileName() { return fileName; }
        public String getFilePath() { return filePath; }
        public int getLineNumber() { return lineNumber; }
        public String getLineContent() { return lineContent; }
        public List<Integer> getWordPositions() { return wordPositions; }
        
        @Override
        public String toString() {
            return String.format("[%s:%d] %s", fileName, lineNumber, lineContent);
        }
    }
}
