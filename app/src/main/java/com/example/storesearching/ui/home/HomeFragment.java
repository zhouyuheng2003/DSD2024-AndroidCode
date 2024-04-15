package com.example.storesearching.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.storesearching.databinding.FragmentHomeBinding;
import android.widget.SearchView;
import android.widget.Button;
import com.example.storesearching.R;
import android.util.TypedValue;
import android.util.Log;
import android.graphics.Color;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private View root;
    class HomeState{
        public int SearchMode;
        public String[] textView_SearchMode_content;
        public String[] button_SwitchSearchMode_content;


        public HomeState(){
            SearchMode = 0;
            textView_SearchMode_content = new String[2];
            button_SwitchSearchMode_content = new String[2];
            textView_SearchMode_content[0] = "stores";
            textView_SearchMode_content[1] = "items";
            button_SwitchSearchMode_content[0] = "store";
            button_SwitchSearchMode_content[1] = "item";
            update();
        }
        private void update(){
            //搜索设置文本框
            TextView textView_SearchMode = root.findViewById(R.id.textView_SearchMode);
            textView_SearchMode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            textView_SearchMode.setText("Search for " + textView_SearchMode_content[SearchMode]);

            //搜索模式切换按钮
            Button button_SwitchSearchMode = root.findViewById(R.id.button_SwitchSearchMode);
            button_SwitchSearchMode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            button_SwitchSearchMode.setText("Switch to " + button_SwitchSearchMode_content[SearchMode ^ 1]);
            button_SwitchSearchMode.setBackgroundColor(Color.parseColor("#B9DADF"));
            button_SwitchSearchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchMode^=1;
                    update();
                    Log.d("Button Clicked", "Button SwitchSearchMode clicked!");
                }
            });
        }
        public void flip(){
            SearchMode = SearchMode ^ 1;
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //初始化search mode
        HomeState HomeSearchMode = new HomeState();



        //搜索框及其设置
        SearchView searchView = root.findViewById(R.id.mainSearchView);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 当用户提交查询文本时调用
                // 在这里可以执行相应的搜索操作
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