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

import org.json.JSONException;

import java.util.List;


public class StoreFragment extends Fragment {

    private View root;

    private int index;
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
    private List<Store> storeList;
    private ViewGroup container;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.container = container;
        root = inflater.inflate(R.layout.fragment_store, container, false);
        DataManager dataManager = DataManager.getInstance();
        storeList = dataManager.currentStoreList();
        if(storeList.size() > index){
            TextView textView_storeName = root.findViewById(R.id.textView_itemName);
            textView_storeName.setText(storeList.get(index).storeName);
            TextView textView_storeDescription = root.findViewById(R.id.textView_itemDescription);
            textView_storeDescription.setText(storeList.get(index).StoreDescription);
<<<<<<< Updated upstream
=======
            try {
                dataManager.updateHuntedStoreIdList(index);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
>>>>>>> Stashed changes
            ratingButton = new ImageButton[5];
            ratingButton[0] = root.findViewById(R.id.ratingButton1);
            ratingButton[1] = root.findViewById(R.id.ratingButton2);
            ratingButton[2] = root.findViewById(R.id.ratingButton3);
            ratingButton[3] = root.findViewById(R.id.ratingButton4);
            ratingButton[4] = root.findViewById(R.id.ratingButton5);
            if(storeList.get(index).rating_fixed == 0)setCurrentRating(0);
            else{
                for (int i = 0; i < ratingButton.length; i++) {
                    if (i < storeList.get(index).rating) {
                        ratingButton[i].setImageResource(android.R.drawable.btn_star_big_on); // 设置亮的颜色
                    } else {
                        ratingButton[i].setImageResource(android.R.drawable.btn_star_big_off); // 设置暗的颜色
                    }
                }
                Button confirmButton = root.findViewById(R.id.confirmButton);
                confirmButton.setText("CONFIRMED ");
            }
            for (int i = 0; i < ratingButton.length; i++) {
                final int rating = i + 1;
                ratingButton[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCurrentRating(rating);
                    }
                });
            }
            Button confirmButton = root.findViewById(R.id.confirmButton);
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(storeList.get(index).rating_fixed == 1){
                        Toast.makeText(container.getContext(), "This store has already been rated", Toast.LENGTH_SHORT).show();
                    }
                    else if(storeList.get(index).rating == 0){
                        Toast.makeText(container.getContext(), "Please rate for this store", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        storeList.get(index).rating_fixed = 1;
                        Button confirmButton = root.findViewById(R.id.confirmButton);
                        confirmButton.setText("CONFIRMED ");
                        //TODO:发送
                    }
                }
            });
        }
        else{
            TextView textView_storeName = root.findViewById(R.id.textView_itemName);
            textView_storeName.setText("Store Not Exists");
        }
        return root;
//        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    private void setCurrentRating(int rating) {
        if(storeList.get(index).rating_fixed == 1){
            Toast.makeText(container.getContext(), "This store has already been rated", Toast.LENGTH_LONG).show();
            return;
        }
        storeList.get(index).rating = rating;
        for (int i = 0; i < ratingButton.length; i++) {
            if (i < rating) {
                ratingButton[i].setImageResource(android.R.drawable.btn_star_big_on); // 设置亮的颜色
            } else {
                ratingButton[i].setImageResource(android.R.drawable.btn_star_big_off); // 设置暗的颜色
            }
        }
    }
}