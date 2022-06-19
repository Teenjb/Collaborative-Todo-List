package com.collaborative.todoapp.request;

import java.sql.Struct;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * This class is used to serve a base api service
 */
public interface BaseApiService {

    /**
     * This method is used to login
     * @param username
     * @param password
     * @return message
     */
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    /**
     * This method is used to register
     * @param username
     * @param password
     * @return message
     */
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password);

    /**
     * This method is used to get all grouplists
     * @return grouplists
     */
    @GET("get-grouplist")
    Call<ResponseBody> grouplistRequest();

    /**
     * This method is used to get all user
     * @return users
     */
    @GET("default")
    Call<ResponseBody> getUser();

    /**
     * This method is used to logout
     * @return message
     */
    @POST("logout")
    Call<ResponseBody> logoutRequest();

    /**
     * this method is used to post a new grouplist
     * @param name
     * @return message
     */
    @FormUrlEncoded
    @POST("create-grouplist")
    Call<ResponseBody> addGroupRequest(@Field("name") String name);

    /**
     * this method is used to delete a grouplist
     * @param id
     * @return message
     */
    @DELETE("delete-grouplist")
    Call<ResponseBody> deleteGroupRequest(@Query("grouplistid") String id);

    /**
     * this method is used to join a grouplist
     * @param id
     * @return message
     */
    @FormUrlEncoded
    @POST("join-grouplist")
    Call<ResponseBody> joinGroupRequest(@Field("grouplistid") String id);

    /**
     * this method is used to get a list
     * @param id
     * @return message
     */
    @GET("get-list")
    Call<ResponseBody> getlistRequest(@Query("grouplistid") String id);

    /**
     * this method is used to post a new list
     * @param id
     * @param title
     * @param due
     * @param ischecked
     * @return message
     */
    @FormUrlEncoded
    @POST("create-list")
    Call<ResponseBody> addListRequest(@Field("grouplistid") String id,
                                       @Field("title") String title,
                                       @Field("due") String due,
                                       @Field("ischecked") String ischecked);

    /**
     * this method is used to update checked status of a list
     * @param id
     * @param ischecked
     * @return message
     */
    @PUT("update-check")
    Call<ResponseBody> updateCheckedRequest(@Query("listid") String id,
                                          @Query("ischecked") String ischecked);

    /**
     * this method is used to delete a list
     * @param id
     * @return message
     */
    @PUT("update-password")
    Call<ResponseBody> updatePasswordRequest(@Query("password") String id);
}
