package com.example.storesearching;

import com.example.storesearching.util.TestLocationActivity;

public class myLocation {
    public double latitude;
    public double longitude;
    public String country;
    public String state;
    public String city;
    public String street;
    public String number;
    public String floor;
    public String zipcode;
    public double getDistance(){
        TestLocationActivity testLocationActivity = TestLocationActivity.getInstance(null,null,false,null);
        if(latitude == 0 && longitude == 0){
            return 1;
        }
        return testLocationActivity.calculateDistance(longitude,latitude);
    }
}
