package com.yijun.photos.data;

import java.io.Serializable;

public class Photos implements Serializable {
    String title;
    int id;
    int albumId;
    String url;
    String thumnailUrl;


    public Photos() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public void setThumnailUrl(String thumnailUrl) {
        this.thumnailUrl = thumnailUrl;
    }

    public Photos(String title, int id, int albumId, String url, String thumnailUrl) {
        this.title = title;
        this.id = id;
        this.albumId = albumId;
        this.url = url;
        this.thumnailUrl = thumnailUrl;

    }
}