package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> titles = new ArrayList<>();
    static ArrayList<String> content = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    static MyAdapter myAdapter;
    DownloadTask downloadTask;
    static SQLiteDatabase storyDb;

    public static void updateRecyclerView() {
        @SuppressLint("Recycle") Cursor c = storyDb.rawQuery("SELECT * FROM stories", null);
        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");

        if(c.moveToFirst()) {
            titles.clear();
            content.clear();

            do {
                titles.add(c.getString(titleIndex));
                content.add(c.getString(contentIndex));
            } while(c.moveToNext());

            myAdapter.notifyDataSetChanged();
        }
    }

    public static class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            StringBuilder result = new StringBuilder();
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                int data = inputStreamReader.read();

                while(data != -1) {
                    result.append((char) data);
                    data = inputStreamReader.read();
                }

                JSONArray jsonArray = new JSONArray(result.toString());
                int itemCount = Math.min(jsonArray.length(), 10);
                StringBuilder storyInfo = new StringBuilder();

                storyDb.execSQL("DELETE FROM stories");

                for(int i=0; i<itemCount; i++) {
                    String storyId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + storyId + ".json?print=pretty");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                    data = inputStreamReader.read();

                    while(data != -1) {
                        storyInfo.append((char) data);
                        data = inputStreamReader.read();
                    }
                    // Log.i("Story", storyInfo.toString());

                    JSONObject jsonObject = new JSONObject(storyInfo.toString());
                    storyInfo.setLength(0);

                    if(!jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                        String storyTitle = jsonObject.getString("title");
                        String storyUrl = jsonObject.getString("url");

//                        url = new URL(storyUrl);
//                        urlConnection = (HttpURLConnection) url.openConnection();
//                        inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
//                        data = inputStreamReader.read();
//                        StringBuilder storyContent = new StringBuilder();
//
//                        while(data != -1) {
//                            storyContent.append((char) data);
//                            data = inputStreamReader.read();
//                        }

                        Log.i("Title|URL", storyTitle + "|" + storyUrl);

                        String sqlQuery = "INSERT INTO stories (storyId, title, content) VALUES (?, ?, ?)";
                        SQLiteStatement liteStatement = storyDb.compileStatement(sqlQuery);
                        liteStatement.bindString(1, storyId);
                        liteStatement.bindString(2, storyTitle);
                        liteStatement.bindString(3, storyUrl);
                        // liteStatement.bindString(3, storyContent.toString());

                        liteStatement.execute();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateRecyclerView();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerWebView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, titles, content);
        recyclerView.setAdapter(myAdapter);

        storyDb = this.openOrCreateDatabase("Stories", MODE_PRIVATE, null);
        storyDb.execSQL("CREATE TABLE IF NOT EXISTS stories (id INTEGER PRIMARY KEY, storyId, INTEGER, title VARCHAR, content VARCHAR)");

        updateRecyclerView();

        downloadTask = new DownloadTask();
        String url = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
        String result = "";

        try {
            // result = downloadTask.execute(url).get();
            // Log.i("API_Result", result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}