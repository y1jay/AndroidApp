package com.yijun.customalert;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.yijun.customalert.adapter.RecyclerViewAdapter;
import com.yijun.customalert.data.CustomAlert;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ArrayList<CustomAlert> customAlertsArrayList = new ArrayList<>();
    public static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private AlertDialog dialog;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
   EditText editTitle;
   EditText editBody;
   TextView txtNO ;
   TextView txtYES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length();i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int userId= jsonObject.getInt("userId");
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                String body = jsonObject.getString("body");

                                CustomAlert customAlert = new CustomAlert(userId,id,title,body);
                                customAlertsArrayList.add(customAlert);

//                                ArrayList<CustomAlert> customAlerts= b.
                            } catch (JSONException e) {
                                e.printStackTrace();




                            }
                        }

                        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertsArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

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
    public void createPopupDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder
                (MainActivity.this);
        View alertView = getLayoutInflater().inflate(R.layout.enter_post,null);
        editTitle = alertView.findViewById(R.id.editTitle);
        editBody = alertView.findViewById(R.id.editBody);
        txtNO = alertView.findViewById(R.id.txtNO);
        txtYES = alertView.findViewById(R.id.txtYES);

        txtYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString().trim();
                String body= editBody.getText().toString().trim();
                if(title.isEmpty()|| body.isEmpty()){
                    Toast.makeText(MainActivity.this,"포스트를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("AAA","저장"+title+body);
//                int quantity_int = Integer.parseInt(title);
//                int size_int = Integer.parseInt(body);

               CustomAlert customAlert = new CustomAlert();
                Log.i("AAA","저장"+customAlert);
                customAlert.setTitle(title);
                customAlert.setBody(body);
                customAlertsArrayList.add(customAlert);
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertsArrayList);
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
        }else if(id ==R.id.menu_add){
            Intent i = new Intent(MainActivity.this,AppPosting.class);

            startActivityForResult(i,0);

        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
           CustomAlert customAlert = (CustomAlert) data.getSerializableExtra("CustomAlertClass");

            customAlertsArrayList.add(customAlert);
            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, customAlertsArrayList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}
