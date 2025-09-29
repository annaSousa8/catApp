package com.example.catapp;

public class Cat {
    private String url;
    private String text2;

    public Cat(String url, String text2) {
        this.url = url;
        this.text2 = text2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
