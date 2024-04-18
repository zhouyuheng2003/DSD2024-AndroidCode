package com.example.storesearching;


import com.example.storesearching.util.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    public static boolean testSign = true;
    private static DataManager instance;
    public int currentUserId;
    public Map< Integer, User> users;
    private DataManager() {
        users = new HashMap<Integer, User>();
        currentUserId = 0;
        users.put(0, new User(0));
    }
    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    public void SearchStore(String query) throws JSONException {
        WebServiceManager webServiceManager = WebServiceManager.getInstance();
        int interfaceId = 3;
        String userName = users.get(currentUserId).UserName;
        webServiceManager.sendJson(interfaceId, userName,
                JsonUtils.buildInterface3JsonObject(interfaceId, userName,query).toString()
                );
        String JsonString = webServiceManager.getJson(interfaceId, userName);
        while(JsonString == ""){
            JsonString = webServiceManager.getJson(interfaceId, userName);
        }
        JSONObject Json = null;
        if(!testSign) Json = new JSONObject(JsonString);
        JsonUtils.parseInterface3JsonObject(Json,users.get(currentUserId).storeList);
    }
    public List<Store> currentStoreList(){
        return users.get(currentUserId).storeList;
    }
}
