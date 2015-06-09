package diskanalyzer.analyzers;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of "FileDataCategory" class.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-30
 */
public class FileDataCategoryTest {
    /**
     * Test of values method, of class FileDataCategory.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        
        FileDataCategory[] result = FileDataCategory.values();
        assertEquals(result.length, 5); // 5 categories overall
    }

    /**
     * Test of valueOf method, of class FileDataCategory.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        
        String name = "MUSIC";
        FileDataCategory result = FileDataCategory.valueOf(name);
        assertEquals(result, FileDataCategory.MUSIC);
        
        name = "VIDEOS";
        result = FileDataCategory.valueOf(name);
        assertEquals(result, FileDataCategory.VIDEOS);
        
        name = "DOCUMENTS";
        result = FileDataCategory.valueOf(name);
        assertEquals(result, FileDataCategory.DOCUMENTS);
        
        name = "PICTURES";
        result = FileDataCategory.valueOf(name);
        assertEquals(result, FileDataCategory.PICTURES);
        
        name = "OTHER";
        result = FileDataCategory.valueOf(name);
        assertEquals(result, FileDataCategory.OTHER);
    }

    /**
     * Test of getAllCategories method, of class FileDataCategory.
     */
    @Test
    public void testGetAllCategories() {
        System.out.println("getAllCategories");
        
        String[] result = FileDataCategory.getAllCategories();
        assertEquals(result.length, 5); // 5 categories overall
    }

    /**
     * Test of getName method, of class FileDataCategory.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        FileDataCategory instance = FileDataCategory.MUSIC;
        assertEquals(instance.getName(), "music");
        
        instance = FileDataCategory.VIDEOS;
        assertEquals(instance.getName(), "videos");
        
        instance = FileDataCategory.DOCUMENTS;
        assertEquals(instance.getName(), "documents");
        
        instance = FileDataCategory.PICTURES;
        assertEquals(instance.getName(), "pictures");
        
        instance = FileDataCategory.OTHER;
        assertEquals(instance.getName(), "other");
    }

    /**
     * Test of toString method, of class FileDataCategory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        
        FileDataCategory instance = FileDataCategory.MUSIC;
        assertEquals(instance.toString(), "music");
        
        instance = FileDataCategory.VIDEOS;
        assertEquals(instance.toString(), "videos");
        
        instance = FileDataCategory.DOCUMENTS;
        assertEquals(instance.toString(), "documents");
        
        instance = FileDataCategory.PICTURES;
        assertEquals(instance.toString(), "pictures");
        
        instance = FileDataCategory.OTHER;
        assertEquals(instance.toString(), "other");
    }
}
