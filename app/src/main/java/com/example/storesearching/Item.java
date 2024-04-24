package com.example.storesearching;
import android.graphics.Bitmap;

public class Item {
    public int itemId;
    public String itemName;
    public double itemPrice;

    public String itemDescription;
    public Bitmap image;
    public int itemStoreId;
    public String itemStoreName;
    public int customerVisits;
    public int rating, rating_fixed;
    public Item(){
        itemId = 1;
        itemName = "pencil";
        itemPrice = 1;
        itemStoreId = 1;
        itemStoreName = "Red sun";
        customerVisits = 0;
        rating = 0;
        rating_fixed = 0;
        image = null;
    }
}