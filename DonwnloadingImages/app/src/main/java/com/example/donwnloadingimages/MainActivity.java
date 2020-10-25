package com.example.donwnloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {

                URL url = new URL(strings[0]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void ImageDownloader(View view)
    {
        ImageView imageView = findViewById(R.id.imageView);
        DownloadImage downloadImage = new DownloadImage();

        try {
            imageView.setImageBitmap(downloadImage.execute("https://cdnb.artstation.com/p/assets/images/images/021/829/059/large/mcashe-mcashe-uchiha-itachi-by-mcashe-dbraapa.jpg?1573091494").get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

                                @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
