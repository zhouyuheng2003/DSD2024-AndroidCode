package com.example.storesearching;

import java.util.ArrayList;
import java.util.List;

public class User {
    public int userId;
    public String UserEmail;
    public String UserName;
    public String HashedPassword;
    public List<Store> storeList;
    public List<Item> itemList;
    public List<Integer> huntedStoreIdList;

    public List<Store> recommendStoreList;
    public User(int userId){
        this.userId = userId;
        UserEmail = "1287121642@qq.com";
        UserName = "";
        HashedPassword = "";
        storeList = new ArrayList<>();
        itemList = new ArrayList<>();
        huntedStoreIdList = new ArrayList<>();
        recommendStoreList = new ArrayList<>();
    }
}