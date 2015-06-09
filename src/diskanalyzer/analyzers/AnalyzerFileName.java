package diskanalyzer.analyzers;

import diskanalyzer.analyzers.utils.AverageVal;
import java.io.File;

/**
 * Holds the information regarding file names (directories and regular files).
 * 
 * @author Milan Ondrasovic
 * @version 2014-12-16
 * @author Milna Ondrasovic
 * @version 2015-04-18
 */
public class AnalyzerFileName extends Analyzer {
    private File maxLenName;
    private final AverageVal averageLen;
    
    /**
     * Returns a new AnalyzerFileName object.
     */
    public AnalyzerFileName() {
        super("File name analyzer",
                "Analyzes file name length and characters it contains", true,
                true);
        
        this.maxLenName = null;
        this.averageLen = new AverageVal();
    }
    
    /**
     * Adds new data regarding the name of the specified file, and
     * updates all the dependant attributes.
     * 
     * @param file file to add
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File file) {
        int fileNameLen = file.getName().length();

        if (this.maxLenName != null) {
            if (fileNameLen > this.maxLenName.getName().length()) {
                this.maxLenName = file;
            }
        } else {
            this.maxLenName = file;
        }

        this.averageLen.addVal(fileNameLen);

        return true;
    }
    
    /**
     * Generates an output based upon the analysis.
     * 
     * @return formatted string representation on the analysis
     */
    @Override
    public AnalysisStat genOutput() {
        AnalysisStat stat = new AnalysisStat("File name information");
        
        String avrgLen = this.averageLen.toString();
        stat.add("Average file name length", avrgLen);
        
        String longestNameText = this.getLongestNameFileText();
        stat.add("Longest file name (text)", longestNameText);
        
        String longestNamePath = this.getLongestNameFilePath();
        stat.add("Longest file name (path)", longestNamePath);

        int longestNameCharNum = this.getLongestNameLength();
        stat.add("Longest file name (length)", longestNameCharNum);
        
        return stat;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.maxLenName = null;
        this.averageLen.clear();
    }
    
    /**
     * Getter.
     * 
     * @return average file name length
     */
    public int getAverageLen() {
        return this.averageLen.getAverageValInt();
    }
    
    /**
     * Getter.
     * 
     * @return text representation of the longest file name
     */
    public String getLongestNameFileText() {
        return (this.maxLenName == null)  ? null : this.maxLenName.getName();
    }
    
    /**
     * Getter.
     * 
     * @return path to the file with the longest file name
     */
    public String getLongestNameFilePath() {
        return (this.maxLenName == null) ? null :
                this.getPathWithoutFileName(this.maxLenName.getPath());
    }
    
    /**
     * Getter.
     * 
     * @return number of characters of the longest file name
     */
    public int getLongestNameLength() {
        String text = this.getLongestNameFileText();
        
        return (text == null) ? 0 : text.length();
    }
    
    /* 
     * Returns only the path leading to a specified file, file name is excluded.
    */
    private String getPathWithoutFileName(String path) {
        int index = path.lastIndexOf(File.separatorChar);
        
        switch (index) {
            case (-1):
                return null;
            case 0:
                return ".";
            default:
                return path.substring(0, index);
        }
    }
}
