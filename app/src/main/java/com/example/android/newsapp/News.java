package com.example.android.newsapp;


public class News {
    private String mTitle;
    private String mSection;
    private String mLink;
    public News(String title,String section,String link){
        mTitle = title;
        mSection = section;
        mLink = link;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getSection(){
        return mSection;
    }
    public String getLink() {
        return mLink;
    }
}