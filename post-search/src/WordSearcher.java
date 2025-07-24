import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일에서 특정 단어를 검색하고 결과를 저장하는 클래스
 */
public class WordSearcher {
    
    /**
     * 파일에서 특정 단어가 포함된 행들을 검색하는 메소드
     * @param file 검색할 파일
     * @param searchWord 검색할 단어
     * @return 검색 결과 (행 번호와 내용을 포함)
     */
    public static List<SearchResult> searchWordInFile(File file, String searchWord) {
        List<SearchResult> results = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    results.add(new SearchResult(file.getName(), lineNumber, line.trim()));
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
    public static List<SearchResult> searchWordInFiles(List<File> files, String searchWord) {
        List<SearchResult> allResults = new ArrayList<>();
        
        for (File file : files) {
            List<SearchResult> fileResults = searchWordInFile(file, searchWord);
            allResults.addAll(fileResults);
        }
        
        return allResults;
    }
    
    /**
     * 검색 결과를 파일로 저장하는 메소드
     * @param results 검색 결과 목록
     * @param outputFileName 저장할 파일명
     * @param searchWord 검색한 단어
     * @return 저장 성공 여부
     */
    public static boolean saveResultsToFile(List<SearchResult> results, String outputFileName, String searchWord) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName, java.nio.charset.StandardCharsets.UTF_8))) {
            writer.println("=== 단어 검색 결과 ===");
            writer.println("검색어: " + searchWord);
            writer.println("검색 날짜: " + new java.util.Date());
            writer.println("총 발견된 항목: " + results.size());
            writer.println();
            
            if (results.isEmpty()) {
                writer.println("검색 결과가 없습니다.");
            } else {
                String currentFileName = "";
                for (int i = 0; i < results.size(); i++) {
                    SearchResult result = results.get(i);
                    
                    // 파일명이 바뀔 때마다 구분선 출력
                    if (!currentFileName.equals(result.getFileName())) {
                        if (i > 0) writer.println();
                        writer.println("파일: " + result.getFileName());
                        writer.println("----------------------------------------");
                        currentFileName = result.getFileName();
                    }
                    
                    writer.printf("행 %d: %s%n", result.getLineNumber(), result.getLineContent());
                }
            }
            
            return true;
        } catch (IOException e) {
            System.err.println("파일 저장 오류: " + outputFileName + " - " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 검색 결과를 나타내는 내부 클래스
     */
    public static class SearchResult {
        private String fileName;
        private int lineNumber;
        private String lineContent;
        
        public SearchResult(String fileName, int lineNumber, String lineContent) {
            this.fileName = fileName;
            this.lineNumber = lineNumber;
            this.lineContent = lineContent;
        }
        
        public String getFileName() { return fileName; }
        public int getLineNumber() { return lineNumber; }
        public String getLineContent() { return lineContent; }
        
        @Override
        public String toString() {
            return String.format("[%s:%d] %s", fileName, lineNumber, lineContent);
        }
    }
}
