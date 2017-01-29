package com.example.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>>{
    NewsAdapter nAdapter;

    private static final String URLString = "https://content.guardianapis.com/search?q=women&from-date=2017-01-01&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this,URLString);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> data) {
        DisplayUI(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        nAdapter.clear();
    }

    public void DisplayUI(ArrayList<News> news){
        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) findViewById(R.id.list);
        if(news==null)
        {
            newsListView.setEmptyView(findViewById(R.id.empty));
        }
        else
        {
            // Create a new {@link ArrayAdapter} of news
            nAdapter = new NewsAdapter(this, news);
            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            newsListView.setAdapter(nAdapter);
        }
    }
}