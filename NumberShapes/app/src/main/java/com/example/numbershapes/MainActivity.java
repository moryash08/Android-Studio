package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class checkT
    {
        public boolean checkTriangular(int num)
        {
            int sum=1, i=1;
            while(sum<num)
            {
                i++;
                sum+=i;
            }
            return (sum==num)?true:false;
        }
    }

    class checkS
    {
        public boolean checkSquare(int num)
        {
            int it=1, temp=0;
            while(temp<num)
            {
                temp=it*it;
                it++;
            }
            return (temp==num)?true:false;
        }
    }

    public void testNumber(View view)
    {
        EditText numText = findViewById(R.id.numEditText);
        String s = numText.getText().toString();
        checkT ct = new checkT();
        checkS cs = new checkS();
        if(s.isEmpty())
            s = "Please Enter a Number";
        else
        {
            int num = Integer.parseInt(s);
            if (cs.checkSquare(num) && ct.checkTriangular(num))
                s += " is Both Triangular and Square!";
            else if (!cs.checkSquare(num) && ct.checkTriangular(num))
                s += " is Triangular but not Square !";
            else if (cs.checkSquare(num) && !ct.checkTriangular(num))
                s += " is Square but not Triangular!";
            else
                s += " is neither Triangular nor Square!";
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
