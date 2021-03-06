package com.yijun.youtube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.yijun.youtube.data.Youtube;

public class Photo extends AppCompatActivity {
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        imgView = findViewById(R.id.imgView);
        Youtube youtube = (Youtube) getIntent().getSerializableExtra("thumbnail");
        String url = youtube.getUrl();
        GlideUrl glideurl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("User-Agent","Your-user-agent").build());
        Glide.with(Photo.this).load(glideurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(imgView);


    }
}
