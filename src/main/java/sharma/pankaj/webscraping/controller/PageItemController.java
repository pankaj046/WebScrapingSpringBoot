package sharma.pankaj.webscraping.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sharma.pankaj.webscraping.model.PageItemModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PageItemController {

    @ResponseBody
    @GetMapping("/home")
    public ResponseEntity<PageItemModel> getHomeData() throws IOException {
        Document doc = Jsoup.connect("https://www.allitebooks.in/").get();
        return getData(doc);
    }


    @ResponseBody
    @GetMapping("/home/{id}")
    public ResponseEntity<PageItemModel> getHomeDataWithPage(@PathVariable(value = "id") String id) throws IOException {
        Document doc = Jsoup.connect("https://www.allitebooks.in/page/" + id).get();
        return getData(doc);
    }

    @ResponseBody
    @GetMapping("/category/{name}")
    public ResponseEntity<PageItemModel> getCategoryDataWithPage(@PathVariable(value = "name") String name) throws IOException {
        Document doc = Jsoup.connect("https://www.allitebooks.in/category/" + name).get();
        return getData(doc);
    }

    @ResponseBody
    @GetMapping("/tag/{submenu}")
    public ResponseEntity<PageItemModel> getSubMenuDataWithPage(@PathVariable(value = "submenu") String submenu) throws IOException {
        Document doc = Jsoup.connect("https://www.allitebooks.in/tag/" + submenu).get();
        return getData(doc);
    }

    private ResponseEntity<PageItemModel> getData(Document doc) {
        PageItemModel response;
        List<PageItemModel.Data> data = new ArrayList<>();
        try {
            Elements body = doc.select("div.td-ss-main-content");
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
