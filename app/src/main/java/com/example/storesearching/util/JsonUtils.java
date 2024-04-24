package com.example.storesearching.util;

import com.example.storesearching.DataManager;
import com.example.storesearching.Item;
import com.example.storesearching.Store;
import com.example.storesearching.myLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import android.location.Address;
public class JsonUtils {
    public static JSONObject buildLocation(Address myAddress){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("latitude", myAddress.getLatitude());
            jsonObject.put("longitude", myAddress.getLongitude());
            jsonObject.put("country", myAddress.getCountryName());
            jsonObject.put("state", myAddress.getAdminArea());
            jsonObject.put("city", myAddress.getLocality());
            jsonObject.put("street", myAddress.getThoroughfare());
            jsonObject.put("number", null);
            jsonObject.put("floor", null);
            jsonObject.put("zipcode", myAddress.getPostalCode());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
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
    public static JSONObject buildInterface7JsonObject(int interfaceId, String currentUser, JSONObject MyLocation,int RequestType) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("MyLocation", MyLocation);
            jsonObject.put("RequestType", RequestType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject buildInterface8JsonObject(int interfaceId, String currentUser, int StoreId, String comment, int rating) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("StoreId", StoreId);
            jsonObject.put("comment", comment);
            jsonObject.put("rating", rating);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject buildInterface9JsonObject(int interfaceId, String currentUser, int ItemId, String comment, int rating) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            jsonObject.put("ItemId", ItemId);
            jsonObject.put("comment", comment);
            jsonObject.put("rating", rating);
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

    public static myLocation JSONObjectToLocation(JSONObject myLocationObject){
        try {
            myLocation location = new myLocation();
            if(DataManager.testSign){
                location.longitude = 0;
                location.latitude = 0;
            }
            else{
                location.latitude = myLocationObject.getDouble("latitude");
                location.longitude = myLocationObject.getDouble("longitude");
                location.country = myLocationObject.getString("country");
                location.state = myLocationObject.getString("state");
                location.city = myLocationObject.getString("city");
                location.street = myLocationObject.getString("street");
                location.number = myLocationObject.getString("number");
                location.floor = myLocationObject.getString("floor");
                location.zipcode = myLocationObject.getString("zipcode");
            }
            return location;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
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
                store.location = JSONObjectToLocation(storeObject.getJSONObject("location"));
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
    public static Item JSONObjectToItem(JSONObject itemObject){
        try {
            Item item = new Item();
            if(DataManager.testSign){
                item.itemId = 1;
                item.itemName = "pencil" + count;
                item.itemPrice = 5;
                item.itemDescription = "pen//pen//pen//pen//pen//pen//pen//pen//pen//pen//";
                // TODO: base64picture
                count = count + 1;
                item.itemStoreName = "Red sun store";
                item.itemStoreId = 1;
                item.customerVisits = 0;
            }
            else{
                item.itemId = itemObject.getInt("ItemId");
                item.itemName = itemObject.getString("ItemName");
                item.itemPrice = itemObject.getDouble("ItemPrice");
                item.itemDescription = itemObject.getString("ItemDescription");
                // TODO: base64picture
                item.itemStoreId = itemObject.getInt("ItemStoreId");
                item.itemStoreName = itemObject.getString("ItemStoreName");
                item.customerVisits = itemObject.getInt("customerVisits");
            }
            return item;
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
    public static void parseInterface7JsonObject(JSONObject jsonObject, List<Store> recommendStoreList) {
        try {
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    recommendStoreList.add(JSONObjectToStore(null));
                }
            }
            else{
                recommendStoreList.clear();
                JSONArray storeListArray = jsonObject.getJSONArray("array");
                for (int i = 0; i < storeListArray.length(); i++) {
                    recommendStoreList.add(JSONObjectToStore(storeListArray.getJSONObject(i)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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
