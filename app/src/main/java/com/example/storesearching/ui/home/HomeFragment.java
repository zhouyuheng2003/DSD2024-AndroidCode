package com.example.storesearching.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storesearching.DataManager;
import com.example.storesearching.Item;
import com.example.storesearching.MyCustomException;
import com.example.storesearching.Store;
import com.example.storesearching.WebServiceManager;
import com.example.storesearching.databinding.FragmentHomeBinding;
import android.widget.SearchView;
import android.widget.LinearLayout;
import android.widget.Button;
import com.example.storesearching.R;
import com.example.storesearching.util.TestLocationActivity;

import android.util.TypedValue;
import android.util.Log;
import android.graphics.Color;
import android.widget.Toast;

import androidx.navigation.Navigation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    //Fragment attribute
    private FragmentHomeBinding binding;
    private View root;
    private LayoutInflater inflater;


    //SearchView UI
    public int SearchMode;//0 means search for stores, 1 means search for items
    public String[] textView_SearchMode_content;//tags
    public String[] button_SwitchSearchMode_content;
    private void update(){
        //搜索设置文本框
        TextView textView_SearchMode = root.findViewById(R.id.textView_SearchMode);
        textView_SearchMode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        if(SearchMode == 2) textView_SearchMode.setText("Hunting Stores");
        else textView_SearchMode.setText("Search For " + textView_SearchMode_content[SearchMode]);

        //搜索模式切换按钮
        Button button_SwitchSearchMode = root.findViewById(R.id.button_SwitchSearchMode);
        button_SwitchSearchMode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
//            button_SwitchSearchMode.setText("Switch to " + button_SwitchSearchMode_content[SearchMode ^ 1]);
        button_SwitchSearchMode.setText("Switch");
        button_SwitchSearchMode.setBackgroundColor(Color.parseColor("#B9DADF"));

        button_SwitchSearchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMode = (SearchMode + 1) %3;
                DataManager.searchMode = SearchMode;
                update();
                updateSearchResult();
                Log.d("Button Clicked", "Button SwitchSearchMode clicked!");
            }
        });
    }
    public void updateRecommendStoreList(){
        if(SearchMode == 2){
            updateSearchResult();
        }
    }
    public String ShowDistance(double dis) {
        //If it is under 1KM, it will show as meters, without the ".x"
        if(dis < 0.95)
            return (String.format("%.0f", dis * 1000) + " m");

        //If between 950 m and 1 Km, it wil show just as 1 Km
        if(dis < 1)
            return "1 Km";

        String dist = String.format("%.1f", dis);

        //It will show as KM, without the ".x"
        if(dist.endsWith(",0") || dist.endsWith(".0"))
            return (String.format("%.0f", dis) + " km");

        //It will show as KM, with the ".x"
        return (dist + " km");
    }
    private void updateSearchResult(){//update the content of ScrollView
        LinearLayout linearlayout_searchresult = root.findViewById(R.id.linearlayout_searchresult);
        linearlayout_searchresult.removeAllViews();

        View[] listItemViews = new View[20];

        DataManager dataManager = DataManager.getInstance();
        if(SearchMode == 0){
            List<Store> storeList = dataManager.currentStoreList();
            for (int i = 0; i < 20; i++) {
                if(i >= storeList.size())break;
                View listItemView = inflater.inflate(R.layout.layout_listitem_store, null);
                Button button = listItemView.findViewById(R.id.button);
                TextView textView_no = listItemView.findViewById(R.id.textView_no);
                textView_no.setText("Number"+ (i+1) +": ");
                TextView textView_name = listItemView.findViewById(R.id.textView_name);
                textView_name.setText(storeList.get(i).storeName);
                TextView textView_des = listItemView.findViewById(R.id.textView_des);
                textView_des.setText("Description:" + storeList.get(i).StoreDescription);

                TextView textView_dis = listItemView.findViewById(R.id.textView_dis);
                textView_dis.setText(ShowDistance(storeList.get(i).location.getDistance()));
//                textView_dis.setText(storeList.get(i).location.getDistance()+"km");
                int finalI = i;
                int finalI1 = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        int index = finalI1;
                        bundle.putInt("storeId", index); // 将整数值放入 Bundle 中，使用一个键来标识它
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_storeFragment, bundle);
                    }
                });
                listItemViews[i] = listItemView;
                linearlayout_searchresult.addView(listItemView);
            }
        }
        else if(SearchMode == 1){
            List<Item> itemList = dataManager.currentItemList();
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
                        int index = finalI1;
                        bundle.putInt("storeId",-1);
                        bundle.putInt("itemId", index); // 将整数值放入 Bundle 中，使用一个键来标识它
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_itemFragment, bundle);
                    }
                });
                listItemViews[i] = listItemView;
                linearlayout_searchresult.addView(listItemView);
            }
        }
        else if(SearchMode == 2){
            try{
                dataManager.getRecommendStoreList();
//                Toast.makeText(container.getContext(), "ok2", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (MyCustomException e) {
                Toast.makeText(container.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            List<Store> recommendStoreList = dataManager.users.get(dataManager.currentUserId).recommendStoreList;
//            Toast.makeText(container.getContext(), "ok3", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < 20; i++) {
                if(i >= recommendStoreList.size())break;
                double dis = recommendStoreList.get(i).location.getDistance();
                if(dis>DataManager.distanceLimit)continue;
//                Log.d("val",recommendStoreList.get(i).storeId+"id");
                View listItemView = inflater.inflate(R.layout.layout_listitem_store, null);
                Button button = listItemView.findViewById(R.id.button);
                TextView textView_no = listItemView.findViewById(R.id.textView_no);
                textView_no.setText("Number"+ (i+1) +": ");
                TextView textView_name = listItemView.findViewById(R.id.textView_name);
                textView_name.setText(recommendStoreList.get(i).storeName);
                TextView textView_des = listItemView.findViewById(R.id.textView_des);
                textView_des.setText("Description:" + recommendStoreList.get(i).StoreDescription);

                TextView textView_dis = listItemView.findViewById(R.id.textView_dis);
                textView_dis.setText(dis+"km");
                int finalI = i;
                int finalI1 = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        int index = finalI1;
                        bundle.putInt("storeId", index); // 将整数值放入 Bundle 中，使用一个键来标识它
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_storeFragment, bundle);
                    }
                });
                listItemViews[i] = listItemView;
                linearlayout_searchresult.addView(listItemView);
            }
        }
    }
    public void onResume() {
        super.onResume();
        updateSearchResult();
    }
    private ViewGroup container;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        //updateUserId;

        //Interface 2, get location
        TestLocationActivity location = TestLocationActivity.getInstance(container.getContext(),getActivity(),true,this);
        location.getLocation();//return a Location
        Toast.makeText(container.getContext(), "version0529b", Toast.LENGTH_SHORT).show();
//        try {
//            WebServiceManager webServiceManager = WebServiceManager.getInstance();
//            JSONObject postData = new JSONObject();
//            postData.put("InterfaceId", 6);
//            postData.put("CurrentUser", "current_user_name");
//            postData.put("ItemName", "user_name");
//            String res = WebServiceManager.uploadJson(1, "me", postData.toString());
//            Toast.makeText(container.getContext(), res == null ? "empty": res, Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//
//        }
        location.TencentLocationActivity();
        this.container = container;

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Search mode manager, init information
//        SearchMode = 0;
        DataManager.searchMode = SearchMode;
        textView_SearchMode_content = new String[2];
        button_SwitchSearchMode_content = new String[2];
        textView_SearchMode_content[0] = "Stores";
        textView_SearchMode_content[1] = "Items";
        button_SwitchSearchMode_content[0] = "store";
        button_SwitchSearchMode_content[1] = "item";
        update();

        //SearchView setting
        SearchView searchView = root.findViewById(R.id.mainSearchView);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DataManager dataManager = DataManager.getInstance();
                try {
                    if(SearchMode == 2){
                        SearchMode = 0;
                        DataManager.searchMode = SearchMode;
                        update();
                    }
                    if(SearchMode == 0) dataManager.SearchStore(query);
                    else if(SearchMode == 1) dataManager.SearchItem(query);
                } catch (JSONException e) {
                    Toast.makeText(container.getContext(), "Fail to parse JSON", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }catch (MyCustomException e) {
                    Toast.makeText(container.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
                updateSearchResult();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 当用户输入或修改查询文本时调用
                // 在这里可以根据新的文本进行实时搜索或过滤操作
                return false;
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}