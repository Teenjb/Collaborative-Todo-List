package com.collaborative.todoapp.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password);

    //@FormUrlEncoded
    @GET("get-grouplist")
    Call<ResponseBody> grouplistRequest();

    @GET("")
    Call<ResponseBody> getUser();
}
