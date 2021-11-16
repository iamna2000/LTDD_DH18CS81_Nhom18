package com.example.messapp;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTab;

    public LoginAdapter(FragmentManager fm, Context context, int totalTab){
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    public Fragment getItem(int possition){
        switch (possition){
            case 0:
                LoginTabFragment loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            case 1:
                SignupTabFragment signupTabFragment = new SignupTabFragment();
                return signupTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTab;
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String title = "";
//        switch (position){
//            case 0:
//                title = "Login";
//                break;
//            case 1:
//                title = "Sign Up";
//                break;
//        }
//        return title;
//    }
}
