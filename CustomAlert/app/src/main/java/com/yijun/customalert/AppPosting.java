package com.yijun.customalert;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yijun.customalert.adapter.RecyclerViewAdapter;
import com.yijun.customalert.data.CustomAlert;

import java.util.ArrayList;

public class AppPosting extends AppCompatActivity {
    EditText editTitle2;
    EditText editBody2;
    Button btnSave2;
    ArrayList<CustomAlert> customAlertsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_posting);

        editTitle2 = findViewById(R.id.editTitle2);
        editBody2 = findViewById(R.id.editBody2);
        btnSave2 = findViewById(R.id.btnSave2);
        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle2.getText().toString().trim();
                String body = editBody2.getText().toString().trim();
                if(title.isEmpty()|| body.isEmpty()){
                    Toast.makeText(AppPosting.this,"포스트를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("AAA","저장"+title+body);
//

                Intent i = getIntent();
               CustomAlert customAlert = new CustomAlert(0,0,title,body);

                Toast.makeText(AppPosting.this,"저장되었습니다",
                        Toast.LENGTH_SHORT).show();

                i.putExtra("CustomAlertClass",customAlert);
               setResult(RESULT_OK,i);

                finish();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public  boolean onOPtionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
                finish();
                return true;
        }
                return super.onOptionsItemSelected(item);
        }
    }

