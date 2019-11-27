package com.example.rizkikurniawan.rotasi.Api;

public class NewsDetail {
    private int ID;
    private String Title;
    private String Description;
    private String Image;
    private String PublishDate;

    public NewsDetail(int ID, String title, String description, String image, String publishDate) {
        this.ID = ID;
        Title = title;
        Description = description;
        Image = image;
        PublishDate = publishDate;
    }

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public String getPublishDate() {
        return PublishDate;
    }
}
