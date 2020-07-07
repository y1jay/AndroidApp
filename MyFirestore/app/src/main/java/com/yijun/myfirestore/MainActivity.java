package com.yijun.myfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText editTitle;
EditText editThought;
Button btnSave;
TextView txtTitle;
TextView txtThought;
Button btnLoad;

public static final String KEY_TITLE = "title";
public static final String KEY_THOUGHT = "thought";
FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTitle = findViewById(R.id.editTitle);
        editThought=findViewById(R.id.editThought);
        btnSave = findViewById(R.id.btnSave);
        txtTitle = findViewById(R.id.txtTitle);
        txtThought = findViewById(R.id.txtThought);
        btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String thought =editThought.getText().toString().trim();
                Map<String, Object> data = new HashMap<>();
                data.put(KEY_TITLE, title);
                data.put(KEY_THOUGHT, thought);

                db.collection("Journal")
                        .document("First Thought")
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,
                                        "저장성공",Toast.LENGTH_SHORT).show();
                                editTitle.setText("");
                                editThought.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Journal")
                        .document("First Thought")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                String title = documentSnapshot.getString(KEY_TITLE);
                                String thought = documentSnapshot.getString(KEY_THOUGHT);

                                txtTitle.setText(title);
                                txtThought.setText(thought);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
    }
}
