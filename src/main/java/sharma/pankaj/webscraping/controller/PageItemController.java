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
import sharma.pankaj.webscraping.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PageItemController {

    @ResponseBody
    @GetMapping("/api/home/{pageNumber}")
    public ResponseEntity<PageItemModel> getHomeDataWithPageNumber(@PathVariable(value = "pageNumber") String pageNumber) {
        if (pageNumber.equals("0") || pageNumber.equals("1")) {
            return getData(Constants.url);
        } else {
            return getData(Constants.url + "page/" + pageNumber);
        }
    }

    @ResponseBody
    @GetMapping("/api/category/{name}/page/{number}")
    public ResponseEntity<PageItemModel> getCategoryDataWithPageNumber(
            @PathVariable(value = "name") String name,
            @PathVariable(value = "number") String number) {
        if (number.equals("0") || number.equals("1")) {
            return getData(Constants.url + "category/" + name);
        } else {
            return getData(Constants.url + "category/" + name + "/page/" + number);
        }
    }

    @ResponseBody
    @GetMapping("/api/tag/{tagId}/page/{number}")
    public ResponseEntity<PageItemModel> getTagDataWithPageNumber(
            @PathVariable(value = "tagId") String tagId,
            @PathVariable(value = "number") String number) {
        if (number.equals("0") || number.equals("1")) {
            return getData(Constants.url + "tag/" + tagId);
        } else {
            return getData(Constants.url + "tag/" + tagId + "/page/" + number);
        }
    }

    @ResponseBody
    @GetMapping("/api/search/{key}/page/{number}")
    public ResponseEntity<PageItemModel> getSearchWithPageNumber(
            @PathVariable(value = "key") String key,
            @PathVariable(value = "number") String number) {
        if (number.equals("0") || number.equals("1")) {
            return getData(Constants.url + "?s=" + key);
        } else {
            return getData(Constants.url + "page/3/" + "?s=" + key);
        }
    }

    private ResponseEntity<PageItemModel> getData(String value) {
        PageItemModel response;
        List<PageItemModel.Data> data = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(value).get();
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
