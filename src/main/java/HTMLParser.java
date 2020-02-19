import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;

public class HTMLParser {
    public static void main(int argc, String[] argv) {
        HashMap<String, Integer> dictionary = new HashMap<>();
        try {
            InputStream in = new FileInputStream("files/file.txt");
            Reader r = new InputStreamReader(in, Charset.defaultCharset());
            int intch;
            StringBuilder currentWord = new StringBuilder("");
            String separators = new String(" '\",?!.");
            while ((intch = r.read()) != -1) {
                char ch = (char) intch;
                if (-1 == separators.indexOf(ch)) {
                    currentWord.append(ch);
                } else {
                    // Just found a word.
                    if (dictionary.containsKey(currentWord.toString())) {
                        dictionary.replace(currentWord.toString(), dictionary.get(currentWord.toString()) + 1);
                    }
                    dictionary.put(currentWord.toString(), 1);
                    currentWord = new StringBuilder("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dictionary);
    }
}