package com.collaborative.todoapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.collaborative.todoapp.model.GroupList;

import java.util.ArrayList;

/**
 * This class is extending BaseAdapter to create a custom adapter for GroupList
 */
public class GroupListAdapter extends BaseAdapter {
    Context context;
    ArrayList<GroupList> groupList;

    /**
     * @param context the context to set
     * @param groupList the groupList to set
     */
    public GroupListAdapter(Context context, ArrayList<GroupList> groupList) {

        this.context = context;
        this.groupList = groupList;
    }

    /**
     * method to return the count of groupList
     * @return the count of groupList
     */
    @Override
    public int getCount() {
        return groupList.size();
    }

    /**
     * method to return the groupList at the specified position
     * @param i
     * @return the groupList at the specified position
     */
    @Override
    public Object getItem(int i) {
        return groupList.get(i);
    }

    /**
     * method to return the groupList id at the specified position
     * @param i
     * @return the groupList id at the specified position
     */
    @Override
    public long getItemId(int i) {
        return groupList.get(i).getGrouplistid();
    }

    /**
     * method to view the groupList and connect to the layout
     * @param i
     * @param view
     * @param viewGroup
     * @return the view of the groupList at the specified position
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grouplist_layout, null);

        }

        TextView groulistName  = (TextView)v.findViewById(R.id.title);
        ImageView icon = (ImageView)v.findViewById(R.id.icon);

        groulistName.setText(groupList.get(i).toString());
        icon.setImageResource(R.drawable.vector_1);

        return v;
    }
}
