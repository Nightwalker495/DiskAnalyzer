package diskanalyzer.analyzers.utils;

/**
 * Simple mutable integer class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class MutableLong {
    private long value;
    
    /**
     * Default constructor.
     */
    public MutableLong() {
        this.value = 0L;
    }
    
    /**
     * Constructor which allows to set the value at the beginning.
     * 
     * @param value value of a long integer
     */
    public MutableLong(long value) {
        this.value = value;
    }
    
    /**
     * Setter.
     * 
     * @param val new value
     */
    public void setValue(long val) {
        this.value = val;
    }
    
    /**
     * Getter.
     * 
     * @return integer value
     */
    public long getValue() {
        return this.value;
    }
    
    /**
     * Adds another value.
     * 
     * @param val value to add
     */
    public void addValue(long val) {
        this.value += val;
    }
    
    /**
     * Increments the value stored by one.
     */
    public void inc() {
        this.value++;
    }
    
    /**
     * Decrements the value stored by one.
     */
    public void dec() {
        this.value--;
    }
    
    /**
     * String representation of the object.
     * 
     * @return long integer value converted to string
     */
    @Override
    public String toString() {
        return "" + this.value;
    }
}