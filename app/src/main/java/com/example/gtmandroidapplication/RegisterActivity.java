package com.example.gtmandroidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Response;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFirstName, etLastName, etEmail, etPassword;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                final String firstname = etFirstName.getText().toString();
                final String lastname = etLastName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } 
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                break;
        }
    }
    //RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, email, password, responseListener);
    //RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
    //queue.add(registerRequest);
}
