package com.yijun.fab;

import android.content.DialogInterface;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editItem;
    EditText editQuantity;
    EditText editColor;
    EditText editSize;
    Button btnSave;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              createPopupDialog();
            }
        });
    }
    //커스텀 다이얼로그
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder
                (MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.enter_item,null);
        editItem = alertView.findViewById(R.id.editItem);
        editQuantity =alertView.findViewById(R.id.editQuantity);
        editColor = alertView.findViewById(R.id.editColor);
        editSize = alertView.findViewById(R.id.editSize);
        btnSave = alertView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String item = editItem.getText().toString().trim();
               String quantity = editQuantity.getText().toString().trim();
               String color = editColor.getText().toString().trim();
               String size = editSize.getText().toString().trim();

               if(quantity.isEmpty()|| size.isEmpty()){
                   Toast.makeText(MainActivity.this,"숫자를 입력하세요",
                           Toast.LENGTH_SHORT).show();
                   return;
               }
               int quantity_int = Integer.parseInt(quantity);
               int size_int = Integer.parseInt(size);

                Log.i("AAA",item+", "+quantity+", "+color+", "+size);

                //다이얼로그 없애기
                dialog.cancel();
            }
        });

        alert.setView(alertView);

        dialog=alert.create();
//                alert.setCancelable(false);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.menu_add){
            Toast.makeText(MainActivity.this,"추가버튼 눌렀음.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.menu_delete){
            Toast.makeText(MainActivity.this,"삭제버튼 눌렀음.",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
