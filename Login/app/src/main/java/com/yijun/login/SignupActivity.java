package com.yijun.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtWelcome = findViewById(R.id.txtWelcome);
        String email = getIntent().getStringExtra("email");
        String passwd = getIntent().getStringExtra("passwd");

        txtWelcome.setText(email + "님 환영합니다.");

        SharedPreferences sp = getSharedPreferences("regist_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email", email);
        editor.putString("passwd", passwd);
        editor.apply();
    }

}
