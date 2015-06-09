package diskanalyzer.analyzers;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Analyzer timestamps of individual files.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-20
 */
public class AnalyzerTimestamp extends Analyzer {  
    private File oldestLastModif;
    
    /**
     * Default constructor.
     */
    public AnalyzerTimestamp() {
        super("Timestamp analyzer",
                "Analyzes timestamps of files and directories", true, true);
        
        this.oldestLastModif = null;
    }
    
    /**
     * Processes file/directory timestamp.
     * 
     * @param data file or directory to process
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File data) {
        if (this.oldestLastModif == null) {
            this.oldestLastModif = data;
        } else {
            long lastModif = data.lastModified();
            
            if (this.isSpecialFileLastModif(lastModif)) {
                return false;
            } else {
                if (this.oldestLastModif.lastModified() > lastModif) {
                    this.oldestLastModif = data;
                }
            }
        }
        
        return true;
    }

    /**
     * Generates an output based upon the analysis.
     * 
     * @return formatted string representation on the analysis
     */
    @Override
    public AnalysisStat genOutput() {
        AnalysisStat stat = new AnalysisStat("Filestamp information");
        
        stat.add("Oldest file (path)", this.getOldestFilePath());
        stat.add("Oldest file (modification date)", this.getOldestFileDate());
        
        return stat;
    }

    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.oldestLastModif = null;
    }
    
    /**
     * Getter.
     * 
     * @return path of the oldest file with regard to the last date of
     * modification
     */
    public String getOldestFilePath() {
        return (this.oldestLastModif == null) ? null : 
                this.oldestLastModif.getPath();
    }
    
    /**
     * Getter.
     * 
     * @return human-readable date in format <b>MM/dd/yyyy HH:mm:ss</b>.
     */
    public String getOldestFileDate() {
        return (this.oldestLastModif == null) ? null :
                this.getTimestampFormatted(this.oldestLastModif);
    }
    
    /*
     * Returns the date of last modification of the file in a human-readable
     * format.
    */
    private String getTimestampFormatted(File file) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
        return format.format(file.lastModified());
    }
    
    /*
     * Special files tend to have a last modification date of 01/01/1970, which
     * we want to ignore.
    */
    private boolean isSpecialFileLastModif(long lastModif) {
        return lastModif == 0L;
    }
}
