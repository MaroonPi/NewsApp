package com.example.android.newsapp;



import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;


public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {
    private String mUrl;
    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }
    @Override
    public ArrayList<News> loadInBackground() {
        ArrayList<News> result = new ArrayList<News>();
        if(mUrl==null){
            return null;
        }
        result = QUtils.extractNews(mUrl);
        return result;
    }
}