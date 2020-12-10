package com.example.rbgkr.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.rbgkr.R;

public class Functions {
    public static void changeMainFragment(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container,fragment)
                .commit();
    }
}