package diskanalyzer.analyzers.utils;

/**
 * Simple mutable integer class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-19
 */
public class MutableInteger {
    private int value;
    
    /**
     * Default constructor.
     */
    public MutableInteger() {
        this.value = 0;
    }
    
    /**
     * Constructor which allows to set the value at the beginning.
     * 
     * @param value value of a integer
     */
    public MutableInteger(int value) {
        this.value = value;
    }
    
    /**
     * Setter.
     * 
     * @param val new value
     */
    public void setValue(int val) {
        this.value = val;
    }
    
    /**
     * Getter.
     * 
     * @return integer value
     */
    public int getValue() {
        return this.value;
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
     * @return integer value converted to string
     */
    @Override
    public String toString() {
        return "" + this.value;
    }
}
