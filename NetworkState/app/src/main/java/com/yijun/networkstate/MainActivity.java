package com.yijun.networkstate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView img;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        btn = findViewById(R.id.btn);
// 버튼을 눌렀을 시에 네트워크 상태 확인 하는 코드
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int statusInfo = getNetworkStatus2(MainActivity.this);
                // 1 은 wifi (밑에 설정해놨음)
                if (statusInfo==1){
                    img.setImageResource(R.drawable.wifi);
                }else if (statusInfo==0){
                    //0 은 모바일 데이터(밑에 설정해놨음
                    img.setImageResource(R.drawable.fiveg);
                }else{
                    img.setImageResource(R.drawable.ic_launcher_foreground);
                }
            }
        });
    }

    public int getNetworkStatus2(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return 3;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return 1;
                }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    return 2;
                }
            }
        } else {
            android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null){
                switch (networkInfo.getType()){
                    case ConnectivityManager.TYPE_WIFI:
                        return 1;
                    case ConnectivityManager.TYPE_MOBILE:
                        return 0;
                    default:
                        return 3;
                }
            }else{
                return 1000;
            }
        }
        return 1000;
    }



}
