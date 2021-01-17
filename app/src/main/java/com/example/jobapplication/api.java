package com.example.jobapplication;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api {
    @FormUrlEncoded
    @POST("/register")
    Call<ResponseBody> register(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> login(@Field("email") String email,@Field("password") String password);

    @GET("/getjobs")
    Call<List<jobModel>> getitems();

}
