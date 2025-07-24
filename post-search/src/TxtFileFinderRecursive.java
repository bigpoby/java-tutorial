import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.nio.charset.StandardCharsets;

/**
 * 윈도우 디렉토리를 입력받아 txt 파일의 목록을 출력하는 프로그램 (재귀 검색 버전, 리팩토링됨)
 */
public class TxtFileFinderRecursive {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        
        try {
            ResultDisplayer.displayHeader("TXT 파일 재귀 검색 프로그램");
            System.out.print("검색할 디렉토리 경로를 입력하세요: ");
            String directoryPath = scanner.nextLine();
            
            System.out.print("하위 디렉토리까지 검색하시겠습니까? (y/n): ");
            String searchSubdirs = scanner.nextLine().toLowerCase();
            boolean recursive = searchSubdirs.equals("y") || searchSubdirs.equals("yes");
            
            // 디렉토리 검증
            File directory;
            try {
                directory = FileManager.validateDirectory(directoryPath);
            } catch (IllegalArgumentException e) {
                ResultDisplayer.displayError(e.getMessage());
                return;
            }
            
            // txt 파일 목록 검색
            List<File> txtFiles = recursive ? 
                FileManager.findTxtFilesRecursive(directory) : 
                FileManager.findTxtFiles(directory);
            
            // 결과 출력
            ResultDisplayer.displayFileList(directoryPath, txtFiles, recursive);
            
        } catch (Exception e) {
            ResultDisplayer.displayError("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
