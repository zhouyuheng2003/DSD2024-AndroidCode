package com.example.storesearching.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storesearching.DataManager;
import com.example.storesearching.R;
import com.example.storesearching.Store;

import java.util.List;


public class StoreFragment extends Fragment {

    private View root;

    private int index;
    private int currentRating;
    private ImageButton[] ratingButton;
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

            currentRating = 0;
            ratingButton = new ImageButton[5];
            ratingButton[0] = root.findViewById(R.id.ratingButton1);
            ratingButton[1] = root.findViewById(R.id.ratingButton2);
            ratingButton[2] = root.findViewById(R.id.ratingButton3);
            ratingButton[3] = root.findViewById(R.id.ratingButton4);
            ratingButton[4] = root.findViewById(R.id.ratingButton5);
            for (int i = 0; i < ratingButton.length; i++) {
                final int rating = i + 1;
                ratingButton[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Button confirmButton = root.findViewById(R.id.confirmButton);
                        confirmButton.setText("CONFIRM " + rating);
                        setCurrentRating(rating);
                    }
                });
            }
            Button confirmButton = root.findViewById(R.id.confirmButton);
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentRating == 0){
                        //TODO:提示
                    }
                    else{
                        //TODO:发送
                    }
                }
            });
        }
        else{
            TextView textView_storeName = root.findViewById(R.id.textView_storeName);
            textView_storeName.setText("Store Not Exists");
        }
        return root;
//        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    private void setCurrentRating(int rating) {
        currentRating = rating;
        for (int i = 0; i < ratingButton.length; i++) {
            if (i < rating) {
                ratingButton[i].setImageResource(android.R.drawable.btn_star_big_on); // 设置亮的颜色
            } else {
                ratingButton[i].setImageResource(android.R.drawable.btn_star_big_off); // 设置暗的颜色
            }
        }
    }
}