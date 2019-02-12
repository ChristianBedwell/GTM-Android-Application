package com.example.navigationdrawer.activity;

import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.navigationdrawer.Constants;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.fragment.LoginFragment;
import com.example.navigationdrawer.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getPreferences(0);
        initFragment();
    }

    private void initFragment(){

        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame, new HomeFragment());
            ft.commit();
        }
        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame, new LoginFragment());
            ft.commit();
        }
    }

}