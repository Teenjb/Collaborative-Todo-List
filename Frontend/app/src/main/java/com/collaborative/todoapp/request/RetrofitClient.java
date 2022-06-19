package com.collaborative.todoapp.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class is used to save cookies from backend
 */
class SessionCookieJar implements CookieJar {

    private List<Cookie> cookies;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (url.encodedPath().endsWith("login")) {
            this.cookies = new ArrayList<>(cookies);
        }
    }
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (!url.encodedPath().endsWith("login") && cookies != null) {
            return cookies;
        }
        return Collections.emptyList();
    }
}

/**
 * this class is used to create a retrofit client
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder().cookieJar(new SessionCookieJar()).build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
