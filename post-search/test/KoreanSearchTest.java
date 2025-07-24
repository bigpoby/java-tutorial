import java.io.File;
import java.util.List;

/**
 * 한글 검색 기능 테스트 프로그램
 */
public class KoreanSearchTest {
    public static void main(String[] args) {
        System.out.println("=== 한글 검색 기능 테스트 ===");
        
        // 테스트 파일들 찾기
        File testDir = new File("e:\\shin\\java\\post-search\\test-files");
        List<File> txtFiles = FileManager.findTxtFilesRecursive(testDir);
        
        System.out.println("발견된 txt 파일 수: " + txtFiles.size());
        
        // 한글 검색어들로 테스트
        String[] koreanSearchWords = {"한글", "테스트", "프로그램", "검색"};
        
        for (String searchWord : koreanSearchWords) {
            System.out.println("\\n--- 검색어: \"" + searchWord + "\" ---");
            List<SearchEngine.SearchResult> results = SearchEngine.searchInFiles(txtFiles, searchWord);
            
            if (results.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                System.out.println("총 " + results.size() + "개 결과 발견:");
                for (SearchEngine.SearchResult result : results) {
                    System.out.println("파일: " + result.getFileName() + 
                                     ", 행 " + result.getLineNumber() + 
                                     ": " + result.getLineContent());
                }
            }
        }
        
        // 영문 검색어도 테스트
        System.out.println("\\n--- 영문 검색 테스트: \"test\" ---");
        List<SearchEngine.SearchResult> englishResults = SearchEngine.searchInFiles(txtFiles, "test");
        System.out.println("영문 검색 결과: " + englishResults.size() + "개");
        
        // 한영 혼용 검색 테스트
        System.out.println("\\n--- 한영 혼용 검색 테스트: \"한글 text\" ---");
        List<SearchEngine.SearchResult> mixedResults = SearchEngine.searchInFiles(txtFiles, "text");
        System.out.println("혼용 검색 결과: " + mixedResults.size() + "개");
    }
}
