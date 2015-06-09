package diskanalyzer.analyzers;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
 * Detects category of the file based on its extension. Extensions which
 * couldn't be assigned to any category will figure as "other".
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-28
 */
class DataCategoryDetector {
    private final Collection<String> musicExts;
    private final Collection<String> videoExts;
    private final Collection<String> documentExts;
    private final Collection<String> pictureExts;
    
    /**
     * Default constructor.
     */
    public DataCategoryDetector() {
        this.musicExts = new HashSet<>();
        this.videoExts = new HashSet<>();
        this.documentExts = new HashSet<>();
        this.pictureExts = new HashSet<>();
        
        this.initMusicCategoryExts();
        this.initVideoCategoryExts();
        this.initDocumentCategoryExts();
        this.initPictureCategoryExts();
    }
    
    /**
     * Detects the data tile category based upon its extension.
     * 
     * @param fileExt file extension
     * @return category of the file based on the assumed content based upon
     * its extension (music, videos, ...), null is returned in case of an
     * invalid string
     */
    public FileDataCategory detectExtDataCategory(String fileExt) {
        if (fileExt != null) {
            if (!fileExt.isEmpty()) {
                String ext = fileExt.trim().toLowerCase();
                
                if (this.musicExts.contains(ext)) {
                    return FileDataCategory.MUSIC;
                } else if (this.videoExts.contains(ext)) {
                    return FileDataCategory.VIDEOS;
                } else if (this.documentExts.contains(ext)) {
                    return FileDataCategory.DOCUMENTS;
                } else if (this.pictureExts.contains(ext)) {
                    return FileDataCategory.PICTURES;
                } else {
                    return FileDataCategory.OTHER;
                }
            }
        }
        
        return null;
    }
    
    /**
     * Retrieves the array of all categories to which a file may fall.
     * 
     * @return array of all possible categories
     */
    public String[] getCategoriesList() {
        return FileDataCategory.getAllCategories();
    }
    
    /* Initializes most common extensions used to store music files */
    private void initMusicCategoryExts() {
        String[] data = new String[] {
            "3ga", "aac", "aiff", "amr", "asf", "asx", "cda", "flac", "m4a",
            "m4p", "midi", "mp3", "ogg", "pcm", "rec", "snd", "uax", "wav",
            "wma", "wpl"
        };
        
        this.musicExts.addAll(Arrays.asList(data));
    }
    
    /* Initializes most common extensions used to store video files */
    private void initVideoCategoryExts() {
        String[] data = new String[] {
            "264", "3g2", "3gp", "asf", "asx", "bik", "dash", "dat", "dvr",
            "flv", "h264", "m2t", "m2ts", "m4v", "mkv", "mod", "mov", "mp4",
            "mpeg", "mpg", "ogv", "rec", "vob", "wmv", "avi"
        };
        
        this.videoExts.addAll(Arrays.asList(data));
    }
    
    /* Initializes most common extensions used to store document files */
    private void initDocumentCategoryExts() {
        String[] data = new String[] {
            "abw", "aww", "chm", "cnt", "dbx", "djvu", "doc", "docx", "docm",
            "dot", "dotm", "dotx", "eml", "epub", "gp4", "gp5", "ind", "indd",
            "key", "keynote", "mht", "mpp", "mpt", "odf", "ods", "odt", "ott",
            "oxps", "pages", "pdf", "pmd", "pot", "potx", "pps", "ppsx", "ppt",
            "pptm", "pptx", "prn", "prproj", "pub", "pwi", "rep", "rtf", "sdd",
            "sdw", "shs", "snp", "sxw", "tpl", "vsd", "wlmp", "wpd", "wps",
            "wri", "xps"
        };
        
        this.documentExts.addAll(Arrays.asList(data));
    }
    
    /* Initializes most common extensions used to store picture files */
    private void initPictureCategoryExts() {
        String[] data = new String[] {
            /* Bitmap extensions */
            "bmp", "dib", "dng", "dt2", "emf", "gif", "ico", "icon", "jpeg",
            "jpg", "map", "pcx", "png", "psd", "raw", "tga", "thm", "tif",
            "tiff", "wbmp", "wdp", "webp", 
            /* Vector extensions */
            "ai", "cdr", "csh", "drw", "emz", "odg", "pic", "svg", "sda", "svg",
            "swf", "wmf"
        };
        
        this.pictureExts.addAll(Arrays.asList(data));
    }
}
