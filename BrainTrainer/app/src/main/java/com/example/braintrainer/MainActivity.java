package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers; // Possible Answers
    int correctAnswerLocation;
    int scoreX = 0, scoreY = 0;

    public void reset(int scoreX, int scoreY)
    {

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for(int i = 0; i < 2; i++)
        {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < 2; j++)
            {
                Button button = (Button) tableRow.getChildAt(j);
                button.setEnabled(true);
            }
        }

        Random random = new Random(); // Generate Random Numbers

        int a = random.nextInt(100 + 1);
        int b = random.nextInt(100 + 1);
        String text = a + " + " + b + " = ";

        TextView QTextView = findViewById(R.id.QTextView);
        QTextView.setText(text);

        correctAnswerLocation = random.nextInt(4);

        answers = new ArrayList<>();

        for(int i = 0; i < 4; i++)
        {

            if(i == correctAnswerLocation)
                answers.add(a + b);

            else
            {
                int wrongAnswer = random.nextInt(200 + 1);

                while(wrongAnswer == (a + b))
                    wrongAnswer = random.nextInt(200 + 1);

                answers.add(wrongAnswer);
            }

        }

        int counter = 0;

        // Set Possible Answers on Buttons
        for(int i = 0; i < 2; i++)
        {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for(int j = 0; j < 2; j++)
            {
                Button button = (Button) tableRow.getChildAt(j);

                button.setText(String.valueOf(answers.get(counter)));

                counter++;
            }
        }

        // Set Score Text View
        text = scoreX + "/" + scoreY;

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(text);

    }

    public void Start(View view) {

        Log.i("OnClick", "Button Works");

        scoreX = scoreY = 0;

        // Make Start Button INVISIBLE
        final Button startButton = findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);

        final Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        // Make Everything else VISIBLE
        final TextView timerTextView = findViewById(R.id.timerTextView);
        timerTextView.setVisibility(View.VISIBLE);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setVisibility(View.VISIBLE);

        final TableLayout tableLayout = findViewById(R.id.tableLayout);
        tableLayout.setVisibility(View.VISIBLE);

        final TextView QTextView = findViewById(R.id.QTextView);
        QTextView.setVisibility(View.VISIBLE);

        final TextView checkTextView = findViewById(R.id.checkTextView);
        checkTextView.setVisibility(View.VISIBLE);
        checkTextView.setText("");

        final TextView result = findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);

        // CountDown Timer
        new CountDownTimer(30100, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

                DecimalFormat formatter = new DecimalFormat("00");
                String number = "0 : " + formatter.format(millisUntilFinished / 1000);
                timerTextView.setText(number);

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                QTextView.setText("TIME UP!");

                for(int i = 0; i < 2; i++)
                {
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

                    for(int j = 0; j < 2; j++)
                    {
                        Button button = (Button) tableRow.getChildAt(j);
                        button.setEnabled(false);
                    }
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        tableLayout.setVisibility(View.INVISIBLE);
                        QTextView.setVisibility(View.INVISIBLE);
                        checkTextView.setVisibility(View.INVISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);

                    }
                }, 3000);

                DecimalFormat formatter = new DecimalFormat("0.0000");
                String score = formatter.format((double) scoreX / scoreY);

                result.setText("ACCURACY : " + (Double.parseDouble(score) * 100) + "% !");
                result.setVisibility(View.VISIBLE);

            }
        }.start();

//        Toast.makeText(this, "Accuracy : " + ((scoreX / scoreY) * 100) + "% !", Toast.LENGTH_SHORT).show();

        reset(0, 0);

    }

    @SuppressLint("SetTextI18n")
    public void checkAnswer(View view)
    {

        Button button = (Button) view;

        TextView checkTextView = findViewById(R.id.checkTextView);

        if(button.getText().equals(String.valueOf(answers.get(correctAnswerLocation))))
        {
            checkTextView.setTextColor(Color.parseColor("#669900"));
            checkTextView.setText("Correct!");
            reset(++scoreX, ++scoreY);
        }

        else
        {
            checkTextView.setTextColor(Color.RED);
            checkTextView.setText("Wrong Answer!");
            reset(scoreX, ++scoreY);
        }

    }

    public void PlayAgain(View view)
    {
        Start(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timerTextView = findViewById(R.id.timerTextView);
        timerTextView.setVisibility(View.INVISIBLE);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setVisibility(View.INVISIBLE);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        tableLayout.setVisibility(View.INVISIBLE);

        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        TextView result = findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);

    }
}
