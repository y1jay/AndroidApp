package com.yijun.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yijun.contactmanager.adapter.RecyclerViewAdapter;
import com.yijun.contactmanager.data.DatabaseHandler;
import com.yijun.contactmanager.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd;
    RecyclerView recyclerView; //메인 화면에 있는 리사이클러 뷰
    RecyclerViewAdapter recyclerViewAdapter; // 우리가 만든, 하나의 셀을 연결시키는 어댑터
    ArrayList<Contact> contactArrayList; // 데이터베이스에서 읽어온 주소록 정보를 저장할 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnadd);
        //리사이클러뷰 연결하고, 기본적인 셋팅.
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //데이터베이스에서 테이블에 저장된 데이터 읽어서, 어레이리스트에 저장
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        //우리가 만든 하나의 셀 표시하는 어댑터를, 리사이클러뷰에 연결
       recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        //우리가 만든 데이터베이스 핸들러 클래스를, 객체 생성한다.=>contacts 테이블이생성된다
        //데이터 하나를 만들어서, 디비(테이블)에 저장해 보자.
//        Contact new_contact = new Contact();
//        new_contact.setName("Jeremy");
//        new_contact.setPhoneNumber("010-1818-2828");
//
//        db.addContact(new_contact);//새로운 contact 만들어서 데이터를 저장
        // 저장된 데이터를 읽어오는 코드.
//        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//        ArrayList<Contact> contactList = db.getAllContects();
//        for(Contact contact : contactList) {   // contactList 안에 있는 데이터를 contact에 담아라
//            Log.i("adduser", "저장된 주소록의 데이터 id : " + contact.getId() +
//                    " 이름은 : " + contact.getName() + " 번호는 : " + contact.getPhoneNumber());

            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, AddContact.class);
                    startActivity(i);
                }

            });




        //DB테이블에 저장된 데이터 갯수 가져오는 메소드 호출.
     int count = db.getCount();
     Log.i("myDB","contact 테이블에 저장된 데이터 갯수"+count);


        // 업데이트 테스트
//        // 이름 바꾸기
//        contact.setName("홍길동");
//        // 업데이트 메소드 실행.
//        db.updateContact(contact);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //데이터베이스에서 테이블에 저장된 데이터 읽어서, 어레이리스트에 저장
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        //우리가 만든 하나의 셀 표시하는 어댑터를, 리사이클러뷰에 연결
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}









