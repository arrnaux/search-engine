import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class DirectoryParser {

    private Queue<File> folders = new LinkedList<>();
    private Queue<File> rawFiles = new LinkedList<>();

    public Queue<File> getFolders() {
        return folders;
    }

    public Queue<File> getRawFiles() {
        return rawFiles;
    }

    public void findFilesInDirectory(String directoryName) {
        File parentDirectory = new File(directoryName);
        if (parentDirectory.isDirectory()) {
            folders.add(parentDirectory);
        }

        while (!folders.isEmpty()) {
            File file = folders.remove();
            File[] filesFromDirectory = file.listFiles();
            if (null != filesFromDirectory) {
                for (File aFor : filesFromDirectory) {
                    if (aFor.isDirectory()) {
                        folders.add(aFor);
                    } else {
                        rawFiles.add(aFor);
                    }
                }
            }
        }
    }
}
