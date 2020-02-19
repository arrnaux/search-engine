import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class HTMLDocumentParser {
    private Document document;

    public HTMLDocumentParser(String address) {
        try {
            document = Jsoup.connect(address).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String extractTitle() {
        return document.title();
    }

    public HashMap<String, String> extractMetaTags() {
        HashMap<String, String> hashMap = new HashMap<>();
        Elements metaTags = document.getElementsByTag("meta");

        for (Element metaTag : metaTags) {
            String name = metaTag.attr("name");
            String content = metaTag.attr("content");

            if (name.equals("keywords") || name.equals("description") || name.equals("robots")) {
                hashMap.put(name, content);
            }
        }
        return hashMap;
    }
}
