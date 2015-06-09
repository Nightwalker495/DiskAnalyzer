package diskanalyzer.analyzers;

import diskanalyzer.analyzers.utils.AverageVal;
import java.io.File;

/**
 * Holds the information about sizes of all processed files. Total files size, 
 * biggest file size together with the average file size are provided.
 * 
 * @author Milan Ondrasovic
 * @version 2014-12-26
 * @author Milan Ondrasovic
 * @version 2015-05-08
 */
public class AnalyzerFileSize extends Analyzer {
    private long totalSize;
    private File biggestFile;
    private final AverageVal averageSize;
    
    /**
     * Returns a new AnalyzerFileSize object.
     */
    public AnalyzerFileSize() {
        super("File size analyzer", "Analyzes file size", true, false);
        
        this.totalSize = 0L;
        this.biggestFile = null;
        this.averageSize = new AverageVal();
    }
    
    /**
     * Adds new file size information.
     * 
     * @param file file to add
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File file) {
        if (file.isFile()) {
            long size = file.length();
            
            if (this.biggestFile != null) {
                if (size > this.biggestFile.length()) {
                    this.biggestFile = file;
                }
            } else {
                this.biggestFile = file;
            }
            
            this.averageSize.addVal(size);
            this.totalSize += size;
            
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Generates an output based upon the analysis.
     * 
     * @return formatted string representation on the analysis
     */
    @Override
    public AnalysisStat genOutput() {
        AnalysisStat stat = new AnalysisStat("File size information");
        
        String fileSizeUnits = this.convertFileSize(this.totalSize);
        stat.add("Total size", fileSizeUnits);
        
        long avrgSize = this.averageSize.getAverageValLong();
        fileSizeUnits = this.convertFileSize(avrgSize);
        stat.add("Average size", fileSizeUnits);
        
        long biggestFileSize = this.getBiggestFileSize();
        fileSizeUnits = this.convertFileSize(biggestFileSize);
        stat.add("Biggest file (size)", fileSizeUnits);
        
        String biggestFilePath = this.getPathBiggestFile();
        stat.add("Biggest file (path)", biggestFilePath);
        
        return stat;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.totalSize = 0L;
        this.biggestFile = null;
        this.averageSize.clear();
    }
    
    /**
     * Getter.
     * 
     * @return total size of all files
     */
    public long getTotalSize() {
        return this.totalSize;
    }
    
    /**
     * Getter.
     * 
     * @return average size of all files
     */
    public long getAverageSize() {
        return this.averageSize.getAverageValLong();
    }
    
    /**
     * Getter.
     * 
     * @return path to the biggest file
     */
    public String getPathBiggestFile() {
        return (this.biggestFile == null) ? null : this.biggestFile.getPath();
    }
    
    /**
     * Getter.
     * 
     * @return size of the biggest file in bytes
     */
    public long getBiggestFileSize() {
        return (this.biggestFile == null) ? 0L : this.biggestFile.length();
    }
    
    /* Converts bytes into different data units and returns it as a string. */
    private String convertFileSize(long bytes) {
        return String.format("~%.2fKB | ~%.3fMB | ~%.4fGB",
                this.bytesToKiloBytes(bytes),
                this.bytesToMegaBytes(bytes),
                this.bytesToGigaBytes(bytes));
    }
    
    /* Converts bytes to kilobytes. */
    private double bytesToKiloBytes(long bytes) {
        return ((double) bytes) / 1024.0;
    }
    
    /* Converts bytes to megabytes. */
    private double bytesToMegaBytes(long bytes) {
        return ((double) bytes) / 1048576.0;
    }
    
    /* Converts bytes to gigabytes. */
    private double bytesToGigaBytes(long bytes) {
        return ((double) bytes) / 1073741824.0;
    }
}
