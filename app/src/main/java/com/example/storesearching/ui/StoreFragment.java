package com.example.storesearching.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storesearching.DataManager;
import com.example.storesearching.Item;
import com.example.storesearching.MapsActivity;
import com.example.storesearching.MyCustomException;
import com.example.storesearching.R;
import com.example.storesearching.Store;
import com.example.storesearching.util.TestLocationActivity;

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
        if(DataManager.searchMode == 0) storeList = dataManager.currentStoreList();
        else storeList = dataManager.currentRecommendStoreList();
        if(storeList.size() > index){
            TextView textView_storeName = root.findViewById(R.id.textView_itemName);
            textView_storeName.setText(storeList.get(index).storeName);
            TextView textView_storeDescription = root.findViewById(R.id.textView_itemDescription);
            textView_storeDescription.setText(storeList.get(index).StoreDescription);
            try {
                dataManager.updateHuntedStoreIdList(index);
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (MyCustomException e) {
                Toast.makeText(container.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
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
                confirmButton.setText("Rated ");
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
                        confirmButton.setText("Rated");
                        DataManager dataManager = DataManager.getInstance();
                        try{
                            dataManager.ratingStore(storeList.get(index).storeId,storeList.get(index).rating,"empty");
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            );

            Button button_visitStore = root.findViewById(R.id.button_visitStore);
            button_visitStore.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     TestLocationActivity testLocationActivity = TestLocationActivity.getInstance(null,null,false,null);
                     MapsActivity.DataFromHome = new String[][]{
//                             {"User Location", String.valueOf(testLocationActivity.getLatitude()+0.003728),
//                                     String.valueOf(testLocationActivity.getLongitude()+0.004866)},
                             {"Store " + storeList.get(index).storeName, String.valueOf(storeList.get(index).location.latitude),
                                     String.valueOf(storeList.get(index).location.longitude)}};
//                     {
//                         {"User Location(" + testLocationActivity.getLatitude() +","+ testLocationActivity.getLongitude() +")", String.valueOf(testLocationActivity.getLatitude()),
//                                 String.valueOf(testLocationActivity.getLongitude())},
//                         {"Store " + storeList.get(index).storeName, String.valueOf(storeList.get(index).location.latitude),
//                                 String.valueOf(storeList.get(index).location.longitude)}};
                     Navigation.findNavController(v).navigate(R.id.action_storeFragment_to_map);

                 }
             }
            );



            View[] listItemViews = new View[20];
            List<Item> itemList = storeList.get(index).itemList;
            LinearLayout storeItemLayout = root.findViewById(R.id.storeItemLayout);
            storeItemLayout.removeAllViews();
            for (int i = 0; i < 20; i++) {
                if(i >= itemList.size())break;
                View listItemView = inflater.inflate(R.layout.layout_listitem_item, null);
                Button button = listItemView.findViewById(R.id.button);
                TextView textView_no = listItemView.findViewById(R.id.textView_no);
                textView_no.setText("Number"+ (i+1) +": ");
                TextView textView_name = listItemView.findViewById(R.id.textView_name);
                textView_name.setText(itemList.get(i).itemName);
                TextView textView_des = listItemView.findViewById(R.id.textView_des);
                textView_des.setText("Description:" + itemList.get(i).itemDescription);
                ImageView imageView_smallItem = listItemView.findViewById(R.id.imageView_smallItem);
                imageView_smallItem.setImageBitmap(itemList.get(i).image);


                int finalI = i;
                int finalI1 = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        int Index = finalI1;
                        bundle.putInt("storeId",index);
                        bundle.putInt("itemId", Index); // 将整数值放入 Bundle 中，使用一个键来标识它
                        Navigation.findNavController(view).navigate(R.id.action_storeFragment_to_itemFragment, bundle);
                    }
                });
                listItemViews[i] = listItemView;
                storeItemLayout.addView(listItemView);
            }

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