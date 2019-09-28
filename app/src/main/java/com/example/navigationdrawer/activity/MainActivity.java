package com.example.navigationdrawer.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.Constants;
import com.example.navigationdrawer.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("Constants", Context.MODE_PRIVATE);
        initFragment();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    private void initFragment() {

        if(pref.getBoolean(Constants.IS_LOGGED_IN,false)) {
            Intent homeIntent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(homeIntent);
        }
        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_frame, new LoginFragment());
            ft.commit();
        }
    }
}