import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HTMLDocumentParser {
    private Document document;

    public HTMLDocumentParser(String address) {
        try {
            document = Jsoup.connect(address).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> extractLinks() {
        Set<String> links = new HashSet<>();
        Elements linkFromPage = document.select("a[href]");
        for (Element link : linkFromPage) {
            String absoluteLink = link.attr("abs:href");
            if (!absoluteLink.contains(document.baseUri())) {
                int index = absoluteLink.indexOf("#");
                if (-1 != index) {
                    absoluteLink = absoluteLink.substring(index);
                }
                if (absoluteLink.length() > 0) {
                    links.add(absoluteLink);
                }
            }
        }
        return links;
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

            if (null != name) {
                if (name.equals("Keywords") || name.equals("description") || name.equals("robots")) {
                    hashMap.put(name, content);
                }
            }
        }
        return hashMap;
    }

    public Element extractBody() {
        return document.body();
    }
}
