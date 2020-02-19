import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class HTMLParser {
    public static void main(String[] args) {
        System.out.print(createDictionaryFromFile("files/file.txt"));
    }

    public static HashMap<String, Integer> createDictionaryFromFile(String filePath) {
        HashMap<String, Integer> dictionary = new HashMap<>();
        try {
            InputStream fileInputStream = new FileInputStream(filePath);
            Reader streamReader = new InputStreamReader(fileInputStream, Charset.defaultCharset());
            int intch;
            StringBuilder currentWord = new StringBuilder("");
            while ((intch = streamReader.read()) != -1) {
                char ch = (char) intch;
                if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
                    currentWord.append(ch);
                } else {
                    // Just found a word.
                    if (dictionary.containsKey(currentWord.toString())) {
                        dictionary.replace(currentWord.toString(), dictionary.get(currentWord.toString()) + 1);
                    } else {
                        dictionary.put(currentWord.toString(), 1);
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