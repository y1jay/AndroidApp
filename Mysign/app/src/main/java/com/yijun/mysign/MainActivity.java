package com.yijun.mysign;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPasswd1;
    EditText editPasswd2;
    Button btnReg;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPasswd1 = findViewById(R.id.editPasswd1);
        editPasswd2 = findViewById(R.id.editPasswd2);
        btnReg = findViewById(R.id.btnReg);
        btnLogin = findViewById(R.id.btnLogin);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 이메일주소, 비번1, 비번2 값 다 가져오자.
                // 2. 이메일 맞는지 체크
                // 3. 비밀번호 길이 체크
                // 4. 비밀번호 2개 다 맞는지 체크
                // 5. 다 이상없으면, 다음 액티비티로 넘어감.

                String email = editEmail.getText().toString();
                String passwd1 = editPasswd1.getText().toString();
                String passwd2 = editPasswd2.getText().toString();

                if(email.contains("@") == false){
                    Toast.makeText(MainActivity.this,
                            "이메일 형식에 맞게 작성해주세요.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(passwd1.length() < 6 || passwd1.length() > 12){
                    Toast.makeText(MainActivity.this,
                            "비밀번호 길이는 6자리 이상, 12자리 이하로 작성해주세요.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(passwd1.compareTo(passwd2) != 0){
                    Toast.makeText(MainActivity.this,
                            "비밀번호가 서로 일치해야 합니다.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                // 두번째 액티비티 만든 후, 이 코드 작성.
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("email", email);
                i.putExtra("passwd", passwd1);
                startActivity(i);
                // 현재의 액티비티를 종료
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
