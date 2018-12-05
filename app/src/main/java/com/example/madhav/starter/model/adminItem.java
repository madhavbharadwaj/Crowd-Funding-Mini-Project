package com.example.madhav.starter.model;

public class adminItem {

    private String head_admin;
    private String id_admin;
    private String desc_admin;
    private String email_admin;
    private String domain_admin;
    private String category_admin;
    private String tou_admin;
    private String pro_git_link_admin;


    public adminItem(String head_admin, String id_admin, String desc_admin, String email_admin, String domain_admin, String category_admin, String tou_admin, String pro_git_link_admin) {
        this.head_admin = head_admin;
        this.id_admin = id_admin;
        this.desc_admin = desc_admin;
        this.email_admin = email_admin;
        this.domain_admin = domain_admin;
        this.category_admin = category_admin;
        this.tou_admin = tou_admin;
        this.pro_git_link_admin = pro_git_link_admin;
    }

    public String getHead_admin() {
        return head_admin;
    }

    public String getId_admin() {
        return id_admin;
    }

    public String getDesc_admin() {
        return desc_admin;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public String getDomain_admin() {
        return domain_admin;
    }

    public String getCategory_admin() {
        return category_admin;
    }

    public String getTou_admin() {
        return tou_admin;
    }

    public String getPro_git_link_admin() {
        return pro_git_link_admin;
    }
}
