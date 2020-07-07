package com.yijun.fingerspeed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int TAB_COUNT =100;
    TextView txtTimer;
    TextView txtCount;
    Button btnTap;
    TextView txtch;
    // 타이머 멤버변수
    CountDownTimer countDownTimer;
    long initCountMillis= 15000;
    int timerInterval = 1000;
    int tabCount = TAB_COUNT;
    int remainingTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimer=findViewById(R.id.txtTimer);
        txtCount=findViewById(R.id.txtCount);
        btnTap=findViewById(R.id.btnTap);
        txtch=findViewById(R.id.txtch);

        txtch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabCount<=0){
                    return;
                }
                tabCount = tabCount -10;
                txtCount.setText(""+tabCount);

                if(tabCount<=0){
                    countDownTimer.cancel();
                    showMyAlert("치트키를 썼습니다","치트키를 썼습니다");
                }

            }
        });

        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. 타이머 남은 시간이 0 인지 체크 0이면 아래 실행 안하면 됨
                //2. 숫자 감소시키고
                //3. 감소된 숫자를 화면에 표시
                if (remainingTime==0){
                    return;
                }

                if(tabCount==0){
                    return;
                }

                tabCount=(tabCount-1);
                txtCount.setText(""+tabCount);


                if(tabCount==0){

                    countDownTimer.cancel();
                    String message = "기록은"+(initCountMillis/1000-remainingTime)+"초입니다";
                    showMyAlert("우쭈쭈",message);
                    Toast.makeText(MainActivity.this,"성공",Toast.LENGTH_LONG).show();
                }
            }
        });

        countDownTimer= new CountDownTimer(initCountMillis,timerInterval) {// 밀리 초(second) 로 적는다 000붙이면 됨
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTime = (int)millisUntilFinished/1000;
                Log.i("Finger","남은시간 : "+millisUntilFinished);

                txtTimer.setText(""+remainingTime);


            }

            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this,"넌 병신", Toast.LENGTH_LONG).show();

                if(tabCount >0){
                   showMyAlert("당신은 등신입니다.","당신의 손가락은 한개입니까?");
                }


            }
        };
        countDownTimer.start();

        txtCount.setText(""+tabCount);
    }
    void showMyAlert(String title, String message){
        AlertDialog.Builder finishAlert = new AlertDialog.Builder(MainActivity.this);
        finishAlert.setTitle(title);
        finishAlert.setMessage(message);
        finishAlert.setPositiveButton("또 함", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                countDownTimer.start();
                tabCount=TAB_COUNT;
                txtCount.setText(""+tabCount);


            }

        });
        finishAlert.setNeutralButton("탈주", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        finishAlert.setCancelable(false);
        finishAlert.show();
    }
}
