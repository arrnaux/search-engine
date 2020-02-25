import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordCategorizer {

    private Set<String> stopWords = new HashSet<>();

    private Set<String> exceptions = new HashSet<>();

    public WordCategorizer() {
        try {
            Scanner scanner = new Scanner(new File("files/stopwords.txt"));
            while (scanner.hasNextLine()) {
                stopWords.add(scanner.next());
            }

            scanner = new Scanner(new File("files/exceptions.txt"));
            while (scanner.hasNextLine()) {
                exceptions.add(scanner.next());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

    public Set<String> getExceptions() {
        return exceptions;
    }

    public boolean shouldBeStored(String word) {
        if (exceptions.contains(word)) {
            return true;
        } else if (!stopWords.contains(word)) {
            // another else for FC
            return true;
        }
        return false;
    }

    String getBaseForm(String string) {
        return string;
    }
}
