package com.example.projectbncc;

public class Webinar{

    private String webinarImageUrl,webinarDesc;

    public Webinar(String webinarDesc, String webinarImageUrl) {
        this.webinarImageUrl = webinarImageUrl;
        this.webinarDesc = webinarDesc;
    }

    public Webinar(){}


    public String getWebinarImageUrl() {
        return webinarImageUrl;
    }

    public void setWebinarImageUrl(String webinarImageUrl) {
        this.webinarImageUrl = webinarImageUrl;
    }

    public String getWebinarDesc() {
        return webinarDesc;
    }

    public void setWebinarDesc(String webinarDesc) {
        this.webinarDesc = webinarDesc;
    }
}
