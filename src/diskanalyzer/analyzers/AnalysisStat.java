package diskanalyzer.analyzers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Results of statistic analysis.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-11
 */
public class AnalysisStat implements Iterable<StatItem>, Iterator<StatItem> {
    private final Collection<StatItem> data;
    private final Iterator<StatItem> iterator;
    private final String name;
    
    /**
     * Default constructor.
     * 
     * @param name headline for the analysis data
     */
    public AnalysisStat(String name) {
        this.data = new ArrayList<>();
        this.iterator = this.data.iterator();
        this.name = name;
    }
    
    /**
     * Getter.
     * 
     * @return headline for the analysis data
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Adds new item to the list.
     * 
     * @param description information description
     * @param value value described
     * @return true on success, false otherwise
     */
    public boolean add(String description, String value) {
        return this.addNew(description, value);
    }
    
    /**
     * Adds new item to the list.
     * 
     * @param description information description
     * @param value value described
     * @return true on success, false otherwise
     */
    public boolean add(String description, int value) {
        return this.addNew(description, "" + value);
    }
    
    /**
     * Adds new item to the list.
     * 
     * @param description information description
     * @param value value described
     * @return true on success, false otherwise
     */
    public boolean add(String description, long value) {
        return this.addNew(description, "" + value);
    }
    
    /**
     * Adds new item to the list.
     * 
     * @param description information description
     * @param value value described
     * @return true on success, false otherwise
     */
    public boolean add(String description, double value) {
        return this.addNew(description, String.format("%.4f", value));
    }
    
    /**
     * Returns the iterator to the list which contains all data provided
     * via add methods.
     * 
     * @return iterator instance
     */
    @Override
    public Iterator<StatItem> iterator() {
        return this.iterator;
    }

    /**
     * Asks the iterator whether there is still an object to process.
     * 
     * @return true if there is next item in the queue, false otherwise
     */
    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    /**
     * Next item from the iterator.
     * 
     * @return next item
     */
    @Override
    public StatItem next() {
        return this.iterator.next();
    }
    
    /**
     * Produces a formatted output from the entire list of data.
     * 
     * @return string representation of the object
     */
    @Override
    public String toString() {
        String str = this.name.toUpperCase() + "\n";
        
        for (StatItem item : this.data) {
            str += item.toString() + "\n";
        }
        
        return str;
    }
    
    /* Adds new data to the list. */
    private boolean addNew(String description, String value) {
        return this.data.add(new StatItem(description, value));
    }
}
