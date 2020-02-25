import java.util.HashMap;

public class Lab1 {
    public static void main(String[] args) {
        WordSpliter wordSpliter = new WordSpliter();
        HashMap<String, Integer> words = wordSpliter.createDictionaryFromFile("files/file.txt");
//        System.out.println(words);
//        HTMLDocumentParser htmlDocumentParser = new HTMLDocumentParser("https://www.w3schools.com/tags/tag_meta.asp");
//        System.out.println(htmlDocumentParser.extractTitle());
//        System.out.println(htmlDocumentParser.extractMetaTags());
//        System.out.println(htmlDocumentParser.extractLinks());
//        System.out.println(htmlDocumentParser.extractBody());
//        System.out.println(htmlDocumentParser.extractLinks());
        DirectoryParser directoryParser = new DirectoryParser();
        directoryParser.processDirectory("files/inputs");
        System.out.println(directoryParser.getRawFiles());
    }
}
