package com.yijun.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yijun.contactmanager.data.DatabaseHandler;
import com.yijun.contactmanager.model.Contact;
import com.yijun.contactmanager.util.Util;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
EditText updateName;
EditText updatePhone;
Button btnUpdate;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateName = findViewById(R.id.updateName);
        updatePhone = findViewById(R.id.updatePhone);
        btnUpdate = findViewById(R.id.btnUpdate);

        id = getIntent().getIntExtra("id",-1);
        String name= getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        updateName.setText(name);
        updatePhone.setText(phone);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터베이스에 업데이트
                // 유저가 변경할 수 있는 이름과 폰번을, 에디트텍스트에서 가져온다.
                String name = updateName.getText().toString().trim();
                String phone = updatePhone.getText().toString().trim();
                // 디비 핸들러 클래스를 통해서 업데이트
                DatabaseHandler db= new DatabaseHandler(UpdateActivity.this);
                Contact contact = db.getContact(id);
                contact.setName(name);
                contact.setPhoneNumber(phone);
                db.updateContact(contact);

                Toast.makeText(UpdateActivity.this, "수정 되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
