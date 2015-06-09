package diskanalyzer.cmds;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Command line parser. Handles user input and extracts commands and parameters
 * from the stream in a specified format.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-17
 */
public class Parser {
    private final Scanner scan;
    private final CmdNames cmdNames;
    private int processedCmdsNum;
    private String lastCmd;
    
    /**
     * Default constructor.
     * 
     * @param stream stream to read from
     */
    public Parser(InputStream stream) {
        if (stream != null) {
            this.processedCmdsNum = 0;
            this.scan = new Scanner(stream);
            this.cmdNames = CmdNames.getInstance();
            this.lastCmd = "";
        } else {
            throw new IllegalArgumentException("stream cannot be null");
        }
    }
    
    /**
     * Checks whether there is at least one command in the queue.
     * 
     * @return true if there is at least one, false otherwise
     */
    public boolean hasNextCmd() {
        return this.scan.hasNextLine();
    }
    
    /**
     * Getter.
     * 
     * @return overall number of processed commands
     */
    public int getProcessedCmdsNum() {
        return this.processedCmdsNum;
    }
    
    /**
     * Getter.
     * 
     * @return last command processed (string representation with all parameters
     * including name of the command)
     */
    public String getLastCmd() {
        return this.lastCmd;
    }
    
    /**
     * Parses the command from the user input.
     * 
     * @return instance of a newly created command, or null in case of invalid
     * command provided
     */
    public Command parseCmd() {
        if (this.scan.hasNextLine()) {
            String line = this.scan.nextLine().trim();
            String[] tokens = line.split("\\s+"); // Split at any white-space
            
            if (tokens.length > 0) {
                String cmdName = tokens[0];
                
                this.processedCmdsNum++;
                this.lastCmd = line;
                
                if (tokens.length > 1) {
                    String[] params = this.processParams(tokens);
                    
                    return this.cmdNames.getCommand(cmdName, params);
                } else {
                    return this.cmdNames.getCommand(cmdName);
                }
            }
        }
        
        return null;
    }

    /*
     * Extracts all the parameters from the line string and provides them 
     * listed in an array. Each parameter is stripped of white-spaces.
     */
    private String[] processParams(String[] tokens) {
        String[] params = new String[tokens.length - 1];
        
        for (int i = 1; i < tokens.length; i++) { // First token is ommited
            params[i - 1] = tokens[i];
        }
        
        return params;
    }
}
