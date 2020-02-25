import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class WordSpliter {
    public HashMap<String, Integer> createDictionaryFromFile(String filePath) {
        HashMap<String, Integer> dictionary = new HashMap<>();
        try {
            InputStream fileInputStream = new FileInputStream(filePath);
            Reader streamReader = new InputStreamReader(fileInputStream, Charset.defaultCharset());
            int intch = 0;
            StringBuilder currentWord = new StringBuilder("");
            while ((intch = streamReader.read()) != -1) {
                char ch = (char) intch;
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                    currentWord.append(ch);
                } else {
                    // Just found a word.
                    WordCategorizer wordCategorizer = new WordCategorizer();
                    if (wordCategorizer.shouldBeStored(currentWord.toString())) {
                        if (dictionary.containsKey(currentWord.toString())) {
                            dictionary.replace(currentWord.toString(), dictionary.get(currentWord.toString()) + 1);
                        } else {
                            dictionary.put(currentWord.toString(), 1);
                        }
                    }
                    currentWord = new StringBuilder("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}