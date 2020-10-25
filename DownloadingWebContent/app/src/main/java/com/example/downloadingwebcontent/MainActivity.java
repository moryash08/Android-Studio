package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static class DownloadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            try {

                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();


                while(data != -1)
                {
                    char current = (char) data;
                    result.append(current);
                    data = inputStreamReader.read();
                }

                return result.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return "Failed!";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask downloadTask = new DownloadTask();
        String result = "";

        try {
            result = downloadTask.execute("https://google.com").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Result", result);
    }
}
