package diskanalyzer.cmds.executers;

/**
 * Exception which indicates missing parameter(s) for the command.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-17
 */
public class InvalidCmdParamException extends Exception {
    /**
     * Creates a new instance of <code>MissingCmdParamException</code> without
     * detail message.
     */
    public InvalidCmdParamException() {
    }

    /**
     * Constructs an instance of <code>MissingCmdParamException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCmdParamException(String msg) {
        super(msg);
    }
}
