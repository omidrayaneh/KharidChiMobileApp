package com.omidrayaneh.omid.kharidchi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("family")
    @Expose
    String family;
    @SerializedName("mobile")
    @Expose
    String mobile;
    @SerializedName("pass")
    @Expose
    String pass;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("phone")
    @Expose
    String phone;


    public Users() {
    }

    public Users(String name, String family, String address, String phone) {
        this.name = name;
        this.family = family;
        this.address = address;
        this.phone = phone;
    }

    public Users(String name, String family, String mobile) {
        this.name = name;
        this.family = family;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
