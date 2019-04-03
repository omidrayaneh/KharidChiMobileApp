package com.omidrayaneh.omid.kharidchi.model;

public class CategoryModel {

    int id;
    String name_category;
    int status_category;
    int sub_categoryId;
    int end_category;
    String image_url;
    String web_image;


    public CategoryModel() {
    }

    public CategoryModel(int id, String name_category, int status_category) {
        this.id = id;
        this.name_category = name_category;
        this.status_category = status_category;
    }

    public int getEnd_category() {
        return end_category;
    }

    public void setEnd_category(int end_category) {
        this.end_category = end_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSub_categoryId() {
        return sub_categoryId;
    }

    public void setSub_categoryId(int sub_categoryId) {
        this.sub_categoryId = sub_categoryId;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getWeb_image() {
        return web_image;
    }

    public void setWeb_image(String web_image) {
        this.web_image = web_image;
    }

    public int getStatus_category() {
        return status_category;
    }

    public void setStatus_category(int status_category) {
        this.status_category = status_category;
    }
}
