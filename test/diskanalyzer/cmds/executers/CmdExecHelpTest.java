package diskanalyzer.cmds.executers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Milan Ondrasovic
 * @version 2015-05-08
 */
public class CmdExecHelpTest {
    private CmdExecHelp inst;
    
    @Before
    public void setUp() {
        this.inst = new CmdExecHelp();
    }

    /**
     * Test of execute method, of class CmdExecHelp.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = NullPointerException.class)
    public void testExecute_StringArr() throws InvalidCmdParamException {
        System.out.println("execute");
        
        this.inst.execute(null);
    }

    /**
     * Test of help method, of class CmdExecHelp.
     */
    @Test
    public void testHelp() {
        System.out.println("help");
        
        String help = this.inst.help();
        assertNotNull(help);
        assertFalse(help.isEmpty());
    }
}
