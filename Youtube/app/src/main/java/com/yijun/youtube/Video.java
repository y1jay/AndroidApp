package com.yijun.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

import com.yijun.youtube.data.Youtube;

public class Video extends AppCompatActivity {
    WebView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video = findViewById(R.id.video);
        Youtube youtube = (Youtube) getIntent().getSerializableExtra("youtube");
        String videoId = youtube.getVideoId();
        String v = "https://www.youtube.com/watch?v=" + videoId;

        openWebPage(v);

        finish();

    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, webpage);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);

        }
    }
}