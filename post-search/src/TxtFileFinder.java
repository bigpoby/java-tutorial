import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * 윈도우 디렉토리를 입력받아 txt 파일의 목록을 출력하는 프로그램
 */
public class TxtFileFinder {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ResultDisplayer.displayHeader("TXT 파일 검색 프로그램");
        System.out.print("검색할 디렉토리 경로를 입력하세요: ");
        String directoryPath = scanner.nextLine();
        
        // 입력받은 경로로 File 객체 생성
        File directory = new File(directoryPath);
        
        // 디렉토리 유효성 검사
        if (!directory.exists()) {
            ResultDisplayer.displayError("지정된 경로가 존재하지 않습니다.");
            scanner.close();
            return;
        }
        
        if (!directory.isDirectory()) {
            ResultDisplayer.displayError("지정된 경로가 디렉토리가 아닙니다.");
            scanner.close();
            return;
        }
        
        // txt 파일 목록 검색
        List<File> txtFiles = findTxtFiles(directory);
        
        // 결과 출력
        ResultDisplayer.displayBasicResults(directoryPath, txtFiles);
        
        scanner.close();
    }
    
    /**
     * 지정된 디렉토리에서 txt 파일들을 찾는 메소드
     * @param directory 검색할 디렉토리
     * @return txt 파일 목록
     */
    private static List<File> findTxtFiles(File directory) {
        List<File> txtFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                    txtFiles.add(file);
                }
            }
        }
        
        return txtFiles;
    }
}
