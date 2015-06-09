package diskanalyzer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for DAEngine class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-27
 */
public class DAEngineTest {
    /**
     * Test of getInstance method, of class DAEngine.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        DAEngine result = DAEngine.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of setRecurse method, of class DAEngine.
     */
    @Test
    public void testSetRecurse() {
        System.out.println("setRecurse");
        
        boolean mode = true;
        DAEngine instance = DAEngine.getInstance();
        
        instance.setRecurse(mode);
        assertEquals(instance.getRecurse(), mode);
    }

    /**
     * Test of runAnalysis method, of class DAEngine.
     */
    @Test
    public void testRunAnalysis() {
        System.out.println("runAnalysis");
        
        DAEngine instance = DAEngine.getInstance();
        boolean result = instance.runAnalysis("");
        assertFalse(result);
        
        result = instance.runAnalysis(null);
        assertFalse(result);
        
        result = instance.runAnalysis("nonExistingFile");
        assertFalse(result);
    }    
}
