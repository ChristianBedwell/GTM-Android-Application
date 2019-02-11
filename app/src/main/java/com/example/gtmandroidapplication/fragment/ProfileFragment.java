package com.example.gtmandroidapplication.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gtmandroidapplication.R;
import com.example.gtmandroidapplication.Constants;
import com.example.gtmandroidapplication.RequestInterface;
import com.example.gtmandroidapplication.models.ServerRequest;
import com.example.gtmandroidapplication.models.ServerResponse;
import com.example.gtmandroidapplication.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tvFullName,tvWelcomeMessage,tvStatusMessage;
    private SharedPreferences pref;
    private Button bChangePassword,bLogout;
    private EditText etOldPassword,etNewPassword;
    private AlertDialog dialog;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);
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

    private void initViews(View view){

        tvFullName = (TextView) view.findViewById(R.id.tvFullName);
        tvWelcomeMessage = (TextView) view.findViewById(R.id.tvWelcomeMessage);
        bChangePassword = (Button) view.findViewById(R.id.bChangePassword);
        bLogout = (Button) view.findViewById(R.id.bLogout);
        bChangePassword.setOnClickListener(this);
        bLogout.setOnClickListener(this);

    }

    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        etOldPassword = (EditText) view.findViewById(R.id.et_old_password);
        etNewPassword = (EditText) view.findViewById(R.id.et_new_password);
        tvStatusMessage = (TextView) view.findViewById(R.id.tv_status_message);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Change Password");
        builder.setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_password = etOldPassword.getText().toString();
                String new_password = etNewPassword.getText().toString();
                if(!old_password.isEmpty() && !new_password.isEmpty()){

                    progress.setVisibility(View.VISIBLE);
                    changePasswordProcess(pref.getString(Constants.EMAIL,""),old_password,new_password);

                }else {

                    tvStatusMessage.setVisibility(View.VISIBLE);
                    tvStatusMessage.setText("Fields are empty");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bChangePassword:
                showDialog();
                break;
            case R.id.bLogout:
                logout();
                break;
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        editor.putString(Constants.EMAIL,"");
        editor.putString(Constants.NAME,"");
        editor.putString(Constants.UNIQUE_ID,"");
        editor.apply();
        goToLogin();
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }

    private void changePasswordProcess(String email,String old_password,String new_password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(email);
        user.setOld_password(old_password);
        user.setNew_password(new_password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                if(resp.getResult().equals(Constants.SUCCESS)){
                    progress.setVisibility(View.GONE);
                    tvStatusMessage.setVisibility(View.GONE);
                    dialog.dismiss();
                    Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();

                }
                else {
                    progress.setVisibility(View.GONE);
                    tvStatusMessage.setVisibility(View.VISIBLE);
                    tvStatusMessage.setText(resp.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.d(Constants.TAG,"failed");
                progress.setVisibility(View.GONE);
                tvStatusMessage.setVisibility(View.VISIBLE);
                tvStatusMessage.setText(t.getLocalizedMessage());

            }
        });
    }
}