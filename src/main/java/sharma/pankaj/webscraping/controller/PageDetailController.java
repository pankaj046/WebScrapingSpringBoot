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
import sharma.pankaj.webscraping.model.PageDetailModel;

@RestController
public class PageDetailController {

    @ResponseBody
    @GetMapping("/pagedetail/{pageId}")
    public ResponseEntity<PageDetailModel> getPageDetails(@PathVariable(value = "pageId") String pageId){
        PageDetailModel response;
        try {
            Document doc = Jsoup.connect("https://www.allitebooks.in/" + pageId).get();
            Elements body = doc.select("div.td-post-content");
            String title = doc.select("h1.entry-title").text();
            String imageUrl = body.select("img").attr("abs:src");
            String info = body.select("p:eq(1)").text();
            String bookName = getValue(info, info.indexOf("Book Name:")+10, "Author:");
            Elements auth = body.select("p:eq(1) > a");
            StringBuilder authorValue = new StringBuilder();
            for (int i = 0; i<auth.size(); i++){
                if (auth.size()-1!= i){
                    authorValue.append(auth.get(i).text()).append(",");
                }else {
                    authorValue.append(auth.get(i).text());
                }
            }
            String author = authorValue.toString();
            String isbnNumber = getValue(info, info.indexOf("ISBN-10:")+8, "Year:");
            String year = getValue(info, info.indexOf("Year:")+5, "Pages:");
            String pages = getValue(info, info.indexOf("Pages:")+6, "Language:");
            String language = getValue(info, info.indexOf("Language:")+9, "File size:");
            String fileSize = getValue(info, info.indexOf("File size:")+10, "File format:");
            String fileFormat = getValue(info, info.indexOf("File format:")+12, "");
            String description = body.select("p:eq(2)").toString();
            String pdfUrl = body.select("p:eq(2)").toString();
            response = new PageDetailModel(false, "Data fetch successfully", new PageDetailModel.Data(
                    title,
                    imageUrl,
                    bookName,
                    author,
                    isbnNumber,
                    year,
                    pages,
                    language,
                    fileSize,
                    fileFormat,
                    description,
                    pdfUrl
            ));
        }catch (Exception e){
            response = new PageDetailModel(true, "Data not fetched\n" +e, null);
        }

        return ResponseEntity.ok(response);
    }

    private String getValue(String data, int start, String end){
        String value = "";
        try {
            if (end.equals("")){
                value = data.substring(start, data.length()).trim();
            }else {
                value = data.substring(start, data.indexOf(end)).trim();
            }
        }catch (Exception e){
            value = "Not Found";
        }
        return value;
    }
}
