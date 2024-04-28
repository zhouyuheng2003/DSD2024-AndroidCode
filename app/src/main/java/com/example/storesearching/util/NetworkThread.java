package com.example.storesearching.util;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread {

    private static final String TAG = "NetworkThread";
    private static final String apiUrl = "https://u216558-8612-8fda9034.westc.gpuhub.com:8443/interface";
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
        try {
            URL url = new URL(apiUrl + interfaceId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            boolean recieveSign = false;
            if(interfaceId == 8)Log.v("vis","true");
            if(interfaceId == 1 || interfaceId == 3 || interfaceId == 5 ||interfaceId == 6 || interfaceId == 7 ||
                    interfaceId == 11 || interfaceId == 12)recieveSign = true;
//            recieveSign = true;//This sign is for testing the interface without output
            if(recieveSign)connection.setDoOutput(true);
            else connection.setDoOutput(false);
            connection.setRequestProperty("Content-Type", "application/json");

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            outputStream.writeBytes(Json);

            outputStream.flush();
            outputStream.close();

            if(recieveSign){
                int responseCode = connection.getResponseCode();
//                result = "res is " + responseCode;
                StringBuilder response = new StringBuilder();
                if (responseCode == HttpURLConnection.HTTP_OK) {
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
                }

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
            result = "null";
            return;
        }
    }
}
