package com.example.storesearching;

import android.util.Log;

import com.example.storesearching.util.NetworkThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class WebServiceManager {
    private static WebServiceManager instance;
    private WebServiceManager() {
        lastGet = null;
    }
    public static synchronized WebServiceManager getInstance() {
        if (instance == null) {
            instance = new WebServiceManager();
        }
        return instance;
    }

//    static String apiUrl = "https://example.com/interface";
//    static String apiUrl = "https://u216558-8612-8fda9034.westc.gpuhub.com:8443/Interface";
//
//
//    public static String uploadJson(int interfaceId,String userName, String Json) {
//        try {
//            URL url = new URL(apiUrl + interfaceId);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            connection.setRequestMethod("POST");
//            if(interfaceId == 1 || interfaceId == 3 || interfaceId == 5 ||interfaceId == 6 || interfaceId == 7 ||
//                    interfaceId == 11 || interfaceId == 12)connection.setDoOutput(true);
//            else connection.setDoOutput(false);
//            connection.setRequestProperty("Content-Type", "application/json");
//
//            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
//
//            outputStream.writeBytes(Json);
//
//            outputStream.flush();
//            outputStream.close();
//
//            if(interfaceId == 1 || interfaceId == 3 || interfaceId == 5 ||interfaceId == 6 || interfaceId == 7 ||
//                    interfaceId == 11 || interfaceId == 12){
//                int responseCode = connection.getResponseCode();
//                StringBuilder response = new StringBuilder();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//                    in.close();
//                } else {
//                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        response.append(inputLine);
//                    }
//                    in.close();
//                    return "Error";
//                }
//                return response.toString();
//            }
//            else return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "null";
//        }
//    }



    public static String uploadJson(int interfaceId,String userName, String Json) {
        NetworkThread networkThread = new NetworkThread(interfaceId, userName, Json);
        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return networkThread.result;
    }


    private static String lastGet;
    public synchronized String getJson(int interfaceId , String userName)throws MyCustomException{
        String nowGet = lastGet;
        if(lastGet == null){
            JSONObject head = new JSONObject();
            try {
                head.put("InterfaceId",interfaceId);
                head.put("CurrentUser",userName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            nowGet = uploadJson(interfaceId, userName, "");
        }
        else lastGet = null;
        if(nowGet == "Error"){
            throw new MyCustomException("Internet Error.");
        }
        return nowGet;
    }
    public synchronized void sendJson(int interfaceId , String userName, String Json){
        lastGet =  uploadJson(interfaceId, userName, Json);
    }
}
