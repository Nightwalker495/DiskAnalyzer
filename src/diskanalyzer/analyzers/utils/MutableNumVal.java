package diskanalyzer.analyzers.utils;

/**
 * Wrapper for the Number class which allows boxing types for primitive 
 * types to be mutable.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-29
 * 
 * @param <TNumVal> value type
 */
public class MutableNumVal<TNumVal extends Number> {
    /* !!!!!!!!!!!!!!!!!!!!NEPOUZITA TRIEDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
    TNumVal value;
    
    /**
     * Default constructor.
     */
    public MutableNumVal() {
    }
    
    /**
     * Constructor which allows to set the value at the beginning.
     * 
     * @param value value of a integer
     */
    /*public MutableInteger(TNumVal value) {
        this.value = value;
    }*/
    
    /**
     * Setter.
     * 
     * @param val new value
     */
    public void setValue(TNumVal val) {
        this.value = val;
    }
    
    /**
     * Getter.
     * 
     * @return integer value
     */
    public TNumVal getValue() {
        return this.value;
    }
    
    /**
     * Increments the value stored by one.
     */
    /*public void increment() {
        this.value = this.value + 1;
    }*/
    
    /**
     * Decrements the value stored by one.
     */
    /*public void decrement() {
        this.value--;
    }*/
    
    /**
     * String representation of the object.
     * 
     * @return integer value converted to string
     */
    @Override
    public String toString() {
        return this.value.toString();
    }
}
