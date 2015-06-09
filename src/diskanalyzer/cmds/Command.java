package diskanalyzer.cmds;

import diskanalyzer.cmds.executers.InvalidCmdParamException;
import diskanalyzer.cmds.executers.ICmdExecuter;

/**
 * Class which holds basic information about command. Immutable object.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-12
 */
public class Command {
    private final ICmdExecuter executer;
    private final String[] params;
    
    /**
     * Creates a new instance with no parameters specified.
     * 
     * @param executer command executer
     */
    public Command(ICmdExecuter executer) {
        this(executer, null);
    }
    
    /**
     * Creates a new instance with ability to modify all available options.
     * 
     * @param executer executer of the command
     * @param params parameters of the command
     */
    public Command(ICmdExecuter executer, String[] params) {
        if (executer == null) {
            String msg = "command executer cannot be null";
            
            throw new IllegalArgumentException(msg);
        } else {
            this.executer = executer;
            this.params = params;
        }
    }
    
    /**
     * Executes the command with specifies options.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown in case of invalid parameters
     * provided
     */
    public boolean execute() throws InvalidCmdParamException {
        if (this.params == null) {
            return this.executer.execute();
        } else {
            return this.executer.execute(this.params);
        }
    }
    
    /**
     * Generates a usage for command.
     * 
     * @return usage as string
     */
    public String help() {
        return this.executer.help();
    }
}
