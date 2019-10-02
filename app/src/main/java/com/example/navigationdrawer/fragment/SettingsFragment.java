package com.example.navigationdrawer.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.navigationdrawer.Constants;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.RequestInterface;
import com.example.navigationdrawer.models.ServerRequest;
import com.example.navigationdrawer.models.ServerResponse;
import com.example.navigationdrawer.models.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingsFragment extends PreferenceFragmentCompat {

    private TextView tvStatusMessage;
    private EditText etOldPassword,etNewPassword;
    private ProgressBar progress;
    private AlertDialog dialog;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Objects.requireNonNull(getActivity()).setTitle("Settings");
        setPreferencesFromResource(R.xml.pref_settings, rootKey);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("Constants", Context.MODE_PRIVATE);

        Preference bChangePassword = findPreference("changePassword");
        bChangePassword.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog();
                return true;
            }
        });
    }

    private void showDialog() throws Resources.NotFoundException {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);

        etOldPassword = (EditText) view.findViewById(R.id.et_old_password);
        etNewPassword = (EditText) view.findViewById(R.id.et_new_password);
        tvStatusMessage = (TextView) view.findViewById(R.id.tv_status_message);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Change Password")
                .setPositiveButton("Change Password", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String old_password = etOldPassword.getText().toString();
                        String new_password = etNewPassword.getText().toString();

                        if (!old_password.isEmpty() && !new_password.isEmpty()) {
                            progress.setVisibility(View.VISIBLE);
                            changePasswordProcess(sharedPreferences.getString(Constants.EMAIL,""), old_password, new_password);
                        }
                        else {
                            tvStatusMessage.setVisibility(View.VISIBLE);
                            tvStatusMessage.setText("Fields are empty");
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private void changePasswordProcess(String email, String old_password, String new_password) {

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
                if (resp.getResult().equals(Constants.SUCCESS)) {
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