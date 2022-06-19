package com.collaborative.todoapp.model;

/**
 * This model is used to represent a group list record from backend
 * @author Fateen
 * @version 1.0
 */
public class GroupList {
    private int grouplistid;
    private String groupname;
    private int userid;

    /**
     * @param grouplistid the grouplistid to set
     * @param groupname the groupname to set
     * @param userid the userid to set
     */
    public GroupList(int grouplistid, String groupname, int userid) {
        this.grouplistid = grouplistid;
        this.groupname = groupname;
        this.userid = userid;
    }
    /**
     * method to return grouplistid
     * @return the grouplistid
     */
    public Integer getGrouplistid() {
        return grouplistid;
    }

    /**
     * method to return groupname
     * @return the groupname
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * method to return groupname as string
     * @return the groupname as string
     */
    public String toString() {
        return groupname;
    }
}
