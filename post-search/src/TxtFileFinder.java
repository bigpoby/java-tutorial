import java.io.File;
import java.util.Scanner;
import java.util.List;

/**
 * 윈도우 디렉토리를 입력받아 txt 파일의 목록을 출력하는 프로그램 (리팩토링된 버전)
 */
public class TxtFileFinder {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            ResultDisplayer.displayHeader("TXT 파일 검색 프로그램");
            System.out.print("검색할 디렉토리 경로를 입력하세요: ");
            String directoryPath = scanner.nextLine();
            
            // 디렉토리 검증
            File directory;
            try {
                directory = FileManager.validateDirectory(directoryPath);
            } catch (IllegalArgumentException e) {
                ResultDisplayer.displayError(e.getMessage());
                return;
            }
            
            // txt 파일 목록 검색
            List<File> txtFiles = FileManager.findTxtFiles(directory);
            
            // 결과 출력
            ResultDisplayer.displayFileList(directoryPath, txtFiles, false);
            
        } catch (Exception e) {
            ResultDisplayer.displayError("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
