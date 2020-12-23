package utils;

import java.io.File;
import java.io.FilenameFilter;

public class Choice {

    //класс для выбора списка файлов
    public String[] getChoices(String dirPath, String extension) {
        String[] choices = null;
        File dir = new File(dirPath);
        if (dir.exists()) {
            choices = dir.list(new FileFilter(extension));
        }
        return choices;
    }


    protected static class FileFilter implements FilenameFilter {

        private final String extension;

        public FileFilter(String extension) {
            this.extension = "." + extension;
        }

        public boolean accept(File dir, String name) {
            return name.endsWith(extension);
        }
    }
}
