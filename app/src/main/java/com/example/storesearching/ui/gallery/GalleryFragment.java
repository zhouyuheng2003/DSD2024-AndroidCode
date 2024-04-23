package com.example.storesearching.ui.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.storesearching.R;

import com.example.storesearching.databinding.FragmentGalleryBinding;
import com.example.storesearching.util.*;
import com.example.storesearching.*;

import org.json.JSONException;
import org.json.JSONObject;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        GalleryViewModel galleryViewModel =
//                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLogin;
//        final Button loginButton = root.findViewById(R.id.button_login);
        final Button loginButton = binding.buttonLogin;
        final Button RegistrationButton = binding.buttonRegistration;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText userName = binding.editTextUserName;
                final EditText passWord = binding.editTextUserPassword;

                String name = String.valueOf(userName.getText());
                String password = String.valueOf(passWord.getText());

                if (name.isEmpty() || password.isEmpty())
                {
                    AlertDialogUtils.ShowAlertDialog(getActivity(),"Incomplete input", "User name or password not entered");
                    return;
                }

                JSONObject JsonLogin = JsonUtils.buildJsonObject(1, name, name,password);

                WebServiceManager webServiceManager = WebServiceManager.getInstance();
                webServiceManager.sendJson(1, name, JsonLogin.toString());

                Runnable handleJsonResponse = new Runnable() {
                    @Override
                    public void run() {
                        String LoginRespose = webServiceManager.getJson(1,name);

                        if(!LoginRespose.isEmpty())
                        {
                            try {
                                JSONObject JsonLoginRespose = new JSONObject(LoginRespose);
                                boolean matchToken = JsonLoginRespose.getBoolean("MatchToken");

                                if(matchToken)
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("LoginRespose", LoginRespose);
                                    Navigation.findNavController(view).navigate(R.id.action_nav_gallery_to_nav_UserInfo,bundle);
//                                    Navigation.findNavController(view).navigate(R.id.action_nav_gallery_to_nav_UserInfo);
                                }
                                else
                                {
                                    AlertDialogUtils.ShowAlertDialog(getActivity(),"Login failed", "Username or password incorrect");
                                    return;
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            new Handler().postDelayed(this, 300);
                        }
                    }
                };

                handleJsonResponse.run();
            }
        });

        RegistrationButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

//                Bundle bundle = new Bundle();
                Navigation.findNavController(view).navigate(R.id.action_nav_gallery_to_nav_registration);
//                AlertDialogUtils.ShowAlertDialog(getActivity(),"进行注册","注册成功");
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