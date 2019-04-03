package com.omidrayaneh.omid.kharidchi.model;


public class product {


    private int id;
    private String name_product;
    private String image_url;
    private String price;
    private String qty;
    private String all_qty;
    private String owner;
    private String discont;



    public product() {
    }

   /* public product(String name_product,String price,String image_url,String all_qty,String qty,String owner,String discont )
    {
        this.name_product = name_product;
        this.image_url=image_url;
        this.price=price;
        this.qty=qty;
        this.discont=discont;
        this.owner=owner;
        this.all_qty=all_qty;
    }*/
    public product(int id,String name_product, String price,String image_url,String all_qty,String qty,String owner,String discont) {

        this.id=id;
        this.name_product = name_product;
        this.image_url=image_url;
        this.price=price;
        this.qty=qty;
        this.discont=discont;
        this.owner=owner;
        this.all_qty=all_qty;

    }

    public String getAll_qty() {
        return all_qty;
    }

    public void setAll_qty(String all_qty) {
        this.all_qty = all_qty;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDiscont() {
        return discont;
    }

    public void setDiscont(String discont) {
        this.discont = discont;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }


    public void setname_product(String name_product) {

        this.name_product = name_product;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }






}
