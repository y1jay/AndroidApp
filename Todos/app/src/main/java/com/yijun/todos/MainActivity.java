package com.yijun.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yijun.todos.adapter.RecyclerViewAdapter;
import com.yijun.todos.data.Todos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    ArrayList<Todos> todosArrayList = new ArrayList<>();
    public static final String URL = "https://jsonplaceholder.typicode.com/todos";

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.
                GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("AAA","어레이"+response.toString());
                        for (int i = 0; i<response.length();i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int userId = jsonObject.getInt("userId");
                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                boolean completed = jsonObject.getBoolean("completed");

                                Todos todos = new Todos(userId, id,title,completed);
                                todosArrayList.add(todos);
                                Log.i("AAA","ddd"+todos);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, todosArrayList);
                        recyclerView.setAdapter(recyclerViewAdapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

    }
}