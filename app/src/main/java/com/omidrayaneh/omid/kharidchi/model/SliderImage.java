package com.omidrayaneh.omid.kharidchi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SliderImage  implements Serializable {
    int id;
    String path;
    String value;
    String status_search;

    public SliderImage(int id, String path, String value, String status_search) {
        this.id = id;
        this.path = path;
        this.value = value;
        this.status_search = status_search;
    }

    public SliderImage() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus_search() {
        return status_search;
    }

    public void setStatus_search(String status_search) {
        this.status_search = status_search;
    }
}