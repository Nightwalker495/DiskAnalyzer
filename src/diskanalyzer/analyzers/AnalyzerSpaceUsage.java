package diskanalyzer.analyzers;

import diskanalyzer.analyzers.utils.MutableLong;
import java.io.File;
import java.util.HashMap;


/**
 * Analyzes space usage of files based on the data type
 * (documents, music, videos). Type of the file is determined according to the
 * file extension. Class contains a list of extensions with their meaning. 
 * All extensions which do not fall to any category will figure in the final
 * list as "other".
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-28
 */
public class AnalyzerSpaceUsage extends Analyzer {
    private final HashMap<FileDataCategory, MutableLong> categoriesData;
    private final DataCategoryDetector detector;
    private long overallSize;
    
    /**
     * Default constructor.
     */
    public AnalyzerSpaceUsage() {
        super("Space usage analyzer",
                "Analyzes space usage of files based on the file type", true,
                false);
        
        this.categoriesData = new HashMap<>();
        this.detector = new DataCategoryDetector();
        this.overallSize = 0L;
        
        // All categories can be assigned since it will not change
        FileDataCategory[] categories = FileDataCategory.values();
        for (FileDataCategory category : categories) {
            // Default values are zero
            this.categoriesData.put(category, new MutableLong(0L));
        }
    }
    
    /**
     * Analyzes file type and adds the information to the statistics.
     * 
     * @param data file to add
     * @return true on success, false otherwise
     */
    @Override
    public boolean addData(File data) {
        FileDataCategory category = this.detectFileCategory(data);

        if (category != null) {
            long size = data.length();
            MutableLong usage = this.categoriesData.get(category);
            usage.addValue(size);

            this.overallSize += size;

            return true;
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
        AnalysisStat stat = new AnalysisStat("File type usage");
        
        for (FileDataCategory category : this.categoriesData.keySet()) {
            String description = category.toString() + " category";
            MutableLong usage = this.categoriesData.get(category);
            double percentUse = this.calcPercentUse(usage.getValue(),
                    this.overallSize);
            
            stat.add(description, String.format("%.3f%%", percentUse));
        }
        
        return stat;
    }

    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    @Override
    public void clear() {
        for (FileDataCategory category : this.categoriesData.keySet()) {
            MutableLong usage = this.categoriesData.get(category);
            usage.setValue(0L);
        }
        
        this.overallSize = 0L;
    }
    
    /* Detects a category of the file (music, ...) based on its extension */
    private FileDataCategory detectFileCategory(File file) {
        String ext = this.extractExt(file.getName());
        
        return (ext == null) ? null : this.detector.detectExtDataCategory(ext);
    }
    
    /*
     * Extracts the ext from the specified file. It searches for the last
     * occurrence of '.' character as indicator of the file ext. The extension
     * starts at first character which is not a '.'.
     */
    private String extractExt(String fileName) {
        int index = fileName.lastIndexOf('.');
        
        return (index >= 0) ? fileName.substring(index + 1) : null;
    }
    
    /* Calculates the percentage of "usage" in "overall". */
    private double calcPercentUse(long usage, long overall) {
        if (overall == 0L) {
            return 0.0;
        } else {
            return ((double) usage / (double) overall) * 100.0;
        }
    }
}
