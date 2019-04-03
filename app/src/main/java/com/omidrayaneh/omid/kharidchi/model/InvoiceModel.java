package com.omidrayaneh.omid.kharidchi.model;

public class InvoiceModel {
    int id;
    int statuse;
    String date;
    String total;
    String invoiceNo;

    public InvoiceModel() {
    }

    public InvoiceModel(int id, int statuse, String date, String total) {
        this.statuse = statuse;
        this.date = date;
        this.total = total;
        this.id=id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatuse(int statuse) {
        this.statuse = statuse;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getStatuse() {
        return statuse;
    }

    public String getDate() {
        return date;
    }

    public String getTotal() {
        return total;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
