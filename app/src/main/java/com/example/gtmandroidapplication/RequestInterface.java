package com.example.gtmandroidapplication;

import com.example.gtmandroidapplication.models.ServerRequest;
import com.example.gtmandroidapplication.models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("learn2crack-login-register/index.php")
    Call<ServerResponse> operation(@Body ServerRequest request);

}