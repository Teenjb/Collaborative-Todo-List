package com.collaborative.todoapp.request;

/**
 * this class is used to serve a base api service
 */
public class UtilsApi {
    public static final String BASE_URL_API = "http://10.0.2.2:3000/todo/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
