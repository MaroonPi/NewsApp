package com.example.android.newsapp;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;



public final class QUtils {

    /** Tag for the log messages */
    public static final String LOG_TAG = QUtils.class.getSimpleName();

    public static ArrayList<News> extractNews(String urlRequest) {
        ArrayList<News> news = new ArrayList<>();
        //get string, convert to url object.
        URL url = null;
        try {
            url = new URL(urlRequest);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        //Make HTTP Request
        HttpURLConnection con;
        InputStream input;
        String jsonStringResponse = "";
        try
        {
            con = (HttpURLConnection)url.openConnection();
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("GET");
            try {
                con.connect();
            }catch (SocketTimeoutException e){
                Log.e(LOG_TAG,"Connection timed out",e);
            }
            if(con.getResponseCode()==200){
                input = con.getInputStream();
                StringBuilder output = new StringBuilder();
                if (input != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    while (line != null) {
                        output.append(line);
                        line = reader.readLine();
                    }
                }
                jsonStringResponse = output.toString();
            }
        }catch (IOException e){
            Log.e(LOG_TAG,"Invalid URL",e);
        }
        //Convert response to JSON
        try
        {

            if (TextUtils.isEmpty(jsonStringResponse)) {
                return null;
            }
            JSONObject jsonResponse = new JSONObject(jsonStringResponse);
            JSONObject response = jsonResponse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject r = results.getJSONObject(i);
                String title = r.getString("webTitle");
                String section = r.getString("sectionName");
                String link = r.getString("webUrl");
                news.add(new News(title,section,link));
            }
        }
        catch (JSONException e){
            Log.e(LOG_TAG,"Invalid JSON object",e);
        }
        return news;
    }

}