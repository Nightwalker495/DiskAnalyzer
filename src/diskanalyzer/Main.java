package diskanalyzer;

/**
 * Program entry-point.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class Main {
    /**
     * Program <b>ENTRY-POINT</b>.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        DiskAnalyzer diskAnalyzer = DiskAnalyzer.getInstance();
        
        diskAnalyzer.run();
    }
    
    /* To suppress instance creation. */
    private Main() {
    }
    
    /* A simple to-do list */
    
    // TODO Develop tests for all classes where it is possible
    // TODO Add exceptions where appropriate
}
