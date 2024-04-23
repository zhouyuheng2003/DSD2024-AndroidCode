package com.example.storesearching.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.storesearching.R;
import com.example.storesearching.WebServiceManager;
import com.example.storesearching.databinding.FragmentGalleryBinding;
import com.example.storesearching.databinding.FragmentUserinfochangeBinding;
import com.example.storesearching.util.AlertDialogUtils;
import com.example.storesearching.util.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoChangeFragment extends Fragment {

    private FragmentUserinfochangeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentUserinfochangeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button confrimButton = binding.confirmButton;

        confrimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText userNameEdit = binding.UserNameEdit;
                final EditText passwordEdit = binding.PasswordEdit;
                final EditText BirthdayEdit = binding.BirthdayEdit;
                final EditText DescriptionTextEdit = binding.DescriptionTextEdit;

                String userName = String.valueOf(userNameEdit.getText());
                String password = String.valueOf(passwordEdit.getText());
                String Birthday = String.valueOf(BirthdayEdit.getText());
                String DescriptionText = String.valueOf(DescriptionTextEdit.getText());

                if(userName.isEmpty() || password.isEmpty() || Birthday.isEmpty() || DescriptionText.isEmpty())
                {
                    AlertDialogUtils.ShowAlertDialog(getActivity(),"Incomplete input", "There is empty space in the input value");
                    return;
                }

                SimpleDateFormat dateFormat_1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dateFormat_2 = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat dateFormat_3 = new SimpleDateFormat("yyyy/MM/dd");

                try {
                    Date date = null;
                    if(Birthday.contains("-")) {date = dateFormat_1.parse(Birthday);}
                        else if(Birthday.contains(".")){date = dateFormat_2.parse(Birthday);}
                            else if(Birthday.contains("/")) {date = dateFormat_3.parse(Birthday);}

                    String[] Descriptions = DescriptionText.split(";");
                    JSONArray Description_array = new JSONArray();
                    for (String Description : Descriptions) {Description_array.put(Description);}

                    JSONObject ChangeInfo = JsonUtils.buildInterface10JsonObject(10,userName,userName,password,date,Description_array);

                    WebServiceManager webServiceManager = WebServiceManager.getInstance();
                    webServiceManager.sendJson(10,userName,ChangeInfo.toString());

                    Bundle bundle = new Bundle();

                    bundle.putString("LoginRespose",ChangeInfo.toString());
                    Navigation.findNavController(view).navigate(R.id.action_nav_changeInfo_to_nav_UserInfo,bundle);

                }catch (ParseException e){
                    e.printStackTrace();
                }
            }
        });
        return root;
    }
}
