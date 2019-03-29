package com.example.sabuj.artificialsoft.api;

import com.example.sabuj.artificialsoft.models.UserData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("bins/15baeq")
    Call<UserData> getUserData();
}
