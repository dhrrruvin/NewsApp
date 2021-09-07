package com.example.newsapp;

import java.io.Serializable;

public class News implements Serializable {

    private String title;
    private String author;
    private String url;
    private String imageUrl;

    public News(String title, String author, String url, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.author = author;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
