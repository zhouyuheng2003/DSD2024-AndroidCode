package com.example.storesearching.TencentMap;



import static com.tencent.tencentmap.mapsdk.maps.TencentMap.MAP_TYPE_NORMAL;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.storesearching.R;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapFragment;
import com.tencent.tencentmap.mapsdk.maps.MapRenderLayer;
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
public class TencentMapActivity extends AppCompatActivity {

    private static final int TEXTURE_ID = MAP_TYPE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tencent_map);
        SurfaceTexture surface =new SurfaceTexture(TEXTURE_ID);//
        TencentMapOptions mapOptions = new TencentMapOptions();
        mapOptions.setExtSurface(surface);
        mapOptions.setExtSurfaceDimension(900, 900);
        MapRenderLayer mapView = new MapRenderLayer(getBaseContext(), mapOptions);
        setContentView(mapView);
    }


//    private FragmentManager fm;
//    protected TencentMap tencentMap;
//    private Fragment supportMapFragment;
//    protected UiSettings mapUiSettings;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tencent_map);
//
//        //创建tencentMap地图对象，可以完成对地图的几乎所有操作
//        fm = getSupportFragmentManager();
//        supportMapFragment =  fm.findFragmentById(R.id.map_frag);
//        tencentMap = supportMapFragment.getMap();
//        mapUiSettings = tencentMap.getUiSettings();
//        //对地图操作类进行操作
//        CameraUpdate cameraSigma =
//                CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                        new LatLng(39.984066, 116.307548),
//                        15,
//                        0f,
//                        0f));
//        //移动地图
//        tencentMap.moveCamera(cameraSigma);
//
//    }
}