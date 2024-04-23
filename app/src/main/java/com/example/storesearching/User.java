package com.example.storesearching;

import java.util.ArrayList;
import java.util.List;

public class User {
    public int userId;
    public String UserEmail;
    public String UserName;
    public String HashedPassword;
    public List<Store> storeList;
    public User(int userId){
        this.userId = userId;
        UserEmail = "1287121642@qq.com";
        UserName = "";
        HashedPassword = "";
        storeList = new ArrayList<>();
<<<<<<< Updated upstream
=======
        itemList = new ArrayList<>();
        huntedStoreIdList = new ArrayList<>();
>>>>>>> Stashed changes
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
//            storeList.add(new Store());
    }
}
