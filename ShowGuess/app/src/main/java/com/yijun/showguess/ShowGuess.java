package com.yijun.showguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    TextView txtRecv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        txtRecv=findViewById(R.id.txtRecv);

        Intent i =getIntent();
        String guess = i.getStringExtra("key");
        int number= i.getIntExtra("number",0);
        String mystr = i.getStringExtra("str");
        boolean myboo = i.getBooleanExtra("boo",false);

        String all = guess + " " + number + " " + mystr + " " + myboo;

        txtRecv.setText(all);


    }
}
