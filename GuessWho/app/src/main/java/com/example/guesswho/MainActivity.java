package com.example.guesswho;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.BitSet;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static class Downloader extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();

            try {

                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1)
                {
                    result.append((char) data);
                    data = inputStreamReader.read();
                }
                //return BitmapFactory.decodeStream(inputStream);
                return result.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Downloader downloader = new Downloader();
        String result = "";

        try {
            result = downloader.execute("https://www.ranker.com/list/100-powerful-celebrities-with-highest-social-site-ranking/worlds-richest-people-lists").get();
            Log.i("HTML Result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
