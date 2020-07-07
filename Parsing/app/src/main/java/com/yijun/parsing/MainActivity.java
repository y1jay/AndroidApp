package com.yijun.parsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //https://jsonplaceholder.typicode.com/todos

    String url = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest
                (Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("Parsing",response.toString());
                        try {
                            int userId= response.getInt("userId");
                            String title= response.getString("id");
                            boolean completed =response.getBoolean("completed");
                            Log.i("Parsing","UserId : "+userId);
                            Log.i("Parsing","id : "+userId);
                            Log.i("Parsing","title : "+userId);
                            Log.i("Parsing","completed : "+userId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Parsing","error : "+error.toString());

            }
        });


        requestQueue.add(jsonObjectRequest);



    }
}
