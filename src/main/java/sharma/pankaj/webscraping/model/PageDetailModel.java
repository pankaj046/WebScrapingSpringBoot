package sharma.pankaj.webscraping.model;

public class PageDetailModel {

    private boolean error;
    private String message;
    private  Data data;

    public PageDetailModel() {
    }

    public PageDetailModel(boolean error, String message, Data data) {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        String title;
        String imageUrl;
        String bookName;
        String author;
        String isbnNumber;
        String year;
        String pages;
        String language;
        String fileSize;
        String fileFormat;
        String description;
        String pdfUrl;


        public Data() {
        }

        public Data(String title, String imageUrl, String bookName,
                    String author, String isbnNumber, String year,
                    String pages, String language, String fileSize,
                    String fileFormat, String description, String pdfUrl) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.bookName = bookName;
            this.author = author;
            this.isbnNumber = isbnNumber;
            this.year = year;
            this.pages = pages;
            this.language = language;
            this.fileSize = fileSize;
            this.fileFormat = fileFormat;
            this.description = description;
            this.pdfUrl = pdfUrl;
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

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIsbnNumber() {
            return isbnNumber;
        }

        public void setIsbnNumber(String isbnNumber) {
            this.isbnNumber = isbnNumber;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getFileFormat() {
            return fileFormat;
        }

        public void setFileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPdfUrl() {
            return pdfUrl;
        }

        public void setPdfUrl(String pdfUrl) {
            this.pdfUrl = pdfUrl;
        }
    }
}
