package vn.edu.hcmuaf.fit.help;

import java.io.File;

public class MediaFile {
    String pathFolder;
    String pathUrl;

    public MediaFile() {
    }

    public MediaFile(String pathFolder, String pathUrl) {
        this.pathFolder = pathFolder;
        this.pathUrl = pathUrl;
    }

    public String getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public File getFile() {
        return new File(pathFolder);
    }
}
