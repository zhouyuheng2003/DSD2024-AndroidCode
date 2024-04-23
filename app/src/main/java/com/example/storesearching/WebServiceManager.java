package com.example.storesearching;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public synchronized String getJson(int interfaceId , String userName){
        //xxxxx
        //""
        JSONObject ret = new JSONObject();
        JSONArray interest = new JSONArray();
        interest.put("computer");
        interest.put("football");
        try {
            ret.put("InterfaceId", interfaceId);
            ret.put("CurrentUser", userName);
            ret.put("MatchToken",true);
            ret.put("Interests",interest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret.toString();

//        if(DataManager.testSign){
//            return "test";
//        }
//        return "";
    }
    public synchronized String getJson(){
        return getJson(0,"");
    }
    public synchronized void sendJson(int interfaceId , String userName, String Json){
        //xxxxx
    }
}
