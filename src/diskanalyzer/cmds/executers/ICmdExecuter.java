package diskanalyzer.cmds.executers;

/**
 * Command executer interface.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-12
 */
public interface ICmdExecuter {
    /**
     * Executes command with no parameters.
     * 
     * @return ture in case of successful execution, false otherwise
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    boolean execute() throws InvalidCmdParamException;
    
    /**
     * Executes command with parameters.
     * 
     * @param params array of parameters 
     * @return ture in case of successful execution, false otherwise
     * @throws diskanalyzer.cmds.InvalidCmdParamException
     */
    boolean execute(String[] params) throws InvalidCmdParamException;
    
    /**
     * Provides an usage for the particular command.
     * 
     * @return string representation of the help text
     */
    String help();
}
