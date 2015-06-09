package diskanalyzer.cmds.executers;

import diskanalyzer.DAEngine;

/**
 * Command which initializes the engine analysis process based on the parameters
 * provided. This command could be regarded as the main one in terms of program
 * functionality.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-17
 */
public class CmdExecAnalyze implements ICmdExecuter {
    /**
     * Executes the command without parameters.
     * 
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException this exception is thrown immediately
     * because this command requires at least one parameter
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {
        String msg = "missing command parameters, at least one needed";
        
        throw new InvalidCmdParamException(msg);
    }

    /**
     * Executes the command with parameters. Runs the disk analysis with
     * specified options.
     * 
     * @param params command parameters
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown in case of invalid parameter 
     * format of number of parameters provided
     */
    @Override
    public boolean execute(String[] params)
            throws InvalidCmdParamException {
        if (params == null) {
            throw new IllegalArgumentException("params cannot be null");
        } else {
            if (params.length == 0) {
                String msg = "not enough parameters, at least one needed";

                throw new InvalidCmdParamException(msg);
            } else {
                // TODO Detect invalid path parameter
                System.out.println("The analysis has started, please wait...");

                for (String path : params) {
                    this.processDirectory(path);
                }

                return true;
            }
        }
    }

    /**
     * Generates a command usage.
     * 
     * @return command usage as string
     */
    @Override
    public String help() {
        String text = "Initializes the entire analysis process based on the\n";
        text += "parameters provided in the following format:\n";
        text += "    <name> <path> .... \n";
        text += "name           name of the command\n";
        text += "path           starting path for the analysis (C:\\...)\n";
        text += "Note: '...' indicates that the number of paths provided can\n";
        text += "be of variable length";
        
        return text;
    }
    
    /* Runs the analysis from the specified path. */
    private void processDirectory(String path) {
        DAEngine engine = DAEngine.getInstance();
        ExecStopwatch stopwatch = new ExecStopwatch();
        
        System.out.println("Starting directory: '" + path + "'.");
        
        stopwatch.start();
        boolean ret = engine.runAnalysis(path);
        stopwatch.stop();
        
        if (ret) {
            System.out.println("Time taken: " + stopwatch);
        } else {
            System.err.println("An error has occured during the analysis");
        }
    }
}
