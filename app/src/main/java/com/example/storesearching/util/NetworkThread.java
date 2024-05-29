package com.example.storesearching.util;
import android.util.Log;

import com.example.storesearching.DataManager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread {
    private static final String TAG = "NetworkThread";
    private static final String apiUrl = "https://u216558-8612-8fda9034.westc.gpuhub.com:8443/interface";

    private static final String databaseApiUrl = "http://13.79.99.190/api/";

    private int interfaceId;
    private String Json;

    public NetworkThread(int interfaceId, String userName, String Json) {
        this.interfaceId = interfaceId;
        this.Json = Json;
        result = null;
    }
    public String result;
    @Override
    public void run() {
        boolean recieveSign = false;
        Log.v("val",Json);
        if(interfaceId == 1 || interfaceId == 3 || interfaceId == 5 ||interfaceId == 6 || interfaceId == 7 ||
                interfaceId == 11 || interfaceId == 12)recieveSign = true;
//      recieveSign = true;//This sign is for testing the interface without output
        try {
            URL url;
            if(DataManager.IntegrationTestingSign){
//                String interfaceKind="";
//                if(interfaceId==1)interfaceKind = "login";
//                else if(interfaceId==2)interfaceKind = "location";
//                else if(interfaceId==3)interfaceKind = "store";
//                else if(interfaceId==4)interfaceKind = "HuntedStore";
//                else if(interfaceId==5)interfaceKind = "HuntedStore";
//                else if(interfaceId==6)interfaceKind = "Item";
//                else if(interfaceId==7)interfaceKind = "search";
//                else if(interfaceId==8)interfaceKind = "Feedback2Store";
//                else if(interfaceId==9)interfaceKind = "Feedback2Item";
//                else if(interfaceId==10)interfaceKind = "Customer";
//                else if(interfaceId==11)interfaceKind = "Customer";
//                else if(interfaceId==12)interfaceKind = "Registration";
//                url = new URL(databaseApiUrl + interfaceKind);
                url = new URL(databaseApiUrl + "interface" + interfaceId);
            }
            else{
                url = new URL(apiUrl + interfaceId);
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            if(interfaceId == 8)Log.v("vis","true");

            if(recieveSign)connection.setDoOutput(true);
            else connection.setDoOutput(false);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            outputStream.writeBytes(Json);

            outputStream.flush();
            outputStream.close();
            Log.v("val",Json);
            Log.v("val","interfaceId:"+interfaceId);
            if(recieveSign){
                int responseCode = connection.getResponseCode();
//                result = "res is " + responseCode;
                StringBuilder response = new StringBuilder();
                Log.v("val","get"+(responseCode));
                if (responseCode == HttpURLConnection.HTTP_OK ) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    result = response.toString();

                } else {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    result = "Error";
//                    result = response.toString();
                }

//                int maxLogSize = 1000;
//                for(int i = 0; i <= result.length() / maxLogSize; i++) {
//                    int start = i * maxLogSize;
//                    int end = (i+1) * maxLogSize;
//                    end = end > result.length() ? result.length() : end;
//                    Log.v("val", result.substring(start, end));
//                }
                Log.v("val","get"+result);
                Log.v("val",url.toString());

                if(interfaceId == 1 || interfaceId == 3 || interfaceId == 5 ||interfaceId == 6 || interfaceId == 7 ||
                        interfaceId == 11 || interfaceId == 12);
                else {
                    Log.v("val","get"+result);
                    result = null;
                }
                return;
            }
            else return;
        } catch (Exception e) {
            e.printStackTrace();
            if(recieveSign)result = "Error";
            else result = null;
            return;
        }
    }
}
