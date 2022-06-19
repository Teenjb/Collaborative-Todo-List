package com.collaborative.todoapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.collaborative.todoapp.model.GroupList;
import com.collaborative.todoapp.model.List;
import com.collaborative.todoapp.request.BaseApiService;
import com.collaborative.todoapp.request.UtilsApi;

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
 * This class is extending BaseAdapter to create a custom adapter for List
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    BaseApiService mApiService;
    ArrayList<List> lists;

    /**
     * @param context the context to set
     * @param lists the lists to set
     */
    public ListAdapter(Context context, ArrayList<List> lists) {
        this.context = context;
        this.lists = lists;
        mApiService = UtilsApi.getAPIService();
    }

    /**
     * method to return the count of lists
     * @return the count of lists
     */
    @Override
    public int getCount() {
        return lists.size();
    }

    /**
     * method to return the list at the specified position
     * @param i
     * @return the list at the specified position
     */
    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    /**
     * method to return the list id at the specified position
     * @param i
     * @return the list id at the specified position
     */
    @Override
    public long getItemId(int i) {
        return lists.get(i).getListid();
    }

    /**
     * method to view the list and connect to the layout
     * @param i
     * @param view
     * @param viewGroup
     * @return the view of the list at the specified position
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_layout, null);

        }

        TextView titleList  = (TextView)v.findViewById(R.id.titleList);
        TextView date = (TextView)v.findViewById(R.id.date);
        CheckBox checkBox = (CheckBox)v.findViewById(R.id.checkBox);

        titleList.setText(lists.get(i).getTitle());
        date.setText(lists.get(i).getDate());
        checkBox.setChecked(lists.get(i).isChecked());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isChecked = checkBox.isChecked();
                updateCheckedRequest(i, isChecked);
            }
        });

        return v;
    }

    /**
     * method to update the checkbox of the list at the specified position
     * @param id
     * @param checked
     */
    void updateCheckedRequest(Integer id, Boolean checked) {
        mApiService.updateCheckedRequest(lists.get(id).getListid().toString(), checked.toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("message").equals("list " + lists.get(id).getListid() +  " updated")) {
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(context, "err", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, "Gagal check", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}
