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
import android.widget.ImageView;
import android.widget.ListView;
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

/**
 * This class is extending AppCompatActivity to create activity for Home
 */
public class Home extends AppCompatActivity {

    private ArrayList<String> items;
    private GroupListAdapter itemsAdapter;
    private ArrayList<GroupList> groupList = new ArrayList<>();
    private ListView lvItems;
    private Button addGroup,joinGroup;
    private ImageView profile;
    private EditText groupName,joinGroupId;
    public static GroupList clickedGroup;
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
        lvItems = (ListView) findViewById(R.id.lvItems);
        joinGroupId = (EditText) findViewById(R.id.joinGroupId);
        joinGroup = (Button) findViewById(R.id.joinGroup);

        getListRequest();
        setupListViewListener();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, Profile.class));
            }
        });
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroupRequest();
            }
        });
        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGroupRequest();
            }
        });
    }

    /**
     * method to setup the list view listener
     */
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        clickedGroup = (GroupList) lvItems.getItemAtPosition(pos);
                        //groupList.remove(pos);
                        System.out.println(clickedGroup.getGrouplistid());
                        Toast.makeText(mContext, "longclicked", Toast.LENGTH_SHORT).show();
                        deleteGroupRequest(clickedGroup.getGrouplistid());
                        groupList.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                });
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {
                        // Remove the item within array at position
                        clickedGroup = (GroupList) lvItems.getItemAtPosition(pos);

                        Intent intent = new Intent(mContext, ListPage.class);

                        intent.putExtra("groupName", clickedGroup.getGroupname());
                        intent.putExtra("groupId", clickedGroup.getGrouplistid().toString());
                        //System.out.println(clickedGroup.getGrouplistid());
                        startActivity(intent);
                        // Return true consumes the long click event (marks it handled)
                    }
                });
    }

    /**
     * method to get the list of groups from request api
     */
    void getListRequest(){
        Gson gson = new Gson();
        mApiService.grouplistRequest()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray jsonArray = jsonRESULTS.getJSONArray("grouplist");
                                if (jsonRESULTS.getString("message").equals("grouplist found")){
                                    groupList = gson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<GroupList>>() {}.getType());
                                    itemsAdapter = new GroupListAdapter(getApplicationContext(), groupList);
                                    lvItems.setAdapter(itemsAdapter);
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
     * method to add group to request api
     */
    void addGroupRequest() {
        if(groupName.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Group Name cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            mApiService.addGroupRequest(groupName.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("message").equals("grouplist created")) {
                                        Toast.makeText(mContext, "Group List Created", Toast.LENGTH_SHORT).show();
                                        Integer grouplistid = jsonRESULTS.getInt("grouplistid");
                                        Integer userid = jsonRESULTS.getInt("userid");
                                        GroupList temp = new GroupList(grouplistid, groupName.getText().toString(), userid);
                                        groupList.add(temp);
                                        itemsAdapter.notifyDataSetChanged();
                                        groupName.setText("");
                                    } else {
                                        Toast.makeText(mContext, "err", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
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

    /**
     * method to delete grouplist to request api
     * @param id
     */
    void deleteGroupRequest(Integer id) {
        mApiService.deleteGroupRequest(id.toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("grouplist "+id.toString()+" deleted")) {
                                    Toast.makeText(mContext, "Group List Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, "err", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(mContext, "Gagal menghapus", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }

    /**
     * method to join grouplist to request api
     */
    void joinGroupRequest() {
        if (joinGroupId.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Group Id cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            mApiService.joinGroupRequest(joinGroupId.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("message").equals("grouplist " + joinGroupId.getText().toString() + " joined")) {
                                        Toast.makeText(mContext, "Join Successful", Toast.LENGTH_SHORT).show();
                                        getListRequest();
                                        joinGroupId.setText("");
                                    } else {
                                        Toast.makeText(mContext, "err", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(mContext, "Join Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                        }
                    });
        }
    }
}