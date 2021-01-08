package sharma.pankaj.webscraping.controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class MenuController {




    @GetMapping("/")
    public String getMenu() throws IOException {
        Document doc = Jsoup.connect("https://www.allitebooks.in/").get();
        Elements newsHeadlines = doc.select("div.td-ss-main-content");
        System.out.println("sdsdsds : "+newsHeadlines);
        String value = "sdsds";
//        for (Element headline : newsHeadlines) {
//            value = "\n" +headline;
////            value = headline.attr("title"), headline.absUrl("href");
//
//
//        }

        return newsHeadlines.toString();
    }
}
