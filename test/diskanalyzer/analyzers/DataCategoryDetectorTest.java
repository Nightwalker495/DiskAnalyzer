package diskanalyzer.analyzers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of "DataCategoryDetector" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class DataCategoryDetectorTest {
    private DataCategoryDetector mainInst;
    
    @Before
    public void setUp() {
        this.mainInst = new DataCategoryDetector();
    }
    
    @After
    public void tearDown() {
        this.mainInst = null;
    }
    
    /**
     * Test of detectExtDataCategory method, of class DataCategoryDetector.
     */
    @Test
    public void testDetectExtDataCategory() {
        System.out.println("detectExtDataCategory");
        
        DataCategoryDetector instance = new DataCategoryDetector();
        
        assertEquals(instance.detectExtDataCategory(null), null);
        assertEquals(instance.detectExtDataCategory(""), null);
        
        assertEquals(instance.detectExtDataCategory("JpEg "),
                FileDataCategory.PICTURES);
        assertEquals(instance.detectExtDataCategory("avi"),
                FileDataCategory.VIDEOS);
        assertEquals(instance.detectExtDataCategory("  MP3  "),
                FileDataCategory.MUSIC);
        assertEquals(instance.detectExtDataCategory("pDF"),
                FileDataCategory.DOCUMENTS);
        assertEquals(instance.detectExtDataCategory("Idontknow"),
                FileDataCategory.OTHER);
    }
    
    /**
     * Test of getCategoriesList method, of class DataCategoryDetector.
     */
    @Test
    public void testGetCategoriesList() {
        System.out.println("getCategoriesList");
        
        DataCategoryDetector instance = new DataCategoryDetector();
        String[] expResult = FileDataCategory.getAllCategories();
        String[] result = instance.getCategoriesList();
        assertArrayEquals(expResult, result);
    }
}
