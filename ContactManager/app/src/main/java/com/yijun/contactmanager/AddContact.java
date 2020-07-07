package com.yijun.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yijun.contactmanager.data.DatabaseHandler;
import com.yijun.contactmanager.model.Contact;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity {

    EditText addname;
    EditText addphoneNumber;
    Button btnsave;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        addname = findViewById(R.id.addname);
        addphoneNumber = findViewById(R.id.addphoneNumber);
        btnsave = findViewById(R.id.btnsave);

        db = new DatabaseHandler(AddContact.this);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addname.getText().toString().trim();
                String phoneNumber = addphoneNumber.getText().toString().trim();

                if(name.isEmpty()){
                    Toast.makeText(AddContact.this,"이름을 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;

                } if(phoneNumber.isEmpty()){
                    Toast.makeText(AddContact.this,"전화번호를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact new_contact = new Contact();
                new_contact.setName(name);
                new_contact.setPhoneNumber(phoneNumber);

                db.addContact(new_contact);//새로운 contact 만들어서 데이터를 저장
                Toast.makeText(AddContact.this, "저장되었습니다", Toast.LENGTH_LONG).show();
                ArrayList<Contact> contactList = db.getAllContacts();
                for (Contact contact : contactList) {   // contactList 안에 있는 데이터를 contact에 담아라
                    Log.i("adduser", "저장된 주소록의 데이터 id : " + contact.getId() +
                            " 이름은 : " + contact.getName() + " 번호는 : " + contact.getPhoneNumber());


                    finish();
                }

            }

        });

    }
    public void onBackPressed() {
        super.onBackPressed();
        ArrayList<Contact> contactList = db.getAllContacts();
        for(Contact contact : contactList) {   // contactList 안에 있는 데이터를 contact에 담아라
            Log.i("adduser", "저장된 주소록의 데이터 id : " + contact.getId() +
                    " 이름은 : " + contact.getName() + " 번호는 : " + contact.getPhoneNumber());
        }
    }

}