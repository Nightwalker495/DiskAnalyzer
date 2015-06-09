package diskanalyzer;

import diskanalyzer.cmds.Command;
import diskanalyzer.cmds.executers.InvalidCmdParamException;
import diskanalyzer.cmds.Parser;

/**
 * Main class of the <b>Disk Analyzer</b> project. It manages only the object
 * which serves the purpose of an engine. This object organizes the whole
 * application.
 * 
 * @author Milan Ondrasovic
 * @version 2014-12-27
 * @author Milan Ondrasovic
 * @version 2015-04-12
 */
public class DiskAnalyzer {
    private static final String CMD_INDICATOR = "command >>> ";
    private static DiskAnalyzer instance = null;
    
    private final Parser parser;
    
    /**
     * Returns a new DiskAnalyzer object.
     * 
     * @return instance of the DiskAnalyzer class (always the same one)
     */
    public static DiskAnalyzer getInstance() {
        if (instance == null) {
            instance = new DiskAnalyzer();
        }
        
        return instance;
    }
    
    /* Default constructor. */
    private DiskAnalyzer() {
        this.parser = new Parser(System.in);
    }
    
    /**
     * Program main loop which coordinates all the underlying components.
     */
    public void run() {
        System.out.println(AppInfo.getAppInfo());
        System.out.println();
        
        while (true) {
            System.out.print(CMD_INDICATOR);
            Command cmd = this.parser.parseCmd();
            
            if (cmd == null) {
                System.err.println("invalid command entered");
            } else {
                try {
                    boolean res = cmd.execute();

                    if (!res) {
                        System.err.println("command execution failure");
                    }
                } catch (InvalidCmdParamException ex) {
                    System.err.println("error: " + ex.getMessage());
                }
            }
        }
    }
    
    /**
     * Terminates the entire application while paying attention to the opened
     * resources, if needed.
     */
    public void quit() {
        System.exit(0);
    }
}
