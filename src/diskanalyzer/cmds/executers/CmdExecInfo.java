package diskanalyzer.cmds.executers;

import diskanalyzer.AppInfo;

/**
 * Command which prints general information about the project.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-28
 */
public class CmdExecInfo implements ICmdExecuter {
    /**
     * Executes the command without parameters. Print information about the
     * program.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException 
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        System.out.println(AppInfo.getAppInfo());
        
        return true;
    }

    /**
     * Executes the command with parameters.
     * 
     * @param params command parameters
     * @return no return
     * @throws InvalidCmdParamException no parameter is expected
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
        return "Prints basic information about the program.";
    }
}
