import java.util.HashMap;

public class Lab1 {
    public static void main(String[] args) {
        WordSplit wordSplit = new WordSplit();
        HashMap<String, Integer> words = wordSplit.createDictionaryFromFile("files/file.txt");
        System.out.println(words);
        HTMLDocumentParser htmlDocumentParser = new HTMLDocumentParser("https://www.w3schools.com/tags/tag_meta.asp");
        System.out.println(htmlDocumentParser.extractTitle());
        System.out.println(htmlDocumentParser.extractMetaTags());
        System.out.println(htmlDocumentParser.extractLinks());
        System.out.println(htmlDocumentParser.extractBody());
    }
}
