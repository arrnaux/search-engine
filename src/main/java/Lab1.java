import java.io.File;

public class Lab1 {
    public static void main(String[] args) {
        L3();
    }

    public static void L1() {
        HTMLDocumentParser htmlDocumentParser = new HTMLDocumentParser("https://www.w3schools.com/tags/tag_meta.asp");
        System.out.println(htmlDocumentParser.extractTitle());
        System.out.println(htmlDocumentParser.extractMetaTags());
        System.out.println(htmlDocumentParser.extractLinks());
        System.out.println(htmlDocumentParser.extractBody());
        System.out.println(htmlDocumentParser.extractLinks());
    }

    public static void L3() {
        WordSpliter wordSpliter = new WordSpliter();
        DirectoryParser directoryParser = new DirectoryParser();
        directoryParser.findFilesInDirectory("files/inputs");
        for (File file : directoryParser.getRawFiles()) {
            System.out.println(file.getPath());
            wordSpliter.writeIndexToFile(file.getPath());
        }
    }
}
