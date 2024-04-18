package com.example.storesearching;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static DataManager instance;
    private Map< Integer, UserManager> users;
    private DataManager() {
        users = new HashMap<Integer, UserManager>();
    }
    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
            instance.users.put(0, new UserManager());
        }
        return instance;
    }
    public static class UserManager {
        public int userId;
        public String UserEmail;
        public String UserName;
        public String HashedPassword;
        public List <Store> storeList;
        public UserManager(){
            userId = 1;
            UserEmail = "1287121642@qq.com";
            UserName = "Alice";
            HashedPassword = "Bob";
            storeList = new ArrayList<>();
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
            storeList.add(new Store());
        }
    }
}
