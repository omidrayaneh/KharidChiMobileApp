package com.omidrayaneh.omid.kharidchi.model;

public class MessageModel {

    int id;
    int status;
    String content;
    String title;
    String date;

    public MessageModel(int id, int status, String content, String title, String date) {
        this.id = id;
        this.status = status;
        this.content = content;
        this.title = title;
        this.date = date;
    }

    public MessageModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
