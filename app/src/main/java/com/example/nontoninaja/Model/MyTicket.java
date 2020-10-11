package com.example.nontoninaja.Model;

public class MyTicket {
    public MyTicket() {

    }


    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtCategory() {
        return txtCategory;
    }

    public void setTxtCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }

    public String getTxtTime() {
        return txtTime;
    }

    public void setTxtTime(String txtTime) {
        this.txtTime = txtTime;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(String imgEvent) {
        this.imgEvent = imgEvent;
    }

    private String txtTitle;
    private String txtCategory;
    private String txtDate;
    private String txtTime;
    private String txtDescription;
    private String txtPriceReguler;
    private String txtPriceVIP;
    private String txtPriceVVIP;
    private String likes;

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTxtPriceReguler() {
        return txtPriceReguler;
    }

    public void setTxtPriceReguler(String txtPriceReguler) {
        this.txtPriceReguler = txtPriceReguler;
    }

    public String getTxtPriceVIP() {
        return txtPriceVIP;
    }

    public void setTxtPriceVIP(String txtPriceVIP) {
        this.txtPriceVIP = txtPriceVIP;
    }

    public String getTxtPriceVVIP() {
        return txtPriceVVIP;
    }

    public void setTxtPriceVVIP(String txtPriceVVIP) {
        this.txtPriceVVIP = txtPriceVVIP;
    }

    public String getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(String txtLocation) {
        this.txtLocation = txtLocation;
    }

    private String txtLocation;
    private String imgEvent;

    public MyTicket(String txtTitle, String txtCategory, String txtDate, String txtTime, String txtDescription,String imgEvent,String txtLocation,String txtPrice) {
        this.txtTitle = txtTitle;
        this.txtCategory = txtCategory;
        this.txtDate = txtDate;
        this.txtTime = txtTime;
        this.txtDescription = txtDescription;
        this.imgEvent = imgEvent;
        this.txtLocation = txtLocation;
        this.txtPriceReguler = txtPrice;
    }
}
