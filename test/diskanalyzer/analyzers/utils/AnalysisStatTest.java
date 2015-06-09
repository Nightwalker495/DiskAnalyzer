package diskanalyzer.analyzers.utils;

import diskanalyzer.analyzers.AnalysisStat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of "AnalysisStat" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class AnalysisStatTest {
    /**
     * Test of getName method, of class AnalysisStat.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String name = "tmpName";
        AnalysisStat instance = new AnalysisStat(name);
        assertEquals(instance.getName(), name);
    }

    /**
     * Test of iterator method, of class AnalysisStat.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        
        AnalysisStat instance = new AnalysisStat("tmp");
        assertNotNull(instance.iterator());
    }
}
