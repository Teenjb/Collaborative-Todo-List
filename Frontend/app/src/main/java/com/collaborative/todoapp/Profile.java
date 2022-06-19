package com.collaborative.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.collaborative.todoapp.model.User;
import com.collaborative.todoapp.request.BaseApiService;
import com.collaborative.todoapp.request.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is extending AppCompatActivity to create a custom activity for Profile
 */
public class Profile extends AppCompatActivity {
    EditText username;
    EditText newPassword;
    Button logoutButton,saveButton;
    Context mContext;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        username = (EditText) findViewById(R.id.username);
        newPassword = (EditText) findViewById(R.id.newPassword);
        logoutButton = (Button) findViewById(R.id.logoutBtn);
        saveButton = (Button) findViewById(R.id.savePassword);
        getUser(username);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
                requestLogout();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPassword.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "Please enter new password", Toast.LENGTH_SHORT).show();
                }else {
                    saveRequest();
                }
            }
        });
    }

    /**
     * This method is used to request logout
     */
    void requestLogout(){
        mApiService.logoutRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            //loading.dismiss();
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("logging you out")){
                                    Toast.makeText(mContext, "LOGOUT", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "GAGAL LOGOUT", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }

    /**
     * This method is used to request user data
     * @param username is used to get the username from the database
     */
    void getUser(EditText username) {
        mApiService.getUser()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            //loading.dismiss();
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.equals("Not Logged in")) {
                                    Toast.makeText(mContext, "not logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    User user = new User();
                                    Gson gson = new Gson();
                                    user = gson.fromJson(jsonRESULTS.getString("user"), User.class);
                                    Toast.makeText(mContext, user.getUsername(), Toast.LENGTH_SHORT).show();
                                    username.setText(user.getUsername());
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "response error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }

    /**
     * This method is used to request save password
     */
    void saveRequest(){
        mApiService.updatePasswordRequest(newPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            //loading.dismiss();
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("password updated")){
                                    Toast.makeText(mContext, "updated", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, "err", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "GAGAL LOGOUT", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}