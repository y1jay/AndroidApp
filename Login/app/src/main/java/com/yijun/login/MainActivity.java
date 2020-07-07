package com.yijun.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPasswd1;
    EditText editPasswd2;
    Button btnsign;
    Button btnLogin;
    EditText editema;
    EditText editpwd;
    TextView txtNO ;
    TextView txtYES;
    AlertDialog dialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPasswd1 = findViewById(R.id.editPasswd1);
        editPasswd2 = findViewById(R.id.editPasswd2);
        btnsign = findViewById(R.id.btnsign);
        btnLogin = findViewById(R.id.btnLogin);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = editEmail.getText().toString();
                String passwd1 = editPasswd1.getText().toString();
                String passwd2 = editPasswd2.getText().toString();

                if(email.contains("@") == false){
                    Toast.makeText(MainActivity.this,
                            "이메일을 적어주세요",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(passwd1.length() < 6 || passwd1.length() > 12){
                    Toast.makeText(MainActivity.this,
                            "비밀번호 길이는 6자리 이상, 12자리 이하로 작성하세요",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(passwd1.compareTo(passwd2) != 0){
                    Toast.makeText(MainActivity.this,
                            "비밀번호가 일치하지 않습니다.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                SharedPreferences sp = getSharedPreferences("regist_pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", email);
                editor.putString("passwd", passwd1);
                editor.apply();
                Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                i.putExtra("email", email);
                i.putExtra("passwd", passwd1);
                startActivity(i);
                finish();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog();
            }
        });


    }
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder
                (MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.enter_login,null);
        editema = alertView.findViewById(R.id.editEma);
        editpwd = alertView.findViewById(R.id.editpwd);
        txtNO = alertView.findViewById(R.id.txtNO);
        txtYES = alertView.findViewById(R.id.txtYES);

        txtYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editema.getText().toString().trim();
                String passwd= editpwd.getText().toString().trim();
                sp = getSharedPreferences("regist_pref", MODE_PRIVATE);
                String savedEmail = sp.getString("email", null);
                String savedPassed = sp.getString("passwd", null);
                if(email.isEmpty()|| passwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"이메일과 비밀번호를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (email.compareTo(savedEmail)!=0){
                    Toast.makeText(MainActivity.this,"이메일이 맞지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }if (passwd.compareTo(savedPassed)!=0){
                    Toast.makeText(MainActivity.this,"비밀번호가 맞지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    Intent i = new Intent(MainActivity.this, SignupActivity.class);
                    i.putExtra("email", savedEmail);
                    i.putExtra("passwd", savedPassed);
                    startActivity(i);
                    finish();
                    dialog.cancel();
                }

            }
        });
        txtNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        alert.setView(alertView);

        dialog=alert.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
