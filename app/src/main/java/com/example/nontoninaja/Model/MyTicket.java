package com.example.nontoninaja.Model;

public class MyTicket {
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

    public int getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(int imgEvent) {
        this.imgEvent = imgEvent;
    }

    private String txtTitle;
    private String txtCategory;
    private String txtDate;
    private String txtTime;
    private String txtDescription;

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    private String txtPrice;

    public String getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(String txtLocation) {
        this.txtLocation = txtLocation;
    }

    private String txtLocation;
    private int imgEvent;

    public MyTicket(String txtTitle, String txtCategory, String txtDate, String txtTime, String txtDescription,int imgEvent,String txtLocation,String txtPrice) {
        this.txtTitle = txtTitle;
        this.txtCategory = txtCategory;
        this.txtDate = txtDate;
        this.txtTime = txtTime;
        this.txtDescription = txtDescription;
        this.imgEvent = imgEvent;
        this.txtLocation = txtLocation;
        this.txtPrice = txtPrice;
    }
}
