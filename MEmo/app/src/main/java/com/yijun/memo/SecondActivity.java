package com.yijun.memo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yijun.memo.data.DatabaseHandler;
import com.yijun.memo.model.Contact;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
EditText editTitle;
EditText editMemo;
Button btnSave;
DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editTitle = findViewById(R.id.editTitle);
        editMemo =findViewById(R.id.editMemo);
        editMemo.setHorizontallyScrolling(false);
        btnSave = findViewById(R.id.btnSave);
        db= new DatabaseHandler(SecondActivity.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String memo = editMemo.getText().toString().trim();
                if (title.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "제목을 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;

                }
                if (memo.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "메모를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact new_contact = new Contact();
                new_contact.setTitle(title);
                new_contact.setMemo(memo);

                db.addContact(new_contact);//새로운 contact 만들어서 데이터를 저장
                Toast.makeText(SecondActivity.this, "저장되었습니다", Toast.LENGTH_LONG).show();
                ArrayList<Contact> contactList = db.getAllContacts();
                for (Contact contact : contactList) {
                    Log.i("AddMemo","저장"+title);
                    finish();
                }
            }
        });
    }
}
