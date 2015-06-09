package diskanalyzer.cmds.executers;

/**
 * Simple "stopwatch" which can be used to measure the time take between two 
 * events. Originally it was designed to time the execution of a method.
 * 
 * @author Milan Ondrasovic
 * @version 2014-11-18
 */
public class ExecStopwatch {
    /* Number of miliseconds in one minute */
    private static final int MSECS_IN_MINUTE = 60000;
    /* Number of miliseconds in one second */
    private static final int MSECS_IN_SECOND = 1000;
    
    private long startTime;
    private long endTime;
    
    /**
     * Returns a new ExecStopwatch object.
     */
    public ExecStopwatch() {
        this.startTime = 0L;
        this.endTime = 0L;
    }
    
    /**
     * Starts the timer. This moment will be used as a starting point for
     * further calculations.
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * Stops the timer. This moment will be used as a ending point for
     * further calculations.
     */
    public void stop() {
        this.endTime = System.currentTimeMillis();
    }
    
    /**
     * Resets the timer.
     */
    public void reset() {
        this.startTime = 0L;
        this.endTime = 0L;
    }
    
    /**
     * Time measured is returned in format: [minutes]:[seconds].[milliseconds].
     * 
     * @return elapsed time formatted
     */
    @Override
    public String toString() {
        int elapsedTime = Math.abs((int) (this.endTime - this.startTime));
        
        int minutes = elapsedTime / MSECS_IN_MINUTE;
        elapsedTime -= minutes * MSECS_IN_MINUTE;
        
        int seconds = elapsedTime / MSECS_IN_SECOND;
        elapsedTime -= seconds * MSECS_IN_SECOND;
        
        int miliSeconds = elapsedTime;
        
        return String.format("%02d:%02d.%03ds", minutes, seconds, miliSeconds);
    }
}
