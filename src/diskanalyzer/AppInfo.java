package diskanalyzer;

/**
 * Simple class which holds general information about the entire project.
 * 
 * @author Milan Ondrasovic
 * @version 2015-04-27
 */
public class AppInfo {
    /**
     * General information about the program, its author, date of release
     * together with the current build version.
     * 
     * @return formatted information about the project
     */
    public static String getAppInfo() {
        String appName = "DiskAnalyzer";
        String appVer = "2.0.0";
        String author = "Milan Ondrašovič";
        String authorEmail = "milan.ondrasovic@gmail.com";
        String releaseDate = "2015.05.08";
        
        String format = "***** %s %s (%s);\n***** Written by %s <%s>;";
        return String.format(format, appName, appVer, releaseDate, author,
                authorEmail);
    }
    
    /* Suppress instance creation */
    private AppInfo() {
    }
}
