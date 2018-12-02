package com.example.madhav.starter.model;


/*
File for  my_projects.java - project_status -> Pending
 */
public class pendingItem {

    private String head_pending;
    private String desc_pending;

    private String email_pending;
    private String domain_pending;
    private String category_pending;
    private String tou_pending;
    private String pro_git_link_pending;

    public pendingItem(String head_pending, String desc_pending, String email_pending, String domain_pending, String category_pending, String tou_pending, String pro_git_link_pending) {
        this.head_pending = head_pending;
        this.desc_pending = desc_pending;
        this.email_pending = email_pending;
        this.domain_pending = domain_pending;
        this.category_pending = category_pending;
        this.tou_pending = tou_pending;
        this.pro_git_link_pending = pro_git_link_pending;
    }

    public String getHead_pending() {
        return head_pending;
    }

    public String getDesc_pending() {
        return desc_pending;
    }

    public String getEmail_pending() {
        return email_pending;
    }

    public String getDomain_pending() {
        return domain_pending;
    }

    public String getCategory_pending() {
        return category_pending;
    }

    public String getTou_pending() {
        return tou_pending;
    }

    public String getPro_git_link_pending() {
        return pro_git_link_pending;
    }
}
