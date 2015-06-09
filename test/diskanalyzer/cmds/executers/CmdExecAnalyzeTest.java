package diskanalyzer.cmds.executers;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test of "CmdExecAnalyze" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class CmdExecAnalyzeTest {
    private CmdExecAnalyze inst;

    @Before
    public void setUp() throws Exception {
        this.inst = new CmdExecAnalyze();
    }
    
    /**
     * Test of execute method, of class CmdExecAnalyze.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = InvalidCmdParamException.class)
    public void testExecute_0args() throws InvalidCmdParamException {
        System.out.println("execute");
        
        this.inst.execute();
    }

    /**
     * Test of execute method, of class CmdExecAnalyze.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = IllegalArgumentException.class)
    public void testExecute_StringArr1() throws InvalidCmdParamException {
        System.out.println("execute");
        
        this.inst.execute(null);
    }

    /**
     * Test of execute method, of class CmdExecAnalyze.
     * 
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    @Test (expected = InvalidCmdParamException.class)
    public void testExecute_StringArr2() throws InvalidCmdParamException {
        System.out.println("execute");
        
        this.inst.execute(new String[] {});
    }
    
    /**
     * Test of help method, of class CmdExecAnalyze.
     */
    @Test
    public void testHelp() {
        System.out.println("help");
        
        String help = this.inst.help();
        assertNotNull(help);
        assertFalse(help.isEmpty());
    }
}
