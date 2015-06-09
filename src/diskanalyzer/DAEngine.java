package diskanalyzer;

import diskanalyzer.analyzers.AnalysisStat;
import diskanalyzer.analyzers.AnalyzerFileName;
import diskanalyzer.analyzers.AnalyzerFileExt;
import diskanalyzer.analyzers.Analyzer;
import diskanalyzer.analyzers.AnalyzerGeneral;
import diskanalyzer.analyzers.AnalyzerDir;
import diskanalyzer.analyzers.AnalyzerFileSize;
import diskanalyzer.analyzers.AnalyzerSpaceUsage;
import diskanalyzer.analyzers.AnalyzerTimestamp;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Main engine which governs the underlying objects. It's purpose is to run the 
 * whole analysis, decide which data will be captured and together with this it
 * decides about the format of the output string.
 *
 * @author Milan Ondrasovic
 * @version 2014-12-17
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class DAEngine {
    private static DAEngine instance = null;
    
    private boolean recurse;
    private boolean analysisDone;
    private final Collection<Analyzer> analyzers;
    private final Collection<Analyzer> analyzersRequireFile;
    private final Collection<Analyzer> analyzersRequireDir;
    
    public static DAEngine getInstance() {
        if (instance == null) {
            instance = new DAEngine(true, true); // Default options
        }
        
        return instance;
    }
    
    /*
     * This constructor allows to specify all available options. All values
     * are enabled, i. e., set as True by default.
     */
    private DAEngine(boolean recurse, boolean caseSensitive) {
        // TODO Figure out a way how to manage case-sensitivity as recursion
        this.recurse = recurse;
        this.analysisDone = false;
        this.analyzers = new ArrayList<>();
        this.analyzersRequireFile = new ArrayList<>();
        this.analyzersRequireDir = new ArrayList<>();
        
        this.analyzers.add(new AnalyzerGeneral());
        this.analyzers.add(new AnalyzerDir());
        this.analyzers.add(new AnalyzerFileSize());
        this.analyzers.add(new AnalyzerFileName());
        this.analyzers.add(new AnalyzerFileExt(caseSensitive));
        this.analyzers.add(new AnalyzerTimestamp());
        this.analyzers.add(new AnalyzerSpaceUsage());
        
        for (Analyzer analyzer : this.analyzers) {
            if (analyzer.requiresFile()) {
                this.analyzersRequireFile.add(analyzer);
            }
            
            if (analyzer.requiresDirectory()) {
                this.analyzersRequireDir.add(analyzer);
            }
        }
    }
    
    /**
     * Setter.
     * 
     * @param mode true to enable, false to disable recursive directory walking
     * mode
     */
    public void setRecurse(boolean mode) {
        this.recurse = mode;
    }
    
    /**
     * Getter.
     * 
     * @return true if recurse mode is enabled, false otherwise
     */
    public boolean getRecurse() {
        return this.recurse;
    }
    
    /**
     * Executes the disk analysis.
     * 
     * @param startPath specifies the starting path
     * @return null if starting path was not specified, true otherwise
     */
    public boolean runAnalysis(String startPath) {
        if (startPath != null) {
            if (!startPath.isEmpty()) {
                File startFile = new File(startPath);
                
                if (startFile.exists()) {
                    if (startFile.isDirectory()) {
                        this.walkDir(startFile);
                        this.analysisDone = true;
                        
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Sets all the objects together this one into the state at the time of
     * initialization.
     */
    public void clear() {
        this.analyzers.stream().forEach((analyzer) -> {
            analyzer.clear();
        });
        
        this.analysisDone = false;
    }
    
    /**
     * Generates the final output by collecting all the partial output from the 
     * child objects.
     * 
     * @return final "formatted" output as a string
     */
    public String generateOutput() {
        String str = "";
        
        if (this.analysisDone) {
            for (Analyzer analyzer : this.analyzers) {
                AnalysisStat stat = analyzer.genOutput();

                str += (stat.toString() + "\r\n");
            }
        } else {
            str = "No analysis has been performed.";
        }
        
        return str;
    }
    
    /*
     * Retrieves all the subdirectories for further processing. Recursive
     * directory walking is done according to the specified options.
     * This method send messages to attributes which serve of purpose
     * of holding certain information about files or directories. 
     */
    private void walkDir(File file) {
        File[] fileList = file.listFiles();
        
        if (fileList != null) {
            for (File currFile : fileList) {
                boolean isFile = currFile.isFile();

                if (isFile) {
                    for (Analyzer analyzer : this.analyzersRequireFile) {
                        analyzer.addData(currFile);
                    }
                } else {
                    for (Analyzer analyzer : this.analyzersRequireDir) {
                        analyzer.addData(currFile);
                    }

                    if (this.recurse) {
                        this.walkDir(currFile);
                    }
                }
            }
        }
    }
}
