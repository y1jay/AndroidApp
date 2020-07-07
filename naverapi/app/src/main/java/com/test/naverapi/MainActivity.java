package com.test.naverapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.test.naverapi.adapter.RecyclerViewAdapter;
import com.test.naverapi.data.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txt;
    RadioGroup radioGroup;
    RadioButton chineseG;
    RadioButton english;
    RadioButton chineseB;
    RadioButton thai;
    RecyclerView recyclerView;
    ImageView img;
    ArrayList<Model> modelArrayList = new ArrayList<>();
    RecyclerViewAdapter recyclerViewAdapter;
    RequestQueue requestQueue;
    String baseUrl = "https://openapi.naver.com/v1/papago/n2mt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        radioGroup = findViewById(R.id.radioGroup);
        chineseG = findViewById(R.id.chineseG);
        chineseB = findViewById(R.id.chineseB);
        english = findViewById(R.id.english);
        thai = findViewById(R.id.thai);
        img =findViewById(R.id.img);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        //JSONObject , JSONArrayRequest 이것은 전에 사용해봤다
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check =radioGroup.getCheckedRadioButtonId();
                String txt1 = txt.getText().toString().trim();
                if (txt1.isEmpty()){
                    Toast.makeText(MainActivity.this,"번역할 문자가 없읍니다.",
                            Toast.LENGTH_SHORT).show();
                }
                if (check == R.id.chineseG){
                    Toast.makeText(MainActivity.this,"중국어 간체로 번역합니다",
                            Toast.LENGTH_SHORT).show();
                }else if (check == R.id.english){
                    Toast.makeText(MainActivity.this,"영어로 번역합니다",
                            Toast.LENGTH_SHORT).show();
                }else if (check == R.id.chineseB){
                    Toast.makeText(MainActivity.this,"중국어 번체로 번역합니다",
                            Toast.LENGTH_SHORT).show();
                }else if (check == R.id.thai){
                    Toast.makeText(MainActivity.this,"태국어로 번역합니다",
                            Toast.LENGTH_SHORT).show();
                }

        // 문자열로 JSON을 받아오는 클래스 : StringRequest
        // 헤더에 데이터를 넣어서 요청하는 방법
        StringRequest request = new StringRequest(
                Request.Method.POST,
                baseUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("AAA",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject message = jsonObject.getJSONObject("message");
                            JSONObject result = message.getJSONObject("result");
                            String text = result.getString("translatedText");
                            Model model = new Model(txt.toString(),text);

                            modelArrayList.add(model);

                            recyclerViewAdapter = new RecyclerViewAdapter(
                                    MainActivity.this,modelArrayList);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            //translatedText 항목을 뽑아 올 수 있습니다.
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("AAA",error.toString());
                    }
                }
        ){
            // 네이버 API의 헤더 셋팅 부분을 여기에 작성한다.

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                params.put("X-Naver-Client-Id", "oUKUuIRqdOS2gfOyj3gK");
                params.put("X-Naver-Client-Secret", "hPoVLjxjoH");
                return params;
            }
            //네이버에 요청할 파라미터를 셋팅한다.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                int check =radioGroup.getCheckedRadioButtonId();
                final String txt1 = txt.getText().toString().trim();
                if (check == R.id.chineseG){
                    params.put("source","ko");
                params.put("target","zh-CN");
                    params.put("text",txt1);

                    return params;

                }else if (check == R.id.english){

                    params.put("source","ko");
                params.put("target","en");
                    params.put("text",txt1);
                    return params;
                }else if (check == R.id.chineseB){

                    params.put("source","ko");
                params.put("target","zh-TW");
                    params.put("text",txt1);
                    return params;
                }else if (check == R.id.thai){

                    params.put("source","ko");
                params.put("target","th");}
                params.put("text",txt1);
                return params;
            }

        };


        // 실제로 네트워크로 API호출(요청)
        requestQueue.add(request);
            }
        });
    }

}
