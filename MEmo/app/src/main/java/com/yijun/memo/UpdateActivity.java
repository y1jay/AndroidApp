package com.yijun.memo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yijun.memo.data.DatabaseHandler;
import com.yijun.memo.model.Contact;

public class UpdateActivity extends AppCompatActivity {
    EditText updateTitle;
    EditText updateMemo;
    Button btnUpdate;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateTitle = findViewById(R.id.updateTitle);
        updateMemo = findViewById(R.id.updateMemo);
        btnUpdate = findViewById(R.id.btnUpdate);

        id = getIntent().getIntExtra("id",-1);
        String title= getIntent().getStringExtra("title");
        String memo = getIntent().getStringExtra("memo");
        updateTitle.setText(title);
        updateMemo.setText(memo);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = updateTitle.getText().toString().trim();
                String memo = updateMemo.getText().toString().trim();

                DatabaseHandler db= new DatabaseHandler(UpdateActivity.this);
                Contact contact = db.getContact(id);
                contact.setTitle(title);
                contact.setMemo(memo);
                db.updateContact(contact);

                Toast.makeText(UpdateActivity.this, "수정 되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

