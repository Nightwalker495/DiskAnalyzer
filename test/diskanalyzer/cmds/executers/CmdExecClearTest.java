package diskanalyzer.cmds.executers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Milan Ondrasovic
 * @version 2015-05-08
 */
public class CmdExecClearTest {
    private CmdExecClear inst;
    
    @Before
    public void setUp() {
        this.inst = new CmdExecClear();
    }

    /**
     * Test of execute method, of class CmdExecClear.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = InvalidCmdParamException.class)
    public void testExecute_StringArr1() throws InvalidCmdParamException {
        System.out.println("execute");
        
        String[] params = new String[] { "tmpA", "tmpB" };
        boolean result = this.inst.execute(params);
    }
    
    /**
     * Test of execute method, of class CmdExecClear.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = InvalidCmdParamException.class)
    public void testExecute_StringArr2() throws InvalidCmdParamException {
        System.out.println("execute");
        
        boolean result = this.inst.execute(null);
    }
    
    /**
     * Test of help method, of class CmdExecClear.
     */
    @Test
    public void testHelp() {
        System.out.println("help");
        
        String help = this.inst.help();
        assertNotNull(help);
        assertFalse(help.isEmpty());
    }
}
