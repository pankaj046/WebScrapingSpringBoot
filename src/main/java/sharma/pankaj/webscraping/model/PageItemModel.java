package sharma.pankaj.webscraping.model;

import java.util.List;

public class PageItemModel {

    private boolean error;
    private String message;

    private List<Data> data;

    public PageItemModel() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getList() {
        return data;
    }

    public void setList(List<Data> data) {
        this.data = data;
    }

    public PageItemModel(boolean error, String message, List<Data> list) {
        this.error = error;
        this.message = message;
        this.data = list;
    }

    public static class Data {
        String title;
        String imageUrl;
        String description;
        String id;

        public Data(String title, String imageUrl, String description, String id) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.description = description;
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
