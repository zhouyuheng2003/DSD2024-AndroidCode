package com.example.storesearching;

import java.util.List;

public class Store {
    public int storeId;
    public String storeName;
    //    public Location location;
//    public List<Item> items
    public String StoreDescription;
    public int rating, rating_fixed;
    public Store() {
        storeId = 1;
        storeName = "";
        StoreDescription = "";
        rating = 0;
        rating_fixed = 0;
    }
}