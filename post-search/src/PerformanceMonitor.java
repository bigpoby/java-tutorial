/**
 * 성능 측정 및 모니터링을 담당하는 클래스
 */
public class PerformanceMonitor {
    
    /**
     * 검색 성능 통계를 수집하는 클래스
     */
    public static class SearchStats {
        private long startTime;
        private long fileSearchStartTime;
        private long searchStartTime;
        private long endTime;
        
        private long fileSearchTimeMs;
        private long searchTimeMs;
        private int filesSearched;
        private int linesProcessed;
        
        public SearchStats() {
            this.startTime = System.currentTimeMillis();
        }
        
        /**
         * 파일 검색 시작을 기록
         */
        public void startFileSearch() {
            this.fileSearchStartTime = System.currentTimeMillis();
        }
        
        /**
         * 파일 검색 완료를 기록
         * @param filesFound 발견된 파일 수
         */
        public void endFileSearch(int filesFound) {
            this.fileSearchTimeMs = System.currentTimeMillis() - fileSearchStartTime;
            this.filesSearched = filesFound;
        }
        
        /**
         * 단어 검색 시작을 기록
         */
        public void startWordSearch() {
            this.searchStartTime = System.currentTimeMillis();
        }
        
        /**
         * 단어 검색 완료를 기록
         * @param linesProcessed 처리된 라인 수
         */
        public void endWordSearch(int linesProcessed) {
            this.searchTimeMs = System.currentTimeMillis() - searchStartTime;
            this.linesProcessed = linesProcessed;
        }
        
        /**
         * 전체 작업 완료를 기록
         */
        public void endTotal() {
            this.endTime = System.currentTimeMillis();
        }
        
        // Getters
        public double getTotalTimeMs() {
            return endTime - startTime;
        }
        
        public double getFileSearchTimeMs() {
            return fileSearchTimeMs;
        }
        
        public double getSearchTimeMs() {
            return searchTimeMs;
        }
        
        public int getFilesSearched() {
            return filesSearched;
        }
        
        public int getLinesProcessed() {
            return linesProcessed;
        }
        
        /**
         * 초당 처리 라인 수 계산
         * @return 초당 라인 수
         */
        public double getLinesPerSecond() {
            if (searchTimeMs == 0) return 0;
            return (linesProcessed * 1000.0) / searchTimeMs;
        }
        
        /**
         * 평균 파일당 처리 시간 계산
         * @return 파일당 평균 시간 (ms)
         */
        public double getAverageTimePerFile() {
            if (filesSearched == 0) return 0;
            return (double) searchTimeMs / filesSearched;
        }
        
        @Override
        public String toString() {
            return String.format(
                "SearchStats{totalTime=%.2fms, fileSearchTime=%.2fms, wordSearchTime=%.2fms, " +
                "filesSearched=%d, linesProcessed=%d, linesPerSecond=%.1f}",
                getTotalTimeMs(), getFileSearchTimeMs(), getSearchTimeMs(),
                filesSearched, linesProcessed, getLinesPerSecond()
            );
        }
    }
    
    /**
     * 메모리 사용량을 측정하는 메소드
     * @return 메모리 사용 정보
     */
    public static MemoryInfo getMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        return new MemoryInfo(totalMemory, freeMemory, usedMemory, maxMemory);
    }
    
    /**
     * 메모리 정보를 담는 클래스
     */
    public static class MemoryInfo {
        private final long totalMemory;
        private final long freeMemory;
        private final long usedMemory;
        private final long maxMemory;
        
        public MemoryInfo(long totalMemory, long freeMemory, long usedMemory, long maxMemory) {
            this.totalMemory = totalMemory;
            this.freeMemory = freeMemory;
            this.usedMemory = usedMemory;
            this.maxMemory = maxMemory;
        }
        
        public double getTotalMemoryMB() { return totalMemory / (1024.0 * 1024.0); }
        public double getFreeMemoryMB() { return freeMemory / (1024.0 * 1024.0); }
        public double getUsedMemoryMB() { return usedMemory / (1024.0 * 1024.0); }
        public double getMaxMemoryMB() { return maxMemory / (1024.0 * 1024.0); }
        
        public double getMemoryUsagePercent() {
            return (usedMemory * 100.0) / totalMemory;
        }
        
        @Override
        public String toString() {
            return String.format(
                "Memory: Used=%.1fMB, Free=%.1fMB, Total=%.1fMB, Max=%.1fMB (%.1f%% used)",
                getUsedMemoryMB(), getFreeMemoryMB(), getTotalMemoryMB(), 
                getMaxMemoryMB(), getMemoryUsagePercent()
            );
        }
    }
}
