package diskanalyzer.cmds.executers;

import diskanalyzer.DAEngine;

/**
 * Changes mode specification for the analyzer engine. 
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-18
 */
public class CmdExecSet implements ICmdExecuter {
    /**
     * Executes the command without parameters.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown immediately because this command
     * requires exactly two parameters
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        String msg = "not enough parameters, exactly two expected";
        
        throw new InvalidCmdParamException(msg);
    }

    /**
     * Changes the mode settings for the analyzer engine. First parameter
     * indicates one of the following values: recursive, case-sensitive. Each
     * of those values represents mode settings for the analyzer the value of
     * which is set to the one provided as second parameter.
     * 
     * @param params command parameters
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown in case of not exactly two
     * parameters provided
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        if (params.length == 2) {
            if (!this.isBoolStrValid(params[1])) {
                String msg = "parameter doesn't represent a boolean value";
                
                throw new InvalidCmdParamException(msg);
            }
            
            String modeName = params[0].toLowerCase();
            boolean value = Boolean.parseBoolean(params[1]);
            
            return this.changeMode(modeName, value);
        } else {
            String msg = "invalid parameters, exactly two expected";
            
            throw new InvalidCmdParamException(msg);
        }
    }

    /**
     * Generates a command usage.
     * 
     * @return command usage as string
     */
    @Override
    public String help() {
        String text = "Changes mode at which the analyzer engine operates.\n";
        text += "This command can be used in the following format:\n";
        text += "    <name> <mode> <value>\n";
        text += "name  name of this command\n";
        text += "mode  mode of the engine to change, it must have a value of\n";
        text += "      one from the list below:\n";
        text += "          recursive (recursive directory walking)\n";
        text += "          case-sensitive (case-sensitivity for names)\n";
        text += "value true/false indicator for the state of the mode\n";
        
        return text;
    }
    
    /* Checks whether the string represents a boolean value. */
    private boolean isBoolStrValid(String boolValue) {
        boolean eqTrue = boolValue.equalsIgnoreCase("true");
        boolean eqFalse = boolValue.equalsIgnoreCase("false");
        
        return eqTrue || eqFalse;
    }
    
    /* Changes the state of a specified mode. */
    private boolean changeMode(String modeName, boolean value) {
        DAEngine engine = DAEngine.getInstance();
            
        switch (modeName) {
            case "recursive":
                engine.setRecurse(value);
                return true;
            case "case-sensitive":
                // TODO Implement changing case-sensitive mode value
                // Fall through
                return true;
            default:
        }
        
        return false;
    }
}
