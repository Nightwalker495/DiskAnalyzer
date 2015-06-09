package diskanalyzer.cmds.executers;

import diskanalyzer.DiskAnalyzer;

/**
 * A command which terminates the entire application.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-18
 */
public class CmdExecExit implements ICmdExecuter {
    /**
     * Terminates the entire application immediately.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException never thrown
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        DiskAnalyzer.getInstance().quit();
        
        return true;
    }

    /**
     * Runs the command with parameters.
     * 
     * @param params command parameters
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown immediately because this command
     * does not require any additional parameters
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        String msg = "too much parameters, none expected";
        
        throw new InvalidCmdParamException(msg);
    }

    /**
     * Generates a command usage.
     * 
     * @return command usage as string
     */
    @Override
    public String help() {
        return "Terminates the entire application without asking anything.";
    }
}
