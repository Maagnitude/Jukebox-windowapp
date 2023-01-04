package gr.hua.dit.oop2;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.logging.Level;

public class MP3FileFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Filepath is a directory");
            return true;
        }
        String extension = getExtension(file);
        if (extension != null) {
            if(extension.equals("mp3")) {
                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Filepath is mp3");
                return true;
            } else {
                //org.hua.LogHandler.writeToLogNoThread(Level.INFO,"Filepath isnt mp3");
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "MP3 files";
    }

    public String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
