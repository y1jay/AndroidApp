package com.yijun.memo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yijun.memo.adapter.RecyclerViewAdapter;
import com.yijun.memo.data.DatabaseHandler;
import com.yijun.memo.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnAdd;
EditText editSelect;

RecyclerView recyclerView;
RecyclerViewAdapter recyclerViewAdapter;
ArrayList<Contact> contactArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        editSelect = findViewById(R.id.editSelect);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
        editSelect.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String select = editSelect.getText().toString().trim();
                DatabaseHandler db =new DatabaseHandler(MainActivity.this);
                contactArrayList = db.getLike(select);
                db.getLike(select);


                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        imgSelect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String select = editSelect.getText().toString().trim();
//                DatabaseHandler db =new DatabaseHandler(MainActivity.this);
//                if(select.isEmpty()){
//                    Toast.makeText(MainActivity.this,"내용을 입력하세요",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                db.getLike(select);
//                contactArrayList = db.getLike(select);
//
//                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
//                recyclerView.setAdapter(recyclerViewAdapter);
//
//            }
//        });
//        imgCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editSelect.setText("");
//                DatabaseHandler db = new DatabaseHandler(MainActivity.this);
//                contactArrayList = db.getAllContacts();
//                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
//                recyclerView.setAdapter(recyclerViewAdapter);
//            }
//        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        contactArrayList = db.getAllContacts();

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    }
