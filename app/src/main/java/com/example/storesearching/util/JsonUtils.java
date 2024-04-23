package com.example.storesearching.util;

import com.example.storesearching.DataManager;
import com.example.storesearching.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class JsonUtils {

    public static JSONObject buildInterface3JsonObject(int interfaceId, String currentUser, String StoreName) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("StoreName", StoreName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject buildInterface4JsonObject(int interfaceId, String currentUser, List<Integer> huntedStoreIdList) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            JSONArray HuntedStoreIdList = new JSONArray();
            for (Integer id : huntedStoreIdList) {
                HuntedStoreIdList.put(id);
            }
            jsonObject.put("HuntedStoreIdList", HuntedStoreIdList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject buildInterface6JsonObject(int interfaceId, String currentUser, String ItemName) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("ItemName", ItemName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public  static  JSONObject buildInterface10JsonObject(int interfaceId, String currentUser, String username, String password, Date Birthday, JSONArray Description)
    {
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("InterfaceId",interfaceId);
            jsonObject.put("CurrentUser",currentUser);
            jsonObject.put("UserName",username);
            jsonObject.put("PassWord",password);
            jsonObject.put("Birthday",Birthday);
            jsonObject.put("Interests",Description);

        }catch (JSONException e){
            e.printStackTrace();;
        }
        return jsonObject;
    }
    private static int count = 0;
    public static Store JSONObjectToStore(JSONObject storeObject){
        try {
            Store store = new Store();
            if(DataManager.testSign){
                store.storeName = "Red Sun Store" + count;
                count = count + 1;
                store.StoreDescription = "Department Store//Department Store//Department Store//Department Store//Department Store//Department Store//Department Store//Department Store//";
            }
            else{
                store.storeId = storeObject.getInt("storeId");
                store.storeName = storeObject.getString("storeName");
//            store.location = JSONObjectToLocation(storeObject.getJSONObject("location"));
                JSONArray itemListArray = storeObject.getJSONArray("items");
                for (int i = 0; i < itemListArray.length(); i++) {
//                store.itemList.add(JSONObjectToStore(itemListArray.getJSONObject(i)));
                }
                store.StoreDescription = storeObject.getString("StoreDescription");
            }
            return store;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void parseInterface3JsonObject(JSONObject jsonObject, List<Store> storeList) {
        try {
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    storeList.add(JSONObjectToStore(null));
                }
            }
            else{
                storeList.clear();
                JSONArray storeListArray = jsonObject.getJSONArray("StoreList");
                for (int i = 0; i < storeListArray.length(); i++) {
                    storeList.add(JSONObjectToStore(storeListArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
<<<<<<< Updated upstream
=======
    public static void parseInterface5JsonObject(JSONObject jsonObject, List<Integer> huntedStoreIdList) {
        try {
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    huntedStoreIdList.add(i);
                }
            }
            else{
                huntedStoreIdList.clear();
                JSONArray huntedStoreIdListArray = jsonObject.getJSONArray("array");
                for (int i = 0; i < huntedStoreIdListArray.length(); i++) {
                    huntedStoreIdList.add(huntedStoreIdListArray.getInt(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseInterface6JsonObject(JSONObject jsonObject, List<Item> itemList) {
        try {
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    itemList.add(JSONObjectToItem(null));
                }
            }
            else{
                itemList.clear();
                JSONArray itemListArray = jsonObject.getJSONArray("array");
                for (int i = 0; i < itemListArray.length(); i++) {
                    itemList.add(JSONObjectToItem(itemListArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
>>>>>>> Stashed changes


    //tempolate:
    // 构建 JSON 对象
    public static JSONObject buildJsonObject(int interfaceId, String currentUser, String userName, String passWord) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("UserName", userName);
            jsonObject.put("PassWord", passWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    // 解析 JSON 对象
    public static void parseJsonObject(JSONObject jsonObject) {
        try {
            int interfaceId = jsonObject.getInt("InterfaceId");
            String currentUser = jsonObject.getString("CurrentUser");
            String userName = jsonObject.getString("UserName");
            String passWord = jsonObject.getString("PassWord");

            System.out.println("InterfaceId: " + interfaceId);
            System.out.println("CurrentUser: " + currentUser);
            System.out.println("UserName: " + userName);
            System.out.println("PassWord: " + passWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
