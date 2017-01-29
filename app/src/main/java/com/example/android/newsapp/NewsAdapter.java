package com.example.android.newsapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;




public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> pNews){
        super(context,0,pNews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        News currentNews = getItem(position);
        TextView title = (TextView)listItemView.findViewById(R.id.title);
        title.setText(currentNews.getTitle());
        TextView section = (TextView)listItemView.findViewById(R.id.section);
        section.setText(currentNews.getSection());
        final String link = currentNews.getLink();
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                v.getContext().startActivity(browserIntent);
            }
        });
        return listItemView;
    }

}