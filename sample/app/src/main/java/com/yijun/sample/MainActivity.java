package com.yijun.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
EditText Txtborn;
TextView Txtage;
Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Txtborn=findViewById(R.id.Txtborn);
        Txtage=findViewById(R.id.Txtage);

        btnClick=findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //버튼 눌렀을때 해야 될 일을 여기에 작성
                // 1. 에디트텍스트에 적혀있는 글자 가져오기
                String girlyear= Txtborn.getText().toString();
                Log.i("nample","HiHi"+girlyear);
                // 2. 이번년도에서, 가지고온 년도를 뺀다
                int girlage = 2020 - Integer.parseInt(girlyear);
                Log.i("nample","계산한 나이는"+girlage);
                // 3. 텍스트뷰에 표시해야겠다.
               Txtage.setText(""+girlage);
                // 4. 에디트텍스트에서 유저가 쓴 글자 지운다.
                Txtborn.setText("");


            }
        });


    }
}
