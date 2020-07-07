package com.yijun.calculator;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        EditText txtPnum;
        EditText txtEnum;
        Button btnC;
        TextView txtRE;

        int calculat =0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            txtPnum=findViewById(R.id.txtPnum);
            txtEnum=findViewById(R.id.txtEnum);
            btnC=findViewById(R.id.btnC);
            txtRE=findViewById(R.id.txtRe);

            btnC.setOnClickListener(new View.OnClickListener() {
                @Override
                                 public void onClick(View v) {
                        String Pnum = txtPnum.getText().toString();
                        String Enum = txtEnum.getText().toString();

                        if (Pnum.isEmpty() ||Enum.isEmpty()){
                            Toast.makeText(MainActivity.this, "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                            return; // null값일때 토스트로 뜨게하는 문법
                        }

                    calculat = (calculat+1);
                    if(calculat >=5){
                        AlertDialog.Builder calAlert = new AlertDialog.Builder(MainActivity.this);

                        calAlert.setTitle(R.string.title);
                        calAlert.setPositiveButton(R.string.alertbtn, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        calAlert.setNeutralButton("취소",null);
                        calAlert.setCancelable(false);// 유저가 화면의 다른곳을 눌러도 알러트다이얼로그가 없어지지않게한다
                        calAlert.show();
                        return;
                    }



                    double eynum = Double.parseDouble(Enum);
                    double pnum= Double.parseDouble(Pnum);
                    double REsult = Double.parseDouble(""+eynum*pnum);

                    txtRE.setText(""+ REsult/100);


                }
            });
        }
    }


