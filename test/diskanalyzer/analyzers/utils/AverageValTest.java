package diskanalyzer.analyzers.utils;

import diskanalyzer.analyzers.utils.AverageVal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for AverageVal class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-03-13
 */
public class AverageValTest {
    /**
     * Test of getAverageValLong method, of class AverageVal.
     */
    @Test
    public void testGetAverageValLong() {
        System.out.println("getAverageValLong");
        
        AverageVal instance = new AverageVal();
        for (long val = 1L; val <= 5L; val++) {
            instance.addVal(val);
        }
        
        assertEquals(3L, instance.getAverageValLong());
    }

    /**
     * Test of getAverageValDouble method, of class AverageVal.
     */
    @Test
    public void testGetAverageValDouble() {
        System.out.println("getAverageValDouble");
        
        AverageVal instance = new AverageVal();
        for (long val = 1L; val <= 5L; val++) {
            instance.addVal(val);
        }
        
        assertEquals(3.0, instance.getAverageValDouble(), 0.0);
    }

    /**
     * Test of clear method, of class AverageVal.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        
        AverageVal instance = new AverageVal();
        instance.addVal(1L);
        instance.addVal(125L);
        instance.clear();
        
        assertEquals(0L, instance.getTotalVal());
    }

    /**
     * Test of getTotalVal method, of class AverageVal.
     */
    @Test
    public void testGetTotalVal() {
        System.out.println("getTotalVal");
        
        AverageVal instance = new AverageVal();
        instance.addVal(1L);
        instance.addVal(2L);
        instance.addVal(4L);
        
        assertEquals(7L, instance.getTotalVal());
    }

    /**
     * Test of getValsCount method, of class AverageVal.
     */
    @Test
    public void testGetValsCount() {
        System.out.println("getValsCount");
        
        AverageVal instance = new AverageVal();
        instance.addVal(1L);
        instance.addVal(2L);
        
        assertEquals(2, instance.getValsCount());
    }
    
}
