package com.example.storesearching;
//Test
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.storesearching.util.TestLocationActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.storesearching.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    public static MapsActivity instance;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int FINE_PERMISSION_CODE = 1;
    public Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private long lastClickTime = 0;
    private static long ClickTimeInterval = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();

        instance = this;
    }

    public void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    public static String[][] DataFromHome;

    public String[][] getData() {
        String[][] data = {
                {"Store UTAD", "41.28678538409359", "-7.740637471808735", "Store in Portugal.\nThis is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it! This is some long text to test it!"},
                {"Store JLU",  "43.82572225983296", "125.28501566529455", "Store in China"}};

//                {"Store UTAD", "41.28678538409359", "-7.740637471808735"},
//                {"Store JLU", "43.82572225983296", "125.28501566529455"}};

        if(DataFromHome != null){
            data = DataFromHome;
        }

        return data;
    }

    public static double stringToDouble(String str) {
        return Double.parseDouble(str);
    }

    public void updateMap(){
        mMap.clear();
        String[][] store = getData();
        LatLng location = null;

        for (int i = 0; i < store.length; i++) {
            double lat = stringToDouble(store[i][1]);
            double lng = stringToDouble(store[i][2]);
            location = new LatLng(lat, lng);
            Marker marker;
            if (store[i].length > 3 && store[i][3] != null && !store[i][3].isEmpty()){
                marker = mMap.addMarker(new MarkerOptions().position(location).title(store[i][0]).snippet(store[i][3]));
            }
            else{
                marker = mMap.addMarker(new MarkerOptions().position(location).title(store[i][0]).snippet(""));
            }
            String[] storeInfo = store[i];
            marker.setTag(storeInfo);
        }
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapsActivity.this));
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getLayoutInflater()));
        mMap.setOnInfoWindowClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        if(TestLocationActivity.TencentSign && DataManager.ChinaSign) {
            LatLng customLocation = new LatLng(TestLocationActivity.TencentLatitude, TestLocationActivity.TencentLongtitude); // 示例经纬度，需替换为实际自定义位置
            double accuracy = TestLocationActivity.TencentAccuracy;
            // 添加自定义位置标记
            mMap.addMarker(new MarkerOptions()
                    .position(customLocation)
                    .title("Custom Location")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot))
                    .anchor(0.5f, 0.5f)); // 使用自定义的蓝色圆圈图标

            // 添加精度圆圈
            CircleOptions circleOptions = new CircleOptions()
                    .center(customLocation)
                    .radius(accuracy) // 单位为米
                    .strokeColor(Color.BLUE)
                    .fillColor(0x220000FF) // 半透明蓝色
                    .strokeWidth(5);
            mMap.addCircle(circleOptions);

            // 移动相机到自定义位置
            if(DataManager.RedirectSign){
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(customLocation, 12.0f));
                DataManager.RedirectSign = false;
            }


            mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        }
        else{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);

            if(store.length == 1 && location != null)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
            else
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 12.0f));
            //TestLocationActivity
            mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateMap();
    }

    @Override
    public void onInfoWindowClick(Marker marker){
        onDoubleClick(marker);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        long currentTime = System.currentTimeMillis();
        long clickInterval = currentTime - lastClickTime;

        if(clickInterval < ClickTimeInterval) {
            onDoubleClick(marker);
        }

        lastClickTime = currentTime;

        return false;
    }

    public void onDoubleClick(@NonNull Marker marker){
        String[] storeInfo = (String[]) marker.getTag();
        Toast.makeText(this, marker.getTitle() + ": " + storeInfo[1] + " / " + storeInfo[2], Toast.LENGTH_SHORT).show();

        getDirections(storeInfo[1], storeInfo[2]);
    }

    private void getDirections(String toLat, String toLng){
        //from = String.valueOf(currentLocation);
        double fromLat = currentLocation.getLatitude();
        double fromLng = currentLocation.getLongitude();
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + fromLat + "," + fromLng + "/" + toLat + "," + toLng);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch(ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
