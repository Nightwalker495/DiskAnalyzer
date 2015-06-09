package diskanalyzer.analyzers;

/**
 * Enum which contains all categories representing file data type.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
enum FileDataCategory {
    MUSIC("music"),
    VIDEOS("videos"),
    DOCUMENTS("documents"),
    PICTURES("pictures"),
    OTHER("other");

    private final String name;
    
    /**
     * Constructor.
     * 
     * @param name name of the category
     */
    FileDataCategory(String name) {
        this.name = name;
    }
    
    /**
     * Array of all category names converted to Strings.
     * 
     * @return array of strings
     */
    public static String[] getAllCategories() {
        FileDataCategory[] vals = FileDataCategory.values();
        String[] list = new String[vals.length];
        
        for (int i = 0; i < list.length; i++) {
            list[i] = vals[i].toString();
        }
        
        return list;
    }
    
    /**
     * Getter.
     * 
     * @return name of the particular enum. instance
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * String representation of the object.
     * 
     * @return name of the particular enum. instance
     */
    @Override
    public String toString() {
        return this.name;
    }
}
