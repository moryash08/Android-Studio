package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 : red, 1 : yellow, 2 : Empty
    int[] gameState = {20, 20, 20, 20, 20, 20, 20, 20, 20};
    int counter = 1;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean finish = false;

    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {
        ImageView imgView = (ImageView) view;

        Log.i("Info", imgView.getTag().toString());

        int tagNum = Integer.parseInt(imgView.getTag().toString());

        if(gameState[tagNum] == 20 && !finish)
        {
            imgView.setTranslationY(-1500);

            if (gameState[tagNum] == 20 && counter == 0)
            {
                imgView.setImageResource(R.drawable.red);
                gameState[tagNum] = counter;
                counter = 1;
            }
            else if (gameState[tagNum] == 20 && counter == 1)
            {
                imgView.setImageResource(R.drawable.yellow);
                gameState[tagNum] = counter;
                counter = 0;
            }
            else
                Toast.makeText(this, "Select an empty Slot.", Toast.LENGTH_SHORT).show();
            imgView.animate().translationYBy(1500).setDuration(500);
        
            for(int i=0;i<8;i++)
            {
                //int temp[] = new int[3];
                int winCheck = 0;
                for(int j=0;j<3;j++)
                {
                    //temp[j] = gameState[winningPositions[i][j]];
                    winCheck += gameState[winningPositions[i][j]];
                }
                if(winCheck==3 || winCheck==0)
                {
                    String winnerName;
                    if(counter == 0)
                        winnerName = "Yellow";
                    else
                        winnerName = "Red";

                    Button btn = findViewById(R.id.playAgainButton);

                    TextView winView = findViewById(R.id.winnerTextView);

                    btn.setVisibility(View.VISIBLE);

                    winView.setText(winnerName + " Wins!");
                    winView.setVisibility(View.VISIBLE);

                    Toast.makeText(this, winnerName + " Wins!", Toast.LENGTH_SHORT).show();

                    for(int j=0;j<3;j++)
                        Log.i("Info", winningPositions[i][j]+" ");
                    finish = true;
                }
            }
        }
    }

    public void playAgain(View view) {
        Button btn = findViewById(R.id.playAgainButton);

        TextView winView = findViewById(R.id.winnerTextView);

        btn.setVisibility(View.INVISIBLE);

        winView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for(int i=0;i<9;i++)
            gameState[i]=20;

        counter = 1;

        finish = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
