package com.yijun.getdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView txtData2;
    EditText editSend2;
    Button btnThird;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        txtData2 = findViewById(R.id.txtData2);
        editSend2 = findViewById(R.id.editSend2);
        btnThird = findViewById(R.id.btnThird);

        Intent i = getIntent();
        String data2 = i.getStringExtra("data2");
        int hiddenData2 = i.getIntExtra("hiddenData2",0);

        txtData2.setText(data2);

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data2 = editSend2.getText().toString().trim();
                Intent i =getIntent();
                i.putExtra("secondData",data2);
                setResult(RESULT_OK, i);

                finish();
            }
        });
    }
}
