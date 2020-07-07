package com.yijun.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yijun.employee.adapter.RecyclerViewAdapter;
import com.yijun.employee.data.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// 네트워크 통신 라이브러리
    RequestQueue requestQueue;

   RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Employee> empList = new ArrayList<>();

   public static final String URL = "http://dummy.restapiexample.com/api/v1/employees";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // 1, 발리의 리퀘스트큐 객체를 가져온다.
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        // 2. 제이슨 리퀘스트 객체 생성 : 서버로부터 응답 받았을때 어떻게 처리할지를 코딩.
        final JsonObjectRequest jsonObjectRequest=
                // http프로토콜의 get메소드설정,
                // 요청할 URL,
                // 요청할때 필요한 json,
                // 서버로부터 정상적 응답 받았을때,
                // 서버로부터 응답 에러발생시
                new JsonObjectRequest(Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("AAA","WWWWWWWWWW");
                                Log.i("AAA","result : "+ response.toString());
                                // status에 무슨 값이 담겨있는지 로그 확인
                                try {
                                    String value = response.getString("status");
                                    Log.i("AAA","key status 의 값은 : "+value);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // 키 데이터의 값을 가져오는 법
                                try {
                                    JSONArray dataArray = response.getJSONArray("data");
                                    Log.i("AAA","key data 의 값은 : "+dataArray);
                                    //Data Parsing 데이터 파싱
                                    for(int i = 0; i<dataArray.length();i++){
                                        JSONObject object = dataArray.getJSONObject(i);
                                      //  Log.i("AAA","key data 의 값은 : "+object);
                                        //직원의 이름 24명 다 로그에 찍어보자
                                        String name = object.getString("employee_name");
                                        Log.i("AAA","루프"+i+"번째 사람의 이름 : "+name);
                                       int id = object.getInt("id");
                                        Log.i("AAA",id+"번");
                                        int salary = object.getInt("employee_salary");
                                        Log.i("AAA",name+"의 연봉은 : "+salary);
                                        int age = object.getInt("employee_age");
                                        Log.i("AAA",name+"의 나이는 : "+age);

                                        // 파싱한 데이터를, 클래스의 객체로 저장
                                        Employee employee = new Employee(id, name, salary, age);
                                        empList.add(employee);

                                    }
                                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, empList);
                                    recyclerView.setAdapter(recyclerViewAdapter);
                                    //for 루프 끝나야, 모든 데이터가 다 어레이리스트에 들어있다.
                                    // 이렇게 어레이리스트에 데이터가 다 들어있는 후에,
                                    // 리사이클러뷰를 표시

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
      // 네트워크 갔다와서, 어레이이스트레 데이터가 쌓여있는 상태 X => 주의
        //요 아래에다가 어댑터 연결하는 코드 넣으면 절대 안됩니다.
        Log.i("AAA","QQQQQQQQ");
    }
}
