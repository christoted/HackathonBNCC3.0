package com.example.nontoninaja.Model;

public class MyTicket {
    private String txtTitle,txtContent,txtDate,txtTime,txtType;
    private int imgContent, imgDate, imgTime, imgType;

    public MyTicket(String txtTitle, String txtContent, String txtDate, String txtTime, String txtType, int imgContent, int imgDate, int imgTime, int imgType) {
        this.txtTitle = txtTitle;
        this.txtContent = txtContent;
        this.txtDate = txtDate;
        this.txtTime = txtTime;
        this.txtType = txtType;
        this.imgContent = imgContent;
        this.imgDate = imgDate;
        this.imgTime = imgTime;
        this.imgType = imgType;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtContent() {
        return txtContent;
    }

    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
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

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public int getImgContent() {
        return imgContent;
    }

    public void setImgContent(int imgContent) {
        this.imgContent = imgContent;
    }

    public int getImgDate() {
        return imgDate;
    }

    public void setImgDate(int imgDate) {
        this.imgDate = imgDate;
    }

    public int getImgTime() {
        return imgTime;
    }

    public void setImgTime(int imgTime) {
        this.imgTime = imgTime;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }
}
