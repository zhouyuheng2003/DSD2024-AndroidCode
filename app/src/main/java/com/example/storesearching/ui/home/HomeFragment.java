package com.example.storesearching.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storesearching.DataManager;
import com.example.storesearching.Store;
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
                //TODO:Set distance
                int finalI = i;
                int finalI1 = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        int index = finalI1;
                        bundle.putInt("storeId", index); // 将整数值放入 Bundle 中，使用一个键来标识它
                        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_storeFragment, bundle);
                        //TODO: set visited
                    }
                });
                listItemViews[i] = listItemView;
                linearlayout_searchresult.addView(listItemView);
            }
        }
        else if(SearchMode == 1){
            //TODO:search Item
        }
        else if(SearchMode == 2){
            //TODO:automatically get store recommendations
        }
    }
    public void onResume() {
        super.onResume();
        updateSearchResult();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        //updateUserId;

        //Interface 2, get location
        TestLocationActivity location = new TestLocationActivity(container.getContext(),getActivity(),true);
        location.getLocation();//return a Location
        Toast.makeText(container.getContext(), "version0422b", Toast.LENGTH_SHORT).show();

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Search mode manager, init information
        SearchMode = 0;
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
                    }
                    if(SearchMode == 0) dataManager.SearchStore(query);
//                    else if(HomeSearchMode.SearchMode == 1) dataManager.SearchItem(query);
                    else if(SearchMode == 1){
                        // TODO: SearchItem
                    }
                } catch (JSONException e) {
                    Toast.makeText(container.getContext(), "Fail to parse JSON", Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
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