package sharma.pankaj.webscraping.model;


import java.util.List;

public class MenuModel {

    private boolean error;
    private String message;
    private List<Data> data;

    public MenuModel() {
    }

    public MenuModel(boolean error, String message, List<Data> data) {
        this.error = error;
        this.message = message;
        this.data = data;
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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data{

        String title;
        String id;
        List<tag> tag;

        public Data() {
        }

        public Data(String title, String id, List<Data.tag> tag) {
            this.title = title;
            this.id = id;
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Data.tag> getTag() {
            return tag;
        }

        public void setTag(List<Data.tag> tag) {
            this.tag = tag;
        }

        public static class tag{
            String subTitle;
            String id;

            public tag(String subTitle, String id) {
                this.subTitle = subTitle;
                this.id = id;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

    }

}
