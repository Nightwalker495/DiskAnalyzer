package diskanalyzer.analyzers;

import diskanalyzer.analyzers.utils.AverageVal;
import java.io.File;

/**
 * Holds the information concerning directory contents and directory trees.
 * 
 * @author Milan Ondrasovic
 * @version 2014-12-24
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class AnalyzerDir extends Analyzer {
    private File biggestDir;
    private int biggestDirSubFilesNum;
    private final AverageVal averageSubFilesNum;
    private File biggestTree;
    private int biggestTreeSize;
    
    /**
     * Returns a new AnalyzerDir object.
     */
    public AnalyzerDir() {
        super("Directory tree analyzer",
                "Analyzes directory trees and directory contents", false, true);
        
        this.biggestDir = null;
        this.biggestDirSubFilesNum = 0;
        this.averageSubFilesNum = new AverageVal();
        this.biggestTree = null;
        this.biggestTreeSize = 0;
    }
    
    /**
     * Adds new directory data.
     * 
     * @param dir specified directory
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File dir) {
        if (this.addDirSizeData(dir)) {
            return this.addBiggestTreeData(dir);
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
        AnalysisStat stat = new AnalysisStat("Directory information");
        
        int subFilesNum = this.getAverageSubFilesNum();
        stat.add("Average subfiles number", subFilesNum);
        
        String dirPath = this.getPathBiggestDir();
        stat.add("Biggest dir. (path)", dirPath);
        
        subFilesNum = this.getBiggestDirSubFilesNum();
        stat.add("Biggest dir. (subfiles number)", subFilesNum);
        
        String treePath = this.getBiggestTree();
        stat.add("Biggest dir. tree (path)", treePath);
        
        int treeSize = this.getBiggestTreeSize();
        stat.add("Biggest dir. tree (size)", treeSize);
        
        return stat;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        this.biggestDir = null;
        this.averageSubFilesNum.clear();
        this.biggestTree = null;
    }
    
    /**
     * Getter.
     * 
     * @return path to the directory with the greatest number
     * of subfiles
     */
    public String getPathBiggestDir() {
        return (this.biggestDir == null) ? null : this.biggestDir.getPath();
    }
    
    /**
     * Getter.
     * 
     * @return number of subfiles of the biggest directory
     */
    public int getBiggestDirSubFilesNum() {
        return (this.biggestDir == null) ? 0 :
                this.biggestDir.listFiles().length;
    }
    
    /**
     * Getter.
     * 
     * @return path to the biggest directory tree
     */
    public String getBiggestTree() {
        return (this.biggestTree == null) ? null : this.biggestTree.getPath();
    }
    
    /**
     * Getter.
     * 
     * @return number of directory tree levels of the biggest
     * directory tree
     */
    public int getBiggestTreeSize() {
        return (this.biggestTree == null) ? 0 :
                this.countDirTreeSize(this.biggestTree);
    }
    
    /**
     * Getter.
     * 
     * @return average number of files in a directory
     */
    public int getAverageSubFilesNum() {
        return (int) this.averageSubFilesNum.getAverageValLong();
    }
    
    /*
     * Modifies the attributes according to the number of files within a 
     * directory. Detection of the biggest directory together with modifying of 
     * the average number of sub-files is done here. 
     */
    private boolean addDirSizeData(File dir) {
        File[] subFilesList = dir.listFiles();
        
        if (subFilesList != null) {
            if (this.biggestDir != null) {
                if (subFilesList.length > this.biggestDirSubFilesNum) {
                    this.biggestDir = dir;
                    this.biggestDirSubFilesNum = subFilesList.length;
                }
            } else {
                this.biggestDir = dir;
                this.biggestDirSubFilesNum = subFilesList.length;
            }
            
            this.averageSubFilesNum.addVal(subFilesList.length);
            
            return true;
        }
        
        return false;
    }
    
    /* Detection of the biggest directory tree. */
    private boolean addBiggestTreeData(File dir) {
        // TODO Maybe I will have to do some checking regarding directories
        if (this.biggestTree != null) {
            int dirTreeSize = this.countDirTreeSize(dir);
            
            if (dirTreeSize > this.biggestTreeSize) {
                this.biggestTree = dir;
                this.biggestTreeSize = dirTreeSize;
            }
        } else {
            this.biggestTree = dir;
            this.biggestTreeSize = this.countDirTreeSize(dir);
        }
        
        return true;
    }
    
    /* Counts the number of directory tree levels of the specified directory. */
    private int countDirTreeSize(File dir) {
        String path = dir.getPath();

        int count = 1;
        for (int i = 0; i < path.length(); i++) {
            if (this.isDirSeparator(path.charAt(i))) {
                count++;
            }
        }

        return count;
    }
    
    /* 
     * Checks whether the character is directory path spearator or not
     * (OS independent).
     */
    private boolean isDirSeparator(char sep) {
        return sep == File.separatorChar;
    }
}
