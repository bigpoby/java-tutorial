import java.io.File;
import java.util.List;

/**
 * 검색 결과를 출력하는 클래스
 */
public class ResultDisplayer {
    
    /**
     * 기본 검색 결과를 출력하는 메소드
     * @param directoryPath 검색한 디렉토리 경로
     * @param txtFiles 발견된 txt 파일 목록
     */
    public static void displayBasicResults(String directoryPath, List<File> txtFiles) {
        System.out.println("\n=== 검색 결과 ===");
        System.out.println("검색 디렉토리: " + directoryPath);
        System.out.println("발견된 txt 파일 개수: " + txtFiles.size());
        System.out.println();
        
        if (txtFiles.isEmpty()) {
            System.out.println("txt 파일을 찾을 수 없습니다.");
        } else {
            System.out.println("txt 파일 목록:");
            for (int i = 0; i < txtFiles.size(); i++) {
                File file = txtFiles.get(i);
                System.out.printf("%d. %s (크기: %d bytes, 수정일: %s)%n", 
                    i + 1, 
                    file.getName(), 
                    file.length(),
                    new java.util.Date(file.lastModified()));
            }
        }
    }
    
    /**
     * 재귀 검색 결과를 출력하는 메소드
     * @param directoryPath 검색한 디렉토리 경로
     * @param txtFiles 발견된 txt 파일 목록
     * @param recursive 재귀 검색 여부
     */
    public static void displayRecursiveResults(String directoryPath, List<File> txtFiles, boolean recursive) {
        System.out.println("\n=== 검색 결과 ===");
        System.out.println("검색 디렉토리: " + directoryPath);
        System.out.println("검색 방식: " + (recursive ? "재귀 검색 (하위 디렉토리 포함)" : "현재 디렉토리만"));
        System.out.println("발견된 txt 파일 개수: " + txtFiles.size());
        System.out.println();
        
        if (txtFiles.isEmpty()) {
            System.out.println("txt 파일을 찾을 수 없습니다.");
        } else {
            System.out.println("txt 파일 목록:");
            for (int i = 0; i < txtFiles.size(); i++) {
                File file = txtFiles.get(i);
                System.out.printf("%d. %s%n", i + 1, file.getAbsolutePath());
                System.out.printf("   크기: %d bytes, 수정일: %s%n", 
                    file.length(),
                    new java.util.Date(file.lastModified()));
                System.out.println();
            }
        }
    }
    
    /**
     * 헤더 정보를 출력하는 메소드
     * @param title 프로그램 제목
     */
    public static void displayHeader(String title) {
        System.out.println("=== " + title + " ===");
    }
    
    /**
     * 오류 메시지를 출력하는 메소드
     * @param errorMessage 오류 메시지
     */
    public static void displayError(String errorMessage) {
        System.out.println("오류: " + errorMessage);
    }
}
