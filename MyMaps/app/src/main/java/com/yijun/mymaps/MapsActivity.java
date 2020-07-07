package com.yijun.mymaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;

//내가 만들 앱에 implements를 해준다.
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ArrayList<MarkerOptions> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);//밑에 onMapReady를 실행

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {//맵 준비가 됐을때 행동을 여기서 한다.(처음할때)
        mMap = googleMap;

        // 여러개의 마커를 처리하는 방법
        // 1. 마커옵션을 만든다.
        MarkerOptions options1 = new MarkerOptions().position(new LatLng(37.540807, 126.836479))
        .title("더조은 강서");
        // 2. ArrayList에 넣어준다/
        list.add(options1);

        MarkerOptions options2 = new MarkerOptions().position(new LatLng(37.540071, 126.839039))
                .title("신월초");
        // 2. ArrayList에 넣어준다/
        list.add(options2);

        for (MarkerOptions options : list){
            mMap.addMarker(options);
        }
        //지도의 중심으로 잡고 싶은 좌표를 넣어주면 지도의 중심으로 표시된다.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.541734, 126.840432),18));














//        //1.GPS정보 먼저 셋팅.
//        //37.5405432,126.8365439
//        //지도의 어디를 중심으로 띄울 지 위치를 정한다.
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(37.540807, 126.836479);
//        //37.5399522,126.8368454
//        LatLng school = new LatLng(37.540071, 126.839039);
//        // 마커를 표시하고싶을때 옵션
//        // 2. 구글맵에 마커 등록
//        mMap.addMarker(new MarkerOptions().position(sydney).title("더조은 강서"));
//        mMap.addMarker(new MarkerOptions().position(school).title("신월 초등학교"));
//
//        //3. 지도 타입을 바꿔본다.
//        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        //4. 화면에 중앙을 설정해서 지도에 보여라.
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,19));
    }
}
