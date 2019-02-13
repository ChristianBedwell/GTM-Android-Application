package com.example.navigationdrawer.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.navigationdrawer.Constants;
import com.example.navigationdrawer.R;

public class HomeFragment extends Fragment {

    private TextView tvFullName,tvWelcomeMessage;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        tvFullName = (TextView) view.findViewById(R.id.tvFullName);
        tvWelcomeMessage = (TextView) view.findViewById(R.id.tvWelcomeMessage);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        tvFullName.setText("Welcome, " + pref.getString(Constants.NAME,"") + "!");
        tvWelcomeMessage.setText("Here in this companion app, you will find all features suited " +
                "for your niche. Click on the navigation menu ☰ to begin!");
    }
}
