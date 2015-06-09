package diskanalyzer.analyzers;

import java.io.File;

/**
 * Abstract class for all analyzer engines.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-19
 * @author Milan Ondrasovic
 * @version 2015-05-08
 */
public abstract class Analyzer {
    private final String name;
    private final String description;
    private final boolean requiresFile;
    private final boolean requiresDir;
    private final boolean requiresFileOrDir;
    
    /**
     * Default constructor for all analyzers.
     * 
     * @param name name of the analyzer (should be short)
     * @param description description of the analyzer (should be short)
     * @param requiresFile indicator whether the analyzer requires only files
     * @param requiresDir indicator whether the analyzer requires only
     * directories
     */
    public Analyzer(String name, String description, boolean requiresFile,
            boolean requiresDir) {
        this.name = name;
        this.description = name;
        this.requiresFile = requiresFile;
        this.requiresDir = requiresDir;
        this.requiresFileOrDir = this.requiresFile && this.requiresDir;
    }
    
    /**
     * Getter. 
     * 
     * @return name of the analyzer
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter.
     * 
     * @return description of the analyzer
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Checks whether the analyzer requires only files for analysis. Getter.
     * 
     * @return true if only files are allowed, false otherwise
     */
    public boolean requiresFile() {
        return this.requiresFile;
    }
    
    /**
     * Checks whether the analyzer requires only directories for analysis.
     * Getter.
     * 
     * @return true if only only directories are allowed, false otherwise
     */
    public boolean requiresDirectory() {
        return this.requiresDir;
    }
    
    /**
     * Checks whether the analyzer requires both files or directories for the
     * analysis. Getter.
     * 
     * @return true if both files or directories are allowed, false otherwise
     */
    public boolean requiresFileOrDir() {
        return this.requiresFileOrDir;
    }
    
    /**
     * Adds new data for further processing.
     * 
     * @param data file data to be added
     * @return true on success, false otherwise
     */
    public abstract boolean addData(File data);
    
    /**
     * Generates output based on the analyzed information.
     * 
     * @return list of all analysis information groups, or null in case of
     * empty database
     */
    public abstract AnalysisStat genOutput();
    
    /**
     * Resets the analyzer to the state at the beginning.
     */
    public abstract void clear();
}
