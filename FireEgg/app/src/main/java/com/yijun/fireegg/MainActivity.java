package com.yijun.fireegg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.yijun.fireegg.adapter.RecyclerViewAdapter;
import com.yijun.fireegg.person.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button btnSave;
    EditText editName;
    EditText editNumber;
    TextView txtNO ;
    TextView txtYES;

    private AlertDialog dialog;
ArrayList<Contact> contactArrayList = new ArrayList<>();
public static final String KEY_NAME = "Name";
public static final String KEY_NUMBER ="Number";
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        db.collection("Contact").get().addOnSuccessListener
                (new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                    Contact contact = snapshots.toObject(Contact.class);

                    contactArrayList.add(contact);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupDialog();
            }
        });

    }
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder
                (MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.add_person,null);
        editName = alertView.findViewById(R.id.editName);
        editNumber = alertView.findViewById(R.id.editNumber);
        txtNO = alertView.findViewById(R.id.txtNO);
        txtYES = alertView.findViewById(R.id.txtYES);

        txtYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = editName.getText().toString().trim();
                final String Number= editNumber.getText().toString().trim();

                if(Name.isEmpty()|| Number.isEmpty()){
                    Toast.makeText(MainActivity.this,"정보를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                final Contact contact = new Contact(Name,Number);
                db.collection("Contact").add(contact)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                contact.setName(Name);
                                contact.setNumber(Number);
                                contactArrayList.add(contact);
                                Toast.makeText(MainActivity.this,"저장되었습니다",
                                        Toast.LENGTH_SHORT).show();
                                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
                                recyclerView.setAdapter(recyclerViewAdapter);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
                recyclerView.setAdapter(recyclerViewAdapter);
                //다이얼로그 없애기
                dialog.cancel();
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
//                alert.setCancelable(false);
        dialog.setCancelable(false);
        dialog.show();


    }
    @Override
    protected void onResume() {
        super.onResume();
        //데이터베이스에서 테이블에 저장된 데이터 읽어서, 어레이리스트에 저장
        contactArrayList.clear();
        db.collection("Contact")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots){
                            String id = snapshots.getId();
                            Log.i("AAA",id);
                            Contact contact = snapshots.toObject(Contact.class);
                            contact.setId(id);
                            contactArrayList.add(contact);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        //우리가 만든 하나의 셀 표시하는 어댑터를, 리사이클러뷰에 연결
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);


    }

}
