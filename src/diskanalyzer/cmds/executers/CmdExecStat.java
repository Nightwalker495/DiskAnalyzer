package diskanalyzer.cmds.executers;

import diskanalyzer.DAEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Command which prints the statistical information from the analysis process.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-18
 */
public class CmdExecStat implements ICmdExecuter {
    private final DAEngine engine;
    
    /**
     * Default constructor.
     */
    public CmdExecStat() {
        this.engine = DAEngine.getInstance();
    }
    
    /**
     * Prints the statistical information from the analysis.
     * 
     * @return true
     * @throws InvalidCmdParamException never thrown
     */
    @Override
    public boolean execute() throws InvalidCmdParamException {        
        System.out.println("Statistical information from the analysis.");
        System.out.println(this.engine.generateOutput());
        
        return true;
    }

    /**
     * Executes the command with parameters.
     * 
     * @param params command parameters
     * @return true on success, false otherwise
     * @throws InvalidCmdParamException thrown immediately because this command
     * does not expect any parameter
     */
    @Override
    public boolean execute(String[] params) throws InvalidCmdParamException {
        if (params.length == 1) {
            try {
                this.outputToFile(params[0], this.engine.generateOutput());
                System.out.format("output has been written to the file '%s'\n",
                        params[0]);
                
                return true;
            } catch (FileNotFoundException ex) {
                return false;
            }
        } else {
            String msg = "in case parameters are provided, only one expected";
            
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
        String text = "Prints statistical information about the analysis.\n";
        text += "In case there is a parameter provided, it will indicate\n";
        text += "a file path to which the information will be written.";
        
        return text;
    }
    
    /*
     * Prints string to the output file.
     */
    private void outputToFile(String fileName, String output) 
        throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(fileName));
        
        writer.println(output);
        writer.close();;
    }
}
