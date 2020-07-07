package com.yijun.fireegg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yijun.fireegg.person.Contact;

public class UpdateActivity extends AppCompatActivity {
    EditText updateName;
    EditText updateNumber;
    Button btnUpdate;
    String id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateName = findViewById(R.id.updateName);
        updateNumber = findViewById(R.id.updateNumber);
        btnUpdate = findViewById(R.id.btnUpdate);
        Contact contact = (Contact) getIntent().getSerializableExtra("Contact");
        id = contact.getId();
        String name = contact.getName();
        String number = contact.getNumber();

        updateName.setText(name);
        updateNumber.setText(number);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UpName = updateName.getText().toString().trim();
                String UpNumber = updateNumber.getText().toString().trim();

                Contact contact = new Contact(UpName, UpNumber);
                db.collection("Contact").document(id).set(contact).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UpdateActivity.this,
                                        "수정 되었습니다.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

    }
}