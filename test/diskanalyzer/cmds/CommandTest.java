package diskanalyzer.cmds;

import org.junit.Test;

/**
 * Test of "Command" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-28
 */
public class CommandTest {
    /**
     * Test of execute method, of class Command.
     * 
     * @throws java.lang.Exception
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCommandConstructor() throws Exception {
        System.out.println("commandConstructor");
        
        Command cmd = new Command(null);
    }
}
