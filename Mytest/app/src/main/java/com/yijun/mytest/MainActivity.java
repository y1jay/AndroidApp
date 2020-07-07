package com.yijun.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView txt_title;
EditText editText;
EditText editText2;
ImageView imgCenter;
    Button btnclick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= findViewById(R.id.editText);
        txt_title= findViewById(R.id.txt_title);
        editText2= findViewById(R.id.editText2);
       btnclick= findViewById(R.id.btnclick);
        imgCenter = findViewById(R.id.imgCenter);
        //버튼을 클릭하면, 로그에 "버튼 클릭됨"을 찍도록 코드 작성
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyClickApp","버튼을 눌렀습니다.");
                // 텍스트를 가져오는 것


                String name = editText.getText().toString();
                String phone = editText2.getText().toString();

                txt_title.setText(name+"\n"+phone);

                           Toast.makeText(MainActivity.this,name+"\n"+phone,
                        Toast.LENGTH_LONG).show();

                           imgCenter.setImageResource(R.drawable.me);

            }
        });
    }
}
