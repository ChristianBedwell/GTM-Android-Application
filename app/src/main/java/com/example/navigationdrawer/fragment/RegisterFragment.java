package com.example.navigationdrawer.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.example.navigationdrawer.Constants;
import com.example.navigationdrawer.R;
import com.example.navigationdrawer.RequestInterface;
import com.example.navigationdrawer.models.ServerRequest;
import com.example.navigationdrawer.models.ServerResponse;
import com.example.navigationdrawer.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends Fragment  implements View.OnClickListener {

    private Button bRegister;
    private EditText etEmail, etPassword, etFullName;
    private TextView tvLoginLink;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        bRegister = (Button) view.findViewById(R.id.bRegister);
        tvLoginLink = (TextView) view.findViewById(R.id.tvLoginLink);
        etFullName = (EditText) view.findViewById(R.id.etFullName);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        progress = (ProgressBar) view.findViewById(R.id.progress);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvLoginLink:
                goToLogin();
                break;

            case R.id.bRegister:

                String name = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(name,email,password);

                }
                else {

                    Snackbar.make(getView(), "Fields are empty!", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registerProcess(String name, String email,String password){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.INVISIBLE);
                goToLogin();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void goToLogin(){

        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
}