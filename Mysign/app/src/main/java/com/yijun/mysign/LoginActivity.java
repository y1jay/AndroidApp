package com.yijun.mysign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editEmail;
    EditText editPasswd;
    Button btnLogin;
    CheckBox checkAutoLogin;

    String savedEmail;
    String savedPassed;
    // 쉐어드 프리퍼런스를 멤버변수로 뺀다.
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPasswd = findViewById(R.id.editPasswd);
        btnLogin = findViewById(R.id.btnLogin);
        checkAutoLogin = findViewById(R.id.checkAutoLogin);

        sp = getSharedPreferences("regist_pref", MODE_PRIVATE);
        savedEmail = sp.getString("email", null);
        savedPassed = sp.getString("passwd", null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 에디트 텍스트에 유저가 입력한 이메일과 비번을 가져와서
                // 쉐어드프리퍼런스에 저장되어 있던, 저장된 이메일과 비번을 서로 비교.
                String email = editEmail.getText().toString().trim();
                String passwd = editPasswd.getText().toString().trim();

                if(savedEmail != null && savedPassed != null &&
                        savedEmail.equals(email) && savedPassed.equals(passwd)){
                    // 로그인 완료 화면 만들어서, 이메일 정보를 전달해 준다.

                    if(checkAutoLogin.isChecked()){
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("auto_login", true);
                        editor.apply();
                    } else {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("auto_login", false);
                        editor.apply();
                    }

                    Intent i = new Intent(LoginActivity.this, AfterLogin.class);
                    i.putExtra("email", email);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "이메일이나 비번이 일치하지 않습니다.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        // 1. 자동로그인이, 쉐어드프리퍼런스에 저정되어 있는지 정보를 가져온다.
        boolean autoLogin = sp.getBoolean("auto_login", false);
        // 2. 자동로그인이 true 로 되어있으면, 이메일과 비밀번호를 에디트텍스트에 표시
        // 3. 체크박스에도, 체크표시를 합니다.
        Log.i("auto_login", "저정된 오토로그인 정보는 : " + autoLogin);
        if(autoLogin == true){
            editEmail.setText(savedEmail);
            editPasswd.setText(savedPassed);
            checkAutoLogin.setChecked(true);
        }


    }
}
