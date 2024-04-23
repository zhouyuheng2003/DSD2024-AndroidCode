package com.example.storesearching.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.storesearching.R;
import com.example.storesearching.WebServiceManager;
import com.example.storesearching.databinding.FragmentGalleryBinding;
import com.example.storesearching.databinding.FragmentRegistrationBinding;
import com.example.storesearching.util.AlertDialogUtils;
import com.example.storesearching.util.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button RegButton = binding.RegButton;

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText userNameEdit = binding.RegNameEdit;
                final EditText passwordEdit = binding.RegPasswordEdit;
                final EditText dupPasswordEdit = binding.RegDupPasswordEdit;

                String userName = String.valueOf(userNameEdit.getText());
                String password = String.valueOf(passwordEdit.getText());
                String dupPassword = String.valueOf(dupPasswordEdit.getText());

                if(userName.isEmpty() || password.isEmpty() || dupPassword.isEmpty())
                {
                    AlertDialogUtils.ShowAlertDialog(getActivity(),"Incomplete input", "user name or password or Duplicate password not entered");
                    return;
                }

                if(!password.equals(dupPassword))
                {
                    AlertDialogUtils.ShowAlertDialog(getActivity(),"input error","password and duplicate password do not match");
                    return;
                }
                JSONObject RegJson = JsonUtils.buildJsonObject(12,userName,userName,password);
                WebServiceManager webServiceManager = WebServiceManager.getInstance();
                webServiceManager.sendJson(12,userName,RegJson.toString());

                Runnable handleRegJsonResponse = new Runnable() {
                    @Override
                    public void run() {

                        String RegResponse = webServiceManager.getJson(12,userName);

                        if(!RegResponse.isEmpty())
                        {
                            try {
                                JSONObject RegJsonRespose = new JSONObject(RegResponse);
                                boolean Successfultoken = RegJsonRespose.getBoolean("successful_token");

                                if(Successfultoken)
                                {
                                    Navigation.findNavController(view).navigate(R.id.action_nav_registration_to_nav_gallery);
                                }
                                else
                                {
                                    AlertDialogUtils.ShowAlertDialog(getActivity(),"Registration failed", "Username incorrect");
                                    return;
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            new Handler().postDelayed(this, 300);
                        }
                    }
                };
                handleRegJsonResponse.run();

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
