package diskanalyzer.analyzers;

/**
 * Statistics item which contains description=value pair. It is used by the
 * analyzers to hold the output information.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class StatItem {
    private final String description;
    private final String value;
    
    /**
     * Constructor.
     * 
     * @param description description regarding the value
     * @param value value as string
     */
    public StatItem(String description, String value)
            throws IllegalArgumentException {
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        } else {
            if (description.isEmpty()) {
                String msg = "description cannot be empty";
                
                throw new IllegalArgumentException(msg);
            } else {
                this.description = description;
                this.value = value;
            }
        }
    }
    
    /**
     * Getter.
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Getter.
     * 
     * @return value
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * String representation of the object in format:
     * "description: value"
     * 
     * @return string representation of the object
     */
    @Override
    public String toString() {
        String empty = "----------";
        
        String descr = (this.description == null) ? empty : this.description;
        String val = (this.value == null) ? empty : this.value;
        
        return descr + ": " + val;
    }
}
