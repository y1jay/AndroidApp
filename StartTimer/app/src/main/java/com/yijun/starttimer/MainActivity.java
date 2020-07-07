package com.yijun.starttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView txtview;
    TextView txtTimer;
    EditText editTimer;
    Button btnc;
    Button btns;
    CountDownTimer Timer;
    int et;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        txtview = findViewById(R.id.txtview);
        txtTimer = findViewById(R.id.txtTimer);
        editTimer = findViewById(R.id.editTimer);
        btnc=findViewById(R.id.btnc);
        btns = findViewById(R.id.btns);
        mp=MediaPlayer.create(this,R.raw.alarm);





        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etstr = editTimer.getText().toString().trim();
                et = Integer.parseInt(etstr);
                txtview.setText(""+et);
                Timer= new CountDownTimer(et*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        int remainingTime=(int)millisUntilFinished/1000;
                        txtview.setText(remainingTime+"초");



                    }

                    @Override
                    public void onFinish() {
                        //타이머 완료시, 소리와 애니메이션
                        mp.start();
                    }
                };
                Timer.start();

            }
        });
        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Timer.cancel();
                txtTimer.setText(""+et);
            }
        });


    }
}
