package com.example.storesearching;

public class Item {
    public int itemId;
    public String itemName;
    public double itemPrice;

    public String itemDescription;
    //TODO:base64 image
    public int itemStoreId;
    public String itemStoreName;
    public int customerVisits;
    public Item(){
        itemId = 1;
        itemName = "pencil";
        itemPrice = 1;
        itemStoreId = 1;
        itemStoreName = "Red sun";
        customerVisits = 0;
    }
}
