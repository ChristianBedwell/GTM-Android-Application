package com.example.navigationdrawer.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.navigationdrawer.R;

public class HomeFragment extends Fragment {

    private TextView tvFullName,tvWelcomeMessage;
    private SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        tvFullName = (TextView) view.findViewById(R.id.tvFullName);
        tvWelcomeMessage = (TextView) view.findViewById(R.id.tvWelcomeMessage);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        pref = getActivity().getSharedPreferences("Constants", Context.MODE_PRIVATE);
        tvFullName.setText("Welcome to the GTM Home Services Companion App!");
        tvWelcomeMessage.setText("Click on the navigation menu (☰) to begin exploring what the app has to offer!");
    }
}
