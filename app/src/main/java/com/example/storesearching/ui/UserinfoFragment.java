package com.example.storesearching.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.storesearching.databinding.FragmentRegistrationBinding;
import com.example.storesearching.databinding.FragmentUserinfoBinding;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.example.storesearching.R;

public class UserinfoFragment extends Fragment{

    private FragmentUserinfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserinfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView userName = binding.UserNameLabel;
        final TextView userDescription = binding.DescriptionTextLabel;

        final Button changeInfoButton = binding.ChangeButton;
        final Button ExitButton = binding.ExitButton;

        Bundle bundle = getArguments();

        String LoginRespose = bundle.getString("LoginRespose");
///*
        try {
            JSONObject LoginJsonRespose = new JSONObject(LoginRespose);

            String username = LoginJsonRespose.getString("CurrentUser");
//            String password = LoginJsonRespose.get("Interests");
            JSONArray  description = LoginJsonRespose.getJSONArray("Interests");

            String depcriptionText = "";

            for(int i = 0 ; i < description.length(); i++)
            {
                depcriptionText +=  description.getString(i) + ";";

                if( (i+1) % 5==0) depcriptionText += "\n";
            }

            userName.setTextSize(30);
//            userName.setGravity();
            userDescription.setTextSize(30);

            userName.setText("UserName:"+username);
            userDescription.setText(depcriptionText);

        }catch (JSONException e){
            e.printStackTrace();
        }
//        */


        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_UserInfo_to_nav_gallery);
            }
        });

        changeInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_UserInfo_to_nav_changeInfo);
            }
        });
        return root;
    }
}
