package com.example.storesearching.util;

import com.example.storesearching.DataManager;
import com.example.storesearching.HistoryVisit;
import com.example.storesearching.Item;
import com.example.storesearching.MyCustomException;
import com.example.storesearching.R;
import com.example.storesearching.Store;
import com.example.storesearching.myLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import android.graphics.BitmapFactory;
import android.location.Address;
import android.util.Base64;
import android.util.Log;

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
            jsonObject.put("number", "");
            jsonObject.put("floor", "");
            jsonObject.put("zipcode", myAddress.getPostalCode());

//            jsonObject.put("latitude", "myAddress.getLatitude()");
//            jsonObject.put("longitude", "myAddress.getLongitude()");
//            jsonObject.put("country"," myAddress.getCountryName()");
//            jsonObject.put("state", "myAddress.getAdminArea()");
//            jsonObject.put("city", "myAddress.getLocality()");
//            jsonObject.put("street", "myAddress.getThoroughfare()");
//            jsonObject.put("number", "");
//            jsonObject.put("floor", "");
//            jsonObject.put("zipcode"," myAddress.getPostalCode()");


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
    public static JSONObject buildInterface4JsonObject(int interfaceId, String currentUser, List<HistoryVisit> huntedStoreIdList) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("InterfaceId", interfaceId);
            jsonObject.put("CurrentUser", currentUser);
            JSONArray HuntedStoreIdList = new JSONArray();
            for (HistoryVisit id : huntedStoreIdList) {
//                HuntedStoreIdList.put(id);
                JSONObject HistoryVisit = new JSONObject();
                HistoryVisit.put("StoreId", id.storeId);
                HistoryVisit.put("VisitTime",id.dataTime);
                HuntedStoreIdList.put(HistoryVisit);
            }
            jsonObject.put("History", HuntedStoreIdList);
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
            Log.d("LastGetContent", "LastSend content: " + MyLocation);
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
            JSONObject Feedback2Store = new JSONObject();

            Feedback2Store.put("StoreId", StoreId);
            Feedback2Store.put("comment", comment);
            Feedback2Store.put("rating", rating);
            Feedback2Store.put("UserName", currentUser);

            jsonObject.put("Feedback", Feedback2Store);
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


            JSONObject Feedback2Item = new JSONObject();

            Feedback2Item.put("StoreId", ItemId);
            Feedback2Item.put("comment", comment);
            Feedback2Item.put("rating", rating);
            Feedback2Item.put("UserName", currentUser);

            jsonObject.put("Feedback", Feedback2Item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public  static  JSONObject buildInterface10JsonObject(int interfaceId, String currentUser, String username, String password, Date Birthday, String Description)
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
                store.location.latitude = 43.82572225983296;
                store.location.longitude = 125.28501566529455;
                for (int i = 0; i < 20; i++) {
                    store.itemList.add(JSONObjectToItem(null));
                }
            }
            else{
                store.storeId = storeObject.getInt("storeId");
                store.storeName = storeObject.getString("storeName");
                store.location = JSONObjectToLocation(storeObject.getJSONObject("location"));


                if (storeObject.has("items")) {
                    Object itemsObject = storeObject.get("items");

                    if (itemsObject instanceof JSONArray) {
                        JSONArray itemListArray = storeObject.getJSONArray("items");
                        for (int i = 0; i < itemListArray.length(); i++) {
                            store.itemList.add(JSONObjectToItem(itemListArray.getJSONObject(i)));
                            if(store.itemList.get(i)==null){
                                Log.v("myerror","fail to get store item");
                            }
                        }
                    } else if (itemsObject instanceof JSONObject) {
                        //TODO: this is for {'ERROR': 'item not found!'} situation
                        store.itemList.clear();
                    }
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
                String base64String = "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";
                byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                item.image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                item.image = null;
                count = count + 1;
                item.itemStoreName = "Red sun store";
                item.itemStoreId = 1;
                item.customerVisits = 0;
            }
            else{

//                Log.d("LastGetContent", "LastGet content: " + itemObject);
                item.itemId = itemObject.getInt("ItemId");


                item.itemName = itemObject.getString("ItemName");
                if(item.itemName == null){
                    Log.d("WA", "json item name: " + itemObject);
                }
                item.itemPrice = itemObject.getDouble("ItemPrice");
                item.itemDescription = itemObject.getString("ItemDescription");
                if(itemObject.getString("ItemImage")=="Not Found"){
                    item.image = null;
                }
                else{
                    String base64String = itemObject.getString("ItemImage");
                    byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
                    item.image = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                }
                item.itemStoreId = itemObject.getInt("ItemStoreId");
                item.itemStoreName = itemObject.getString("ItemStoreName");
//                item.customerVisits = itemObject.getInt("customerVisits");
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
    public static void parseInterface5JsonObject(JSONObject jsonObject, List<HistoryVisit> huntedStoreIdList) {
        try {
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    huntedStoreIdList.add(new HistoryVisit(i, "2024:4:26 18:41:41"));
                }
            }
            else{
                huntedStoreIdList.clear();
                if (jsonObject.has("History")) {
                    Object itemsObject = jsonObject.get("History");

                    if (itemsObject instanceof JSONArray) {
                        JSONArray huntedStoreIdListArray = jsonObject.getJSONArray("History");
                        for (int i = 0; i < huntedStoreIdListArray.length(); i++) {
                            huntedStoreIdList.add(new HistoryVisit(huntedStoreIdListArray.getJSONObject(i).getInt("StoreId"),huntedStoreIdListArray.getJSONObject(i).getString("VisitTime")));
                        }
                    } else if (itemsObject instanceof JSONObject) {
                        //TODO: this is for {'ERROR': "No History to show"} situation
                    }
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
//                Log.d("LastGetContent", "LastGet content: " + jsonObject);
                itemList.clear();
                if (jsonObject.has("ItemList")) {
                    Object itemsObject = jsonObject.get("ItemList");

                    if (itemsObject instanceof JSONArray) {
                        JSONArray itemListArray = jsonObject.getJSONArray("ItemList");
                        for (int i = 0; i < itemListArray.length(); i++) {
                            itemList.add(JSONObjectToItem(itemListArray.getJSONObject(i)));
                        }
                    } else if (itemsObject instanceof JSONObject) {
                        //TODO: this is for {'ERROR': 'item not found!'} situation
                        itemList.clear();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parseInterface7JsonObject(JSONObject jsonObject, List<Store> recommendStoreList) {
        try {
            Log.d("LastGetContent", "LastGet content: " + jsonObject);
            if(DataManager.testSign){
                for (int i = 0; i < 20; i++) {
                    recommendStoreList.add(JSONObjectToStore(null));
                }
            }
            else{
                recommendStoreList.clear();
                JSONArray storeListArray = jsonObject.getJSONArray("StoreList");
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
