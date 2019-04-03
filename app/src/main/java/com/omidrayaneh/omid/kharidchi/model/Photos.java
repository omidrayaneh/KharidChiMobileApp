package com.omidrayaneh.omid.kharidchi.model;

public class Photos {
    int id;
    int product_id;
    String web_url;

    public Photos() {
    }

    public Photos(int id, int product_id, String web_url) {
        this.id = id;
        this.product_id = product_id;
        this.web_url = web_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
}
