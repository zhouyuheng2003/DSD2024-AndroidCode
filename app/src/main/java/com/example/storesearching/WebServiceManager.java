package com.example.storesearching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebServiceManager {
    private static WebServiceManager instance;
    private WebServiceManager() {
    }
    public static synchronized WebServiceManager getInstance() {
        if (instance == null) {
            instance = new WebServiceManager();
        }
        return instance;
    }
    public synchronized String getJson(int interfaceId , int userId){
        //xxxxx
        //""
    }
    public synchronized String getJson(){
        return getJson(0,0);
    }
    public synchronized void sendJson(int interfaceId , int userId,String Json){
        //xxxxx
    }
}
