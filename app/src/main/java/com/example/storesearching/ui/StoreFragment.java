package com.example.storesearching.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.storesearching.DataManager;
import com.example.storesearching.R;
import com.example.storesearching.Store;

import java.util.List;


public class StoreFragment extends Fragment {

    private View root;

    private int index;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        index = 0;
        if (bundle != null) {
            index = bundle.getInt("storeId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_store, container, false);
        DataManager dataManager = DataManager.getInstance();
        List<Store> storeList = dataManager.currentStoreList();
        if(storeList.size() > index){
            TextView textView_storeName = root.findViewById(R.id.textView_storeName);
            textView_storeName.setText(storeList.get(index).storeName);
            TextView textView_storeDescription = root.findViewById(R.id.textView_storeDescription);
            textView_storeDescription.setText(storeList.get(index).StoreDescription);

        }
        else{
            TextView textView_storeName = root.findViewById(R.id.textView_storeName);
            textView_storeName.setText("Store Not Exists");
        }
        return root;
//        return inflater.inflate(R.layout.fragment_store, container, false);
    }
}