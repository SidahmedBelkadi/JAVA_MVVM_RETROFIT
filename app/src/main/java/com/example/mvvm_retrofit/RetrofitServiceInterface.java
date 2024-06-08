package com.example.mvvm_retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RetrofitServiceInterface {

    String token = "Bearer 50ed0aabb10caae59a896b392f0d38e663bdf25f139e42de96ed62d42f563b9b";

    @POST("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: " + token})
    Call<UserModel> createUser(@Body UserModel user);


}
