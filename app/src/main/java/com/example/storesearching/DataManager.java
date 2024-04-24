package com.example.storesearching;


import com.example.storesearching.util.JsonUtils;
import com.example.storesearching.util.TestLocationActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    public static double distanceLimit = 2;
    public static boolean testSign = true;//whether it is unit test
    public static int searchMode = 0;//modified by HomeFragment
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
    public void SearchItem(String query) throws JSONException {
        WebServiceManager webServiceManager = WebServiceManager.getInstance();
        int interfaceId = 6;
        String userName = users.get(currentUserId).UserName;
        webServiceManager.sendJson(interfaceId, userName,
                JsonUtils.buildInterface6JsonObject(interfaceId, userName, query).toString()
        );
        String JsonString = webServiceManager.getJson(interfaceId, userName);
        while(JsonString == ""){
            JsonString = webServiceManager.getJson(interfaceId, userName);
        }
        JSONObject Json = null;
        if(!testSign) Json = new JSONObject(JsonString);
        JsonUtils.parseInterface6JsonObject(Json,users.get(currentUserId).itemList);
    }
    public void updateHuntedStoreIdList(int index) throws JSONException {
        Integer currentId = users.get(currentUserId).storeList.get(index).storeId;
        boolean updateFlag = true;
        WebServiceManager webServiceManager = WebServiceManager.getInstance();
        int interfaceId = 5;
        String userName = users.get(currentUserId).UserName;
        String JsonString = webServiceManager.getJson(interfaceId, userName);
        while(JsonString == ""){
            JsonString = webServiceManager.getJson(interfaceId, userName);
        }
        JSONObject Json = null;
        if(!testSign) Json = new JSONObject(JsonString);
        JsonUtils.parseInterface5JsonObject(Json,users.get(currentUserId).huntedStoreIdList);
        List<Integer> huntedStoreIdList = users.get(currentUserId).huntedStoreIdList;
        for(int i = 0; i < huntedStoreIdList.size(); i++){
            if(huntedStoreIdList.get(i) == currentId){
                updateFlag = false;
            }
        }
        if(updateFlag){
            huntedStoreIdList.add(currentId);

            interfaceId = 4;
            webServiceManager.sendJson(interfaceId, userName,
                    JsonUtils.buildInterface4JsonObject(interfaceId, userName,huntedStoreIdList).toString()
            );
        }
    }
    public void getRecommendStoreList() throws JSONException {
        WebServiceManager webServiceManager = WebServiceManager.getInstance();
        int interfaceId = 7;
        String userName = users.get(currentUserId).UserName;
        TestLocationActivity testLocationActivity = TestLocationActivity.getInstance(null,null,false);
        webServiceManager.sendJson(interfaceId, userName,
                JsonUtils.buildInterface7JsonObject(interfaceId, userName,testLocationActivity.getLocationJson(),2).toString()
        );

        String JsonString = webServiceManager.getJson(interfaceId, userName);
        while(JsonString == ""){
            JsonString = webServiceManager.getJson(interfaceId, userName);
        }
        JSONObject Json = null;
        if(!testSign) Json = new JSONObject(JsonString);
        JsonUtils.parseInterface7JsonObject(Json,users.get(currentUserId).recommendStoreList);
    }

    public List<Store> currentStoreList(){
        return users.get(currentUserId).storeList;
    }
    public List<Item> currentItemList(){
        return users.get(currentUserId).itemList;
    }
}
