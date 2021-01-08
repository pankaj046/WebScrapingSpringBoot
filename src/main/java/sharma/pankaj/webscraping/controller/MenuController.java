package sharma.pankaj.webscraping.controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sharma.pankaj.webscraping.model.MenuModel;
import sharma.pankaj.webscraping.model.PageItemModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MenuController {




    @GetMapping("/")
    public ResponseEntity<MenuModel> getMenu(){
        MenuModel response = new MenuModel();
        try {
            List<MenuModel.Data> data = new ArrayList<>();
            Document doc = Jsoup.connect("https://www.allitebooks.in/").get();
            Elements body = doc.select("ul.sf-menu > li");
            for (int i=0; i<6; i++){
                Element element = body.get(i);
                if (i==0){
                    data.add(new MenuModel.Data(element.text(), "/", null));
                }else if (i == 1 || i==5){
                    String url1 = element.select("li > a").attr("href");
                    String[] id = url1.split("/");
                    List<MenuModel.Data.tag> tags = new ArrayList<>();
                    Elements elementsTag = element.select("ul.sub-menu > li");
                    for (Element element1 : elementsTag){
                        String url = element1.select("li > a").attr("href");
                        String[] a = url.split("/");
                        tags.add(new MenuModel.Data.tag(element1.text(), a[a.length - 1]));
                    }
                    String title = element.text();
                    String[] t1 = title.split(" ");
                    if (i == 1){
                        data.add(new MenuModel.Data(t1[0], id[id.length - 1], tags));
                    }else {
                        data.add(new MenuModel.Data(t1[0] + " "+t1[1], id[id.length - 1], tags));
                    }

                }else {
                    String url = element.select("li > a").attr("href");
                    String[] a = url.split("/");
                    data.add(new MenuModel.Data(element.text(), a[a.length - 1], null));
                }
            }
            response = new MenuModel(false, "Data Fetch Successfully", data);
        }catch (Exception e){
            response = new MenuModel(true, "Data Not Found", null);
        }

        return ResponseEntity.ok(response);
    }
}
