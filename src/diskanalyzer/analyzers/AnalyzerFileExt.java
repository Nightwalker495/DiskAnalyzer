package diskanalyzer.analyzers;

import diskanalyzer.analyzers.utils.MutableInteger;
import diskanalyzer.analyzers.utils.AverageVal;
import java.io.File;
import java.util.HashMap;

/**
 * Holds the information regarding the usage of various file extensions.
 * 
 * @author Milan Ondrasovic
 * @version 2014-12-16
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class AnalyzerFileExt extends Analyzer {
    private boolean caseSensitive;
    private HashMap<String, MutableInteger> extensions;
    private AverageVal averageLen;
    private String longestExt;
    
    /**
     * Default constructor. Returns a new AnalyzerFileExt object.
     */
    public AnalyzerFileExt() {
        this(true);
    }
    
    /**
     * Specific constructor. Returns a new AnalyzerFileExt object.
     * 
     * @param caseSensitive specifies whether to use case-sensitive matching
     */
    public AnalyzerFileExt(boolean caseSensitive) {
        super("File extension analyzer",
                "Analyzes file extension length and characters it contains",
                true, false);
        
        this.caseSensitive = caseSensitive;
        this.extensions = new HashMap<>();
        this.averageLen = new AverageVal();
        this.longestExt = "";
    }
    
    /**
     * Adds new information about the ext of the specified file to the 
     * overall list. 
     * 
     * @param file specified file to process the ext of
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File file) {
        String ext = this.extractExt(file);

        if (ext != null) {
            ext = this.obeyCaseSensitivityOpt(ext);

            MutableInteger occurs = this.extensions.get(ext);
            if (occurs == null) { // Not in the list yet
                this.extensions.put(ext, new MutableInteger(1));
            } else {
                // Increment the number of occurences
                occurs.inc();
            }

            int extLen = ext.length();
            if (extLen > this.longestExt.length()) {
                this.longestExt = ext;
            }

            this.averageLen.addVal(extLen);
        }
        
        return false;
    }
    
    /**
     * Generates an output based upon the analysis.
     * 
     * @return formatted string representation on the analysis
     */
    @Override
    public AnalysisStat genOutput() {
        AnalysisStat stat = new AnalysisStat("File extension information");
        
        String avrgLen = this.averageLen.toString();
        stat.add("Average extension length", avrgLen);
        
        String mostUsedExtName = this.getMostUsedExt();
        stat.add("Most used extension (name)", mostUsedExtName);
        
        int mostUsedExtUsage = this.getMostUsedExtUsage();
        stat.add("Most used extension (usage)", mostUsedExtUsage);
        
        String longExt = this.getLongestExt();
        stat.add("Longest extension", longExt);
        
        return stat;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.extensions.clear();
        this.averageLen.clear();
        this.longestExt = "";
    }
    
    /**
     * Setter.
     * 
     * @param sensitivity specifies whether to enable case-sensitive mode or
     * not
     */
    public void setCaseSensitive(boolean sensitivity) {
        this.caseSensitive = sensitivity;
    }
    
    /**
     * Getter.
     * 
     * @return true, if case-sensitive mode in enabled, false otherwise
     */
    public boolean getCaseSensitive() {
        return this.caseSensitive;
    }
    
    /**
     * A string representation of the most used file ext.
     * 
     * @return string representation of the most used file ext, null if no such
     * extension exists
     */
    public String getMostUsedExt() {
        return this.findMostUsedExt();
    }
    
    /**
     * A number of usages of the most used ext.
     * 
     * @return number of usages of the most used ext
     */
    public int getMostUsedExtUsage() {
        String ext = this.findMostUsedExt();
        
        return (ext == null) ? 0 : this.extensions.get(ext).getValue();
    }
    
    /**
     * An average length of file extensions calculated from the list.
     * 
     * @return average length of the extensions 
     */
    public int getAverageLen() {
        return this.averageLen.getAverageValInt();
    }
    
    /**
     * String representation of the longest ext. Extension with the
     * greatest number of characters in its name.
     * 
     * @return ext with the greatest number of characters 
     */
    public String getLongestExt() {
        return this.longestExt;
    }
    
    /*
     * Extracts the ext from the specified file. It searches for the last
     * occurrence of '.' character as indicator of the file ext.
     */
    private String extractExt(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        
        return (index >= 0) ? fileName.substring(index) : null;
    }
    
    /*
     * Modifies the ext according to the specified case-sensitivity
     * options. If the case-insensitive mode is enabled, then the ext
     * is converted to the lower-case string, otherwise, no modification is 
     * made.
     */
    private String obeyCaseSensitivityOpt(String extension) {
        extension = extension.trim();
        
        return (this.caseSensitive) ? extension : extension.toLowerCase();
    }
    
    /* Returns the most used extesion */
    private String findMostUsedExt() {
        String mostUsed = null;
        int maxOccurs = 0;

        for (String ext : this.extensions.keySet()) {
            int occurs = this.extensions.get(ext).getValue();

            if (occurs > maxOccurs) {
                mostUsed = ext;
                maxOccurs = occurs;
            }
        }

        return mostUsed;
    }
}
