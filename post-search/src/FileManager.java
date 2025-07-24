
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 파일 시스템 관련 작업을 담당하는 클래스
 */
public class FileManager {

    /**
     * 디렉토리 유효성을 검사하는 메소드
     *
     * @param directoryPath 검사할 디렉토리 경로
     * @return 유효한 디렉토리 객체 또는 null
     */
    public static File validateDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            throw new IllegalArgumentException("지정된 경로가 존재하지 않습니다: " + directoryPath);
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("지정된 경로가 디렉토리가 아닙니다: " + directoryPath);
        }

        return directory;
    }

    /**
     * 지정된 디렉토리에서 txt 파일들을 찾는 메소드 (현재 디렉토리만)
     *
     * @param directory 검색할 디렉토리
     * @return txt 파일 목록
     */
    public static List<File> findTxtFiles(File directory) {
        File[] files = directory.listFiles();

        if (files == null) {
            return new ArrayList<>();
        }

        return java.util.Arrays.stream(files)
                .filter(File::isFile)
                .filter(FileManager::isTxtFile)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * 지정된 디렉토리에서 txt 파일들을 재귀적으로 찾는 메소드
     *
     * @param directory 검색할 디렉토리
     * @return txt 파일 목록
     */
    public static List<File> findTxtFilesRecursive(File directory) {
        List<File> txtFiles = new ArrayList<>();
        findTxtFilesRecursiveHelper(directory, txtFiles);
        return txtFiles;
    }

    /**
     * 재귀 검색 헬퍼 메소드
     *
     * @param directory 검색할 디렉토리
     * @param txtFiles 결과를 저장할 리스트
     */
    private static void findTxtFilesRecursiveHelper(File directory, List<File> txtFiles) {
        File[] files = directory.listFiles();

        if (files != null) {
            Arrays.stream(files)
                    .filter(File::isFile)
                    .filter(FileManager::isTxtFile)
                    .forEach(txtFiles::add);

            Arrays.stream(files)
                    .filter(File::isDirectory)
                    .forEach(subDir -> findTxtFilesRecursiveHelper(subDir, txtFiles));
        }
    }

    /**
     * 파일이 txt 파일인지 확인하는 메소드
     *
     * @param file 확인할 파일
     * @return txt 파일 여부
     */
    private static boolean isTxtFile(File file) {
        return file.getName().toLowerCase().endsWith(".txt");
    }

    /**
     * 파일 정보를 반환하는 메소드
     *
     * @param file 정보를 가져올 파일
     * @return 파일 정보 객체
     */
    public static FileInfo getFileInfo(File file) {
        return new FileInfo(
                file.getName(),
                file.getAbsolutePath(),
                file.length(),
                new java.util.Date(file.lastModified())
        );
    }

    /**
     * 파일 정보를 담는 내부 클래스
     */
    public static class FileInfo {

        private final String name;
        private final String absolutePath;
        private final long size;
        private final java.util.Date lastModified;

        public FileInfo(String name, String absolutePath, long size, java.util.Date lastModified) {
            this.name = name;
            this.absolutePath = absolutePath;
            this.size = size;
            this.lastModified = lastModified;
        }

        public String getName() {
            return name;
        }

        public String getAbsolutePath() {
            return absolutePath;
        }

        public long getSize() {
            return size;
        }

        public java.util.Date getLastModified() {
            return lastModified;
        }
    }
}
