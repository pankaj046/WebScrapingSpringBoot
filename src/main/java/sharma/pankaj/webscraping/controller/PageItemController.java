package sharma.pankaj.webscraping.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sharma.pankaj.webscraping.model.PageItemModel;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PageItemController {

    PageItemModel response;

    @ResponseBody
    @GetMapping("/pageItem")
    public ResponseEntity<PageItemModel> getPageItem() {
        response = new PageItemModel();
        List<PageItemModel.Data> data = new ArrayList<>();
        try {

            Document doc = Jsoup.connect("https://www.allitebooks.in/").get();
            Elements body = doc.select("div.td-ss-main-content");
            System.out.println("" + body.select("div.td_module_19.td_module_wrap.td-animation-stack.td-meta-info-hide").size());
            for (Element element : body.select("div.td_module_19.td_module_wrap.td-animation-stack.td-meta-info-hide")) {
                String title = element.select("h3").text();
                String imageUrl = element.select("img").attr("abs:src");
                String description = element.select("div.td-excerpt").text();
                String url = element.select("div.td-read-more > a").attr("href");
                String[] a = url.split("/");
                data.add(new PageItemModel.Data(title, imageUrl, description, a[a.length - 1]));
            }
            response = new PageItemModel(false, "Data Fetch Successfully", data);

        } catch (Exception e) {
            response = new PageItemModel(false, "Data Not Fetched", data);
        }
        return ResponseEntity.ok(response);
    }
}
