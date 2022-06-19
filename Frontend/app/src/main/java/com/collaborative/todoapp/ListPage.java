package com.collaborative.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.collaborative.todoapp.model.GroupList;
import com.collaborative.todoapp.model.List;
import com.collaborative.todoapp.request.BaseApiService;
import com.collaborative.todoapp.request.UtilsApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is extending AppCompatActivity to create a custom activity for List
 */
public class ListPage extends AppCompatActivity {

    TextView todoName,todoId;
    private String todoNameString,todoIdString;
    ListAdapter listAdapter;
    EditText listName,listDate;
    Button addList;
    List clickedList;
    private ListView lvItems;
    private ArrayList<List> list = new ArrayList<>();
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        todoName = (TextView) findViewById(R.id.todoName);
        todoId = (TextView) findViewById(R.id.todoId);
        lvItems = (ListView) findViewById(R.id.lvList);
        listName = (EditText) findViewById(R.id.listName);
        listDate = (EditText) findViewById(R.id.listDate);
        addList = (Button) findViewById(R.id.addList);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                todoNameString = null;
                todoIdString = null;
            } else {
                todoNameString = extras.getString("groupName");
                todoIdString = extras.getString("groupId");
            }
        } else {
            todoNameString = (String) savedInstanceState.getSerializable("groupName");
            todoIdString = (String) savedInstanceState.getSerializable("groupId");
        }

        System.out.println(todoIdString);
        getListRequest();
        todoName.setText(todoNameString);
        todoId.setText("ID: " + todoIdString);
        listAdapter = new ListAdapter(getApplicationContext(), list);
        lvItems.setAdapter(listAdapter);

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listName.getText().toString().isEmpty() || listDate.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter a todo name", Toast.LENGTH_SHORT).show();
                }else{
                    addlistRequest();
                }
            }
        });
    }

    /**
     * This method is used to get list request
     */
    void getListRequest(){
        Gson gson = new Gson();
        mApiService.getlistRequest(todoIdString)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("list");
                                if (jsonRESULTS.getString("message").equals("list found")){
                                    Toast.makeText(mContext, "List Found", Toast.LENGTH_SHORT).show();
                                    list = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<List>>() {}.getType());
                                    listAdapter = new ListAdapter(getApplicationContext(), list);
                                    lvItems.setAdapter(listAdapter);
                                } else {
                                    Toast.makeText(mContext, "list kosong", Toast.LENGTH_SHORT).show();
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

    /**
     * This method is used to add list request
     */
    void addlistRequest() {
        mApiService.addListRequest(todoIdString, listName.getText().toString(), listDate.getText().toString(), "false")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("list " + listName.getText().toString() +  " created")) {
                                    Toast.makeText(mContext, "Group List Created", Toast.LENGTH_SHORT).show();
                                    Integer listId = jsonRESULTS.getInt("listid");
                                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(listDate.getText().toString());
                                    List temp = new List(listId,Integer.parseInt(todoIdString), listName.getText().toString(),date, false);
                                    list.add(temp);
                                    listAdapter.notifyDataSetChanged();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, "err", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Gagal menambahkan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}