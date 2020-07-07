package com.yijun.fireegg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText updateName;
    EditText updatePhone;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateName = findViewById(R.id.updateName);
        updatePhone = findViewById(R.id.updatePhone);
        btnUpdate = findViewById(R.id.btnUpdate);


    }
}
