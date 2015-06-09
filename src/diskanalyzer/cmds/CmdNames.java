package diskanalyzer.cmds;

import diskanalyzer.cmds.executers.ICmdExecuter;
import diskanalyzer.cmds.executers.CmdExecAnalyze;
import diskanalyzer.cmds.executers.CmdExecClear;
import diskanalyzer.cmds.executers.CmdExecExit;
import diskanalyzer.cmds.executers.CmdExecHelp;
import diskanalyzer.cmds.executers.CmdExecInfo;
import diskanalyzer.cmds.executers.CmdExecRun;
import diskanalyzer.cmds.executers.CmdExecSet;
import diskanalyzer.cmds.executers.CmdExecStat;
import java.util.HashMap;

/**
 * Singleton class that contains all available commands.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-12
 */
public class CmdNames {
    private static CmdNames instance = null; // Singleton
    
    private final HashMap<String, ICmdExecuter> cmds;
    
    /**
     * Singleton design pattern used here.
     * 
     * @return instance to the CmdNames object
     */
    public static CmdNames getInstance() {
        if (instance == null) {
            instance = new CmdNames();
        }
        
        return instance;
    }
    
    /* Default constructor. */
    private CmdNames() {
        this.cmds = new HashMap<>();
        
        this.cmds.put("analyze", new CmdExecAnalyze());
        this.cmds.put("clear", new CmdExecClear());
        this.cmds.put("exit", new CmdExecExit());
        this.cmds.put("stat", new CmdExecStat());
        this.cmds.put("help", new CmdExecHelp());
        this.cmds.put("set", new CmdExecSet());
        this.cmds.put("run", new CmdExecRun());
        this.cmds.put("info", new CmdExecInfo());
    }
    
    /**
     * Retrieves the list of all available commands.
     * 
     * @return list of all available commands concatenated to the one string
     */
    public String getCmdNames() {
        String names = "";
        
        for (String name : this.cmds.keySet()) {
            names += name + " ";
        }
        
        return names;
    }
    
    /**
     * Retrieves a command based on its name.
     * 
     * @param name name of the command
     * @return a new command instance in case it was found, or null otherwise
     */
    public Command getCommand(String name) {
        ICmdExecuter executer = this.cmds.get(this.processCmdName(name));
        
        return (executer == null) ? null : new Command(executer);
    }
    
    /**
     * Retrieves a command according its name.
     * 
     * @param name name of the command
     * @param params parameters of the command
     * @return a new command instance in case it was found, or null otherwise
     */
    public Command getCommand(String name, String[] params) {
        ICmdExecuter executer = this.cmds.get(this.processCmdName(name));
        
        return (executer == null) ? null : new Command(executer, params);
    }
    
    /* Trims the string and changes all its characters to a lower-case state. */
    private String processCmdName(String name) {
        return (name == null) ? null : name.trim().toLowerCase();
    }
}
