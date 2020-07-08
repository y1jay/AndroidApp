package com.yijun.emplo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yijun.emplo2.adapter.RecyclerViewAdapter;
import com.yijun.emplo2.list.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView txtList;
RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RequestQueue requestQueue;
    ArrayList<Employee> empList = new ArrayList<>();
    public static final String URL = "http://dummy.restapiexample.com/api/v1/employees";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        final JsonObjectRequest jsonObjectRequest=
                new JsonObjectRequest(Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("AAA","WWWWWWWWWW");
                                Log.i("AAA","result : "+ response.toString());
                                try {
                                    String value = response.getString("status");
                                    Log.i("AAA","key status 의 값은 : "+value);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    JSONArray dataArray = response.getJSONArray("data");
                                    Log.i("AAA","key data 의 값은 : "+dataArray);
                                    for(int i = 0; i<dataArray.length();i++){
                                        JSONObject object = dataArray.getJSONObject(i);
                                        String name = object.getString("employee_name");
                                        int id = object.getInt("id");
                                        int age = object.getInt("employee_age");
                                        int salary = object.getInt("employee_salary");
                                        Employee employee = new Employee(id, name, age, salary);
                                        empList.add(employee);

                                    }
                                    recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, empList);
                                    recyclerView.setAdapter(recyclerViewAdapter);

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

        Log.i("AAA","QQQQQQQQ");
    }
}
