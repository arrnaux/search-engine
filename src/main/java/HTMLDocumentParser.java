import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HTMLDocumentParser {
    private Document document;

    public HTMLDocumentParser(String address) {
        try {
            document = Jsoup.connect(address).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> extractLinks(){
        List<String> linksList=new LinkedList<>();
        Elements links=document.select("a");
        for (Element e: links){
            String relHref = e.attr("href");
            String absHref = e.attr("abs:href");
            if(absHref.contains("#")) {
                int i=absHref.indexOf("#");
                linksList.add(absHref.substring(0,i));
            } else {
                linksList.add(absHref);
            }
        }
        return linksList;
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

            if (name.equals("Keywords") || name.equals("description") || name.equals("robots")) {
                hashMap.put(name, content);
            }
        }
        return hashMap;
    }

    public Element extractBody(){
        return document.body();
    }
}
