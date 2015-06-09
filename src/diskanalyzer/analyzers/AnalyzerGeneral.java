package diskanalyzer.analyzers;

import java.io.File;

/**
 * Holds the information regarding number of files and directories processed.
 * 
 * @author Milan Ondrasovic
 * @version 2012-12-16
 */
public class AnalyzerGeneral extends Analyzer {
    private int filesNum;
    private int dirsNum;
    
    /**
     * Returns a new AnalyzerGeneral object.
     */
    public AnalyzerGeneral() {
        super("General information analyzer",
                "Analyzes number of processed files and directories", true,
                true);
        
        this.filesNum = 0;
        this.dirsNum = 0;
    }
    
    /**
     * Adds new data according to the file type. If the file is a regular file,
     * then the number of processed files is increased, otherwise, if it's a
     * directory, the number of processed directories is increased.
     * 
     * @param file file to add
     * @return true on success, false otherwise 
     */
    @Override
    public boolean addData(File file) {
        if (file.isDirectory()) {
            this.dirsNum++;
        } else {
            this.filesNum++;
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
        AnalysisStat stat = new AnalysisStat("General information");
        
        stat.add("Number of files", this.filesNum);
        stat.add("Number of directories", this.dirsNum);
        
        return stat;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.filesNum = 0;
        this.dirsNum = 0;
    }
    
    /**
     * Getter.
     * 
     * @return overall number of processed regular files
     */
    public int getFilesNum() {
        return this.filesNum;
    }
    
    /**
     * Getter.
     * 
     * @return overall number of processed directories
     */
    public int getDirsNum() {
        return this.dirsNum;
    }
}
