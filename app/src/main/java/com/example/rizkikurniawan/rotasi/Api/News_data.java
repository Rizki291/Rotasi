package com.example.rizkikurniawan.rotasi.Api;

public class News_data {
    private String Id;
    private String Title;
    private String Image;
    private String PublishDate;

    public News_data(String id, String title, String image, String publishDate) {
        Id = id;
        Title = title;
        Image = image;
        PublishDate = publishDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }
}
