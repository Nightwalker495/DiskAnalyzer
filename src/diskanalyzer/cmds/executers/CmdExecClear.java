package diskanalyzer.cmds.executers;

import diskanalyzer.DAEngine;

/**
 * Command which clears the entire statistics and resets the engine.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-18
 */
public class CmdExecClear implements ICmdExecuter {
    /**
     * Executes the command without parameters, which is the only valid option
     * at this point.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException this exception shouldn't be risen here
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        DAEngine engine = DAEngine.getInstance();
        
        engine.clear();
        
        return true;
    }

    /**
     * Invalid execution of the command with parameters.
     * 
     * @param params parameters of the command
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException rises this exception immediately because
     * this command does not expect any parameter
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        String msg = "too many parameters, none expected";
        
        throw new InvalidCmdParamException(msg);
    }

    /**
     * Generates a command usage.
     * 
     * @return command usage as string
     */
    @Override
    public String help() {
        String text = "Clears the entire statistics, resets the engine to \n";
        text += "the default state at the beginning.";
        
        return text;
    }
}
