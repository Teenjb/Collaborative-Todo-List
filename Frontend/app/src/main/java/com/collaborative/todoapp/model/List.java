package com.collaborative.todoapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This model is used to represent a list record from backend
 * @author Fateen
 * @version 1.0
 */
public class List {
    private Integer listid;
    private int grouplistid;
    private String title;
    private Date due;
    private Boolean ischecked;
    public static final SimpleDateFormat dateFormatOutput = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @param listid the listid to set
     * @param grouplistid the grouplistid to set
     * @param title the title to set
     * @param due the due to set
     * @param isChecked the isChecked to set
     */
    public List(Integer listid, int grouplistid, String title, Date due, Boolean isChecked) {
        this.listid = listid;
        this.grouplistid = grouplistid;
        this.title = title;
        this.due = due;
        this.ischecked = isChecked;
    }

    /**
     * method to return listid
     * @return the listid
     */
    public Integer getListid() {
        return listid;
    }

    /**
     * method to return grouplistid
     * @return the grouplistid
     */
    public int getGrouplistid() {
        return grouplistid;
    }

    /**
     * method to return title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * method to return due
     * @return the due
     */
    public String getDate() {
        return dateFormatOutput.format(due);
    }

    /**
     * method to return ischecked
     * @return the ischecked
     */
    public Boolean isChecked() {
        return ischecked;
    }

    /**
     * method to set ischecked
     */
    public void setChecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
