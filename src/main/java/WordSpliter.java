import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;

public class WordSpliter {

    public HashMap<String, Integer> createDirectIndexFromFile(String filePath) {

        HashMap<String, Integer> directIndex = new HashMap<>();
        WordFilter wordFilter = new WordFilter();

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
                    if (wordFilter.shouldBeStored(currentWord.toString())) {
                        if (directIndex.containsKey(currentWord.toString())) {
                            directIndex.replace(currentWord.toString(), directIndex.get(currentWord.toString()) + 1);
                        } else {
                            directIndex.put(currentWord.toString(), 1);
                        }
                    }
                    currentWord = new StringBuilder("");
                }
            }
            directIndex.remove("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directIndex;
    }

    public void writeIndexToFile(String filePath) {
        HashMap<String, Integer> map = createDirectIndexFromFile(filePath);
        JSONObject jsonObject = new JSONObject(map);
        try {
            File outputFile = new File("files/outputs/" + filePath);
            if (outputFile.exists()) {
                PrintWriter printWriter = new PrintWriter(outputFile);
                printWriter.write("");
                printWriter.close();
            } else {
                outputFile.getParentFile().mkdirs();
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}