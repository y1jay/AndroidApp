package com.yijun.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yijun.serializable.model.Person;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
EditText editName;
EditText editEmail;
Button btnSend;
RadioGroup radioGroup;
RadioButton radiof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        btnSend = findViewById(R.id.btnSend);
        radioGroup = findViewById(R.id.radioGroup);
        // 처음 실행시, 무조건 여자쪽에 체크 되어 있도록 하는 코드
        radiof = findViewById(R.id.radioF);
        radiof.setChecked(true);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름 가져오기
                String name = editName.getText().toString().trim();
                // 이메일 가져오기
                String email =editEmail.getText().toString().trim();
                // 어느 라디오 버튼에 체크 되어있는지, 체크된 버튼의 아이디 가져오기.
                int checked = radioGroup.getCheckedRadioButtonId();
                boolean isMail = false;
                if(checked ==R.id.radioM){
                    isMail = true;
                }else{
                    isMail = false;
                }
                //객체생성
                Person person = new Person(name, email,isMail);
                //위의 객체를, 새로운 엑티비티에 전달.
                Intent i = new Intent(MainActivity.this,Second.class);
                i.putExtra("PersonClass",person);
                startActivity(i);
            }
        });
    }
}









