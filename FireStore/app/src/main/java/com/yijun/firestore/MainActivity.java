package com.yijun.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText editTitle;
EditText editThought;
Button btnSave;

public static final String KEY_TITLE = "title";
    public static final String KEY_THOUGHT="thought";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTitle = findViewById(R.id.editTitle);
        editThought = findViewById(R.id.editThought);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String thought = editThought.getText().toString().trim();

                //가이드에 맵으로 넣으라고 나와있다.
                Map<String,Object> data = new HashMap<>();
                data.put(KEY_TITLE, title);
                data.put(KEY_THOUGHT,thought);
                db.collection("Journal")
                        .document("First Thoughts")
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this,
                                        "잘 저장되었습니다.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            Log.i("AAA",e.toString());
                            }
                        });
            }
        });

    }
}
