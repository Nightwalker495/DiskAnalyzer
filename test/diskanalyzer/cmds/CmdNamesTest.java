package diskanalyzer.cmds;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of "CmdNames" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class CmdNamesTest {
    /**
     * Test of getInstance method, of class CmdNames.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        CmdNames inst1 = CmdNames.getInstance();
        CmdNames inst2 = CmdNames.getInstance();
        assertEquals(inst1, inst2);
    }

    /**
     * Test of getCmdNames method, of class CmdNames.
     */
    @Test
    public void testGetCmdNames() {
        System.out.println("getCmdNames");
        
        CmdNames instance = CmdNames.getInstance();
        String result = instance.getCmdNames();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getCommand method, of class CmdNames.
     */
    @Test
    public void testGetCommand_String() {
        System.out.println("getCommand");
        
        CmdNames instance = CmdNames.getInstance();
        Command result = instance.getCommand("");
        assertNull(result);
        result = instance.getCommand(null);
        assertNull(result);
        result = instance.getCommand("non-existent command");
        assertNull(result);
    }

    /**
     * Test of getCommand method, of class CmdNames.
     */
    @Test
    public void testGetCommand_String_StringArr() {
        System.out.println("getCommand");
        
        CmdNames instance = CmdNames.getInstance();
        Command result = instance.getCommand("", null);
        assertNull(result);
        result = instance.getCommand(null, new String[] { "tmp" });
        assertNull(result);
        result = instance.getCommand("non-existent command", null);
        assertNull(result);
    }
}
