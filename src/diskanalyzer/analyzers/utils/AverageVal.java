package diskanalyzer.analyzers.utils;

/**
 * Serves the purpose of a counter with the ability to calculate the average
 * value of the input values.
 * 
 * @author Milan Ondrasovic
 * @version 2014-11-13
 */
public class AverageVal {
    private long value;
    private int count;
    
    /**
     * Returns a new AverageVal object. Initializes the basic values which are
     * insufficient for calculations, there's need to provide more data through
     * messages.
     */
    public AverageVal() {
        this.value = 0L;
        this.count = 0;
    }
    
    /**
     * Adds new value to the storage.
     * 
     * @param val specifies the value to add
     */
    public void addVal(long val) {
        this.value += val;
        this.count++;
    }
    
    /**
     * Calculates the average value from the stored values. The result value
     * is rounded since the return value is of integral type.
     * 
     * @return average value as long, in case of no input, zero
     */
    public long getAverageValLong() {
        if (this.count == 0) {
            return 0L;
        } else {
            double val = (double) (this.value / (long) this.count);
            
            return this.roundVal(val);
        }
    }
    
    /**
     * Calculates the average value from the stored values. The result value
     * is rounded since the return value is of integral type.
     * 
     * @return average value as integer, in case of no input, zero
     */
    public int getAverageValInt() {
        return (int) this.getAverageValLong();
    }
    
    /**
     * Calculates the average value from the stored values.
     * 
     * @return average value, in case of no input, zero
     */
    public double getAverageValDouble() {
        if (this.count == 0) {
            return 0.0;
        } else {
            return (double) this.value / (double) this.count;
        }
    }
    
    /**
     * Returns the sum of all values added to the pool.
     * 
     * @return total value stored
     */
    public long getTotalVal() {
        return this.value;
    }
    
    /**
     * Returns the number of values stored.
     * 
     * @return values count
     */
    public int getValsCount() {
        return this.count;
    }
    
    /**
     * Sets the values of all the attributes to the state at the beginning.
     */
    public void clear() {
        this.value = 0L;
        this.count = 0;
    }
    
    /**
     * String representation of the object. 
     * 
     * @return the result average value with precision to 4 decimal digits
     * converted as string
     */
    @Override
    public String toString() {
        return String.format("%.4f", this.getAverageValDouble());
    }
    
    /* Rounds the input value and returns it as integral type. */
    private long roundVal(double val) {
        return (long) (val + 0.5);
    }
}