package diskanalyzer.analyzers.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of MutableInteger class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-27
 */
public class MutableIntegerTest {
    /**
     * Test of getValue method, of class MutableInteger.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        
        MutableInteger instance = new MutableInteger();
        assertEquals(instance.getValue(), 0);
    }

    /**
     * Test of inc method, of class MutableInteger.
     */
    @Test
    public void testInc() {
        System.out.println("increment");
        
        MutableInteger instance = new MutableInteger(10);
        instance.inc();
        assertEquals(instance.getValue(), 11);
    }

    /**
     * Test of dec method, of class MutableInteger.
     */
    @Test
    public void testDec() {
        System.out.println("decrement");
        
        MutableInteger instance = new MutableInteger(10);
        instance.dec();
        assertEquals(instance.getValue(), 9);
    }

    /**
     * Test of toString method, of class MutableInteger.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        MutableInteger instance = new MutableInteger(20);
        assertEquals(instance.toString(), "20");
    }
}
