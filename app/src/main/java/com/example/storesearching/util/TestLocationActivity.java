package com.example.storesearching.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.Locale;

import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

//refer to https://blog.csdn.net/Coo123_/article/details/107659139
public class TestLocationActivity {

    public static final int LOCATION_CODE = 301;
    private static final int PERMISSION_REQUEST_LOCATION = 1001;
    private LocationManager locationManager;
    private String locationProvider = null;
    private Context context;
    private Activity activity;
    private boolean output;//1 means output hint

    public TestLocationActivity(Context context, Activity activity, boolean output) {
        this.context = context;
        this.activity = activity;
        this.output = output;
        this.output = false;
        //getLocation();
    }
    public JSONObject getLocationJson(){
        return null;
    }//TODO:
    public double calculateDistance(double longitude, double latitude) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) ) {
            Location location = locationManager.getLastKnownLocation(locationProvider);
            return DistanceCalculator.calculateDistance(latitude, longitude, location.getLatitude(), location.getLongitude());
        }
        return -1.0;
    }
    public Location getLocation(){
        // 检查是否已经获取了位置权限
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 如果权限未被授予，则请求权限
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_LOCATION);
            if(output)Toast.makeText(context, "请求权限", Toast.LENGTH_SHORT).show();
        }

        //1.获取位置管理器
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
            Log.v("TAG", "定位方式GPS");
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
            Log.v("TAG", "定位方式Network");
        }else {
            if(output)Toast.makeText(context, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
            }
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) )
        {
            //3.获取上次的位置，一般第一次运行，此值为null
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location!=null){
                if(output)Toast.makeText(context, location.getLongitude() + " " +
                        location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                Log.v("TAG", "获取上次的位置-经纬度："+location.getLongitude()+"   "+location.getLatitude());
                getAddress(location);

            }else{
                //监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
                Toast.makeText(context, "开启监控", Toast.LENGTH_SHORT).show();
                locationManager.requestLocationUpdates(locationProvider, 3000, 1,locationListener);
            }
            return location;
        }
        return null;
    }

    public LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
        }
        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
        }
        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                //如果位置发生变化，重新显示地理位置经纬度
                if(output)Toast.makeText(context, location.getLongitude() + " " +
                        location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                Log.v("TAG", "监视地理位置变化-经纬度："+location.getLongitude()+"   "+location.getLatitude());
            }
        }
    };

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case LOCATION_CODE:
//                if(grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED
//                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(context, "申请权限", Toast.LENGTH_LONG).show();
//                    try {
//                        List<String> providers = locationManager.getProviders(true);
//                        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//                            //如果是Network
//                            locationProvider = LocationManager.NETWORK_PROVIDER;
//
//                        }else if (providers.contains(LocationManager.GPS_PROVIDER)) {
//                            //如果是GPS
//                            locationProvider = LocationManager.GPS_PROVIDER;
//                        }
//                        Location location = locationManager.getLastKnownLocation(locationProvider);
//                        if (location!=null){
//                            Toast.makeText(context, location.getLongitude() + " " +
//                                    location.getLatitude() + "", Toast.LENGTH_SHORT).show();
//                            Log.v("TAG", "获取上次的位置-经纬度："+location.getLongitude()+"   "+location.getLatitude());
//                        }else{
//                            // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
//                            locationManager.requestLocationUpdates(locationProvider, 0, 0,locationListener);
//                        }
//
//                    }catch (SecurityException e){
//                        e.printStackTrace();
//                    }
//                } else {
//                    Toast.makeText(context, "缺少权限", Toast.LENGTH_LONG).show();
//                    finish();
//                }
//                break;
//        }
//    }

    //获取地址信息:城市、街道等信息
    private List<Address> getAddress(Location location) {
        List<Address> result = null;
        try {
            if (location != null) {
                Geocoder gc = new Geocoder(context, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),
                        location.getLongitude(), 1);
                if(output)Toast.makeText(context, "获取地址信息："+result.toString(), Toast.LENGTH_LONG).show();
                Log.v("TAG", "获取地址信息："+result.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}


class DistanceCalculator {

    // 地球半径（单位：千米）
    private static final double EARTH_RADIUS = 6371.0;

    // 计算两个位置之间的距离（单位：千米）
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 将经纬度转换为弧度
        double lat1Radians = Math.toRadians(lat1);
        double lon1Radians = Math.toRadians(lon1);
        double lat2Radians = Math.toRadians(lat2);
        double lon2Radians = Math.toRadians(lon2);

        // 计算经纬度差值
        double deltaLat = lat2Radians - lat1Radians;
        double deltaLon = lon2Radians - lon1Radians;

        // 使用 Haversine 公式计算大圆距离
        double a = Math.pow(Math.sin(deltaLat / 2), 2) +
                Math.cos(lat1Radians) * Math.cos(lat2Radians) *
                        Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 计算距离
        return EARTH_RADIUS * c;
    }
}
