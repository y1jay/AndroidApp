package com.yijun.youtube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yijun.youtube.adapter.RecyclerViewAdapter;
import com.yijun.youtube.data.Youtube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {
    EditText search;
    ImageView imgsearch;
    String youtubeUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyBzLBlYgOHxau2iB9_8MKDkzANuCSfeJPc&maxResults=20&type=video&regionCode=KR";
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Youtube> youtubeArrayList = new ArrayList<>();
    // 유튜브에서 넥스트페이지토큰이라는 것을 받으면 항상 멤버변수에 저장해준다
    String nextPageToken;
    String pageToken = "";
    String searchUrl = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if(lastPosition+1 == totalCount){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                    if(nextPageToken.compareTo(pageToken)!=0){
                        pageToken = nextPageToken;
                        String url = "";
                        if (searchUrl.isEmpty()){
                             url = youtubeUrl+"&pageToken="+pageToken;
                        }else{
                             url = searchUrl+"&pageToken="+pageToken;
                        }
                        // 이 url로 네트워크 데이터 요청.
                        Log.i("AAA",url);
                        addNetworkData(url);
                    }
                }
            }
        });


        search = findViewById(R.id.search);
        imgsearch = findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = search.getText().toString().trim();
                if(word.isEmpty()){
                    searchUrl = youtubeUrl;
                }else{
                    searchUrl = youtubeUrl + "&q=" + word;
                }
                // 새로 바뀐 검색어로 데이터를 가져오기 위해서, 원래 들어있던 어레이리스트의
                // 데이터를 모드 지우고, 새로 받아온다.
                youtubeArrayList.clear();
                getNetworkData(searchUrl);

            }
        });


        // 발리 라이브러리 이용해서, 호출
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        getNetworkData(youtubeUrl);



    }

    public void getNetworkData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("AAA", response.toString());
                        try {
                            nextPageToken = response.getString("nextPageToken");
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0; i < items.length(); i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                JSONObject id = jsonObject.getJSONObject("id");
                                String videoId = id.getString("videoId");
                                JSONObject snippet = jsonObject.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                String desc = snippet.getString("description");
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                JSONObject def = thumbnails.getJSONObject("medium");
                                String url = def.getString("url");

                               Youtube youtube = new Youtube(title, desc, url, videoId);
                                youtubeArrayList.add(youtube);
//                                Log.i("AAA", videoId +","+title+", "+desc+", "+url);
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(
                                    MainActivity.this,youtubeArrayList);
                            recyclerView.setAdapter(recyclerViewAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }
    public void addNetworkData(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("AAA", response.toString());
                        try {
                            nextPageToken = response.getString("nextPageToken");
                            JSONArray items = response.getJSONArray("items");
                            for(int i = 0; i < items.length(); i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                JSONObject id = jsonObject.getJSONObject("id");
                                String videoId = id.getString("videoId");
                                JSONObject snippet = jsonObject.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                String desc = snippet.getString("description");
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                JSONObject def = thumbnails.getJSONObject("medium");
                                String url = def.getString("url");

                                Youtube youtube = new Youtube(title, desc, url, videoId);
                                youtubeArrayList.add(youtube);
//                                Log.i("AAA", videoId +","+title+", "+desc+", "+url);
                            }
                            recyclerViewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }



}
