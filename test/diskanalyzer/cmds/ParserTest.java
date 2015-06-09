package diskanalyzer.cmds;

import org.junit.Test;

/**
 * Test of "Parser" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class ParserTest {
    @Test (expected = IllegalArgumentException.class)
    public void testConstructor() {
        System.out.println("parserConstructor");
        
        Parser parser = new Parser(null);
    }
}
