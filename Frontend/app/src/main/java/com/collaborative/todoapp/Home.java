package com.collaborative.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.collaborative.todoapp.model.GroupList;
import com.collaborative.todoapp.request.BaseApiService;
import com.collaborative.todoapp.request.UtilsApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<GroupList> itemsAdapter;
    private ArrayList<GroupList> groupList = new ArrayList<>();
    private ListView lvItems;
    private Button addGroup;
    private ImageView profile;
    private EditText groupName;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        setContentView(R.layout.activity_home);
        addGroup = (Button) findViewById(R.id.addGroup);
        profile = (ImageView) findViewById(R.id.profile);
        groupName = (EditText) findViewById(R.id.groupName);
        getListRequest();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Profile.class));
            }
        });
    }

    void getListRequest(){
        Gson gson = new Gson();
        mApiService.grouplistRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("grouplist");
                                if (jsonRESULTS.getString("message").equals("grouplist found")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    groupList = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<GroupList>>() {}.getType());
                                    lvItems = (ListView) findViewById(R.id.lvItems);
                                    itemsAdapter = new ArrayAdapter<GroupList>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, groupList);
                                    lvItems.setAdapter(itemsAdapter);
                                    Toast.makeText(mContext,jsonArray.toString(), Toast.LENGTH_SHORT).show();
                                    //String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    // Intent intent = new Intent(mContext, Home.class);
                                    //intent.putExtra("result_nama", nama);
                                    // startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Sudah Login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}