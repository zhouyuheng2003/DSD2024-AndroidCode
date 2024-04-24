package com.example.storesearching;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public int storeId;
    public String storeName;
    public myLocation location;
    public List<Item> itemList;
    public String StoreDescription;
    public int rating, rating_fixed;
    public Store() {
        storeId = 1;
        storeName = "";
        location = new myLocation();
        StoreDescription = "";
        rating = 0;
        rating_fixed = 0;
        itemList = new ArrayList<>();
    }
}