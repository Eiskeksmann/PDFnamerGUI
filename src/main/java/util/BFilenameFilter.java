package util;

import java.io.File;
import java.io.FilenameFilter;

public class BFilenameFilter implements FilenameFilter {

    String starter;
    String extension;

    public BFilenameFilter(String starter, String extension){

        this.starter = starter;
        this.extension = extension;
    }

    public boolean accept(File dir, String name) {
        return name.startsWith(starter) && name.endsWith(extension);
    }

}
