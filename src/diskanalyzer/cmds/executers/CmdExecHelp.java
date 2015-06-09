package diskanalyzer.cmds.executers;

import diskanalyzer.cmds.CmdNames;
import diskanalyzer.cmds.Command;

/**
 * Command which provides usages for other commands.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-18
 */
public class CmdExecHelp implements ICmdExecuter {
    // NOTE I wanted to use a private attribute with CmdNames instance, but
    // it recursively calls itself until the stack overflows, because a
    // singleton design pattern is used there.
    
    /**
     * Prints the list of all available commands.
     * 
     * @return true
     * @throws InvalidCmdParamException never thrown
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        String cmds = CmdNames.getInstance().getCmdNames(); // List of cmds
        
        System.out.println("List of all available commands:");
        System.out.println(cmds);
        
        return true;
    }

    /**
     * Prints the usage of a command provided as parameter.
     * 
     * @param params command parameter
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown in case of zero or too many
     * parameters
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        if (params.length == 1) {
            String name = params[0];
            Command cmd = CmdNames.getInstance().getCommand(name);
            
            if (cmd == null) {
                System.out.println("No usage for '" + name + "'.");
                
                return false;
            } else {
                System.out.format("Usage for the command '" + name + "'.");
                System.out.println();
                System.out.println(cmd.help());
                
                return true;
            }
        } else {
            String msg = "invalid number of parameters, only one expected";
            
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
        String text = "Provides help for other commands.\n";
        text += "In case of no parameter provided, all available command are\n";
        text += "listed.";
        text += "In case that the following format is obeyed:\n";
        text += "    <name> <command>\n";
        text += "name    command name\n";
        text += "command command to show the usage of\n";
        
        return text;
    }
}
