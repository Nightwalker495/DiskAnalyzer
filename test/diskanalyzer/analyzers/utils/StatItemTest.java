package diskanalyzer.analyzers.utils;

import diskanalyzer.analyzers.StatItem;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of StatItem class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-27
 */
public class StatItemTest {
    /**
     * Test of getDescription method, of class StatItem.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        
        String description = "description";
        StatItem instance = new StatItem(description, null);
        assertEquals(instance.getDescription(), description);
    }

    /**
     * Test of getValue method, of class StatItem.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        
        String value = "tmpValue";
        StatItem instance = new StatItem("tmp", value);
        assertEquals(instance.getValue(), value);
    }
}
