package com.baitap02.login.model;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cate_id;
    private String cate_name;
    private String icons;
    private int userid; // Thêm trường userid

    
    public int getCate_id() { return cate_id; }
    public void setCate_id(int cate_id) { this.cate_id = cate_id; }
    public String getCate_name() { return cate_name; }
    public void setCate_name(String cate_name) { this.cate_name = cate_name; }
    public String getIcons() { return icons; }
    public void setIcons(String icons) { this.icons = icons; }
    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }
}