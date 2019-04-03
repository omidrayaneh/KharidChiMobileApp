package com.omidrayaneh.omid.kharidchi.model;

import java.util.List;

/**
 * Created by pratap.kesaboyina on 30-11-2015.
 */
public class prodact_category {



    private String name_category;
    private List<product> product;


    public prodact_category() {

    }
    public prodact_category(String name_category, List<product> product) {
        this.name_category = name_category;
        this.product = product;
    }



    public String getname_category() {
        return name_category;
    }

    public void setname_category(String name_category) {
        this.name_category = name_category;
    }

    public List<product> getProduct() {
        return product;
    }

    public void setProduct(List<product> product) {
        this.product = product;
    }


}
