package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public class DownloadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1)
                {
                    result.append((char) data);
                    data = inputStreamReader.read();
                }

                return result.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.i("JSON", s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather", weatherInfo);

                JSONArray arr = new JSONArray(weatherInfo);
                for(int i = 0; i < arr.length(); i++)
                {
                    JSONObject part = arr.getJSONObject(i);
                    TextView textView = findViewById(R.id.weatherView);

                    String weatherResult = "";
                    String main = part.getString("main");
                    String desc = part.getString("description");

                    if(!main.isEmpty() && !desc.isEmpty())
                        weatherResult = main + " : " + desc + "\n";

                    if(!weatherResult.isEmpty())
                        textView.setText(weatherResult);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void checkWeather(View view)
    {

        EditText editText = findViewById(R.id.cityName);
        DownloadTask downloadTask = new DownloadTask();
        String result = "";

        try {
            result = downloadTask.execute("https://openweathermap.org/data/2.5/weather?q=" + editText.getText().toString() + "&appid=439d4b804bc8187953eb36d2a8c26a02").get();

            if(result.isEmpty())
                Toast.makeText(this, "Enter a valid City Name", Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
