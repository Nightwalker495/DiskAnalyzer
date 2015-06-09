package diskanalyzer.cmds.executers;

import diskanalyzer.cmds.Command;
import diskanalyzer.cmds.Parser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Command which runs a disk-analyzer-script (*.das) file.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-24
 */
public class CmdExecRun implements ICmdExecuter {
    // To prevent calling more than one script at a time
    private boolean executionRunning;
    
    public CmdExecRun() {
        this.executionRunning = false;
    }
    
    /**
     * Runs the command without parameters.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException this command does require at least
     * one parameter, thus this exception is thrown immediately
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        throw new InvalidCmdParamException("not enough parameters");
    }
    
    /**
     * Runs the script file (*.das) with the specified format.
     * 
     * @param params command parameters (script file paths)
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException command requires at least one parameter
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        if (!this.executionRunning) {
            if (params.length == 0) {
                throw new InvalidCmdParamException("not enough parameters");
            } else {
                this.executionRunning = true;
                
                boolean ret = true;
                
                for (String scriptPath : params) {
                    try {
                        InputStream stream = new FileInputStream(scriptPath);

                        System.out.println("executing file: " + scriptPath);
                        if (!this.executeScriptFile(stream)) {
                            ret = false;
                        }
                    } catch (FileNotFoundException ex) {
                        System.err.format("could not open file: %s",
                                ex.getMessage());
                        System.err.println();
                    }
                }

                this.executionRunning = false;
                
                return ret;
            }
        }
        
        return false;
    }

    /**
     * Generates a command usage.
     * 
     * @return command usage as string
     */
    @Override
    public String help() {
        String text = "Executes a disk-analyzer-script (*.das) file.\n";
        text += "Command reuires the following format for the execution:\n";
        text += "    <name> <path> ...";
        text += "name name of the command\n";
        text += "path path of the script (can be more than one)\n";
        text += "The file should have a specific format obeying the\n";
        text += "following rules:\n";
        text += "   one command on one line\n";
        text += "   comments start with '#' at the beginning of the line\n";
        
        return text;
    }
    
    /* Executes the entire script file line by line. */
    private boolean executeScriptFile(InputStream stream) {
        Parser parser = new Parser(stream);
        
        while (parser.hasNextCmd()) {
            if (!this.executeLine(parser.parseCmd())) {
                String cmdStr = parser.getLastCmd();
                System.err.format("could not execute command '%s'\n", cmdStr);
                System.err.println("terminating script file execution...");
                
                return false;
            }
        }
        
        return true;
    }
    
    /* Executes one line in the script file (one command) */
    private boolean executeLine(Command cmd) {
        if (cmd != null) {
            try {
                return cmd.execute();
            } catch (InvalidCmdParamException ex) {
                System.err.println("error occured when processing script file");
                System.out.println(ex.getMessage());
            }
        }
        
        return false;
    }
}
