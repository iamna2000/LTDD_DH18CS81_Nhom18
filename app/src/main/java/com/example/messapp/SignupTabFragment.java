package com.example.messapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.fragment.app.Fragment;

public class SignupTabFragment extends Fragment {

    EditText username, email, password, confirmPass;
    Button signup;

    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);


        username = root.findViewById(R.id.username);
        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        confirmPass = root.findViewById(R.id.confirm_password);
        signup = root.findViewById(R.id.btn_signup);

        email.setTranslationX(800);
        password.setTranslationX(800);
//        confirmPass.setTranslationX(800);
        username.setTranslationX(800);
        signup.setTranslationX(800);

        username.setAlpha(v);
        email.setAlpha(v);
        password.setAlpha(v);
        confirmPass.setAlpha(v);
        signup.setAlpha(v);

        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        confirmPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();


        return root;
    }

}
