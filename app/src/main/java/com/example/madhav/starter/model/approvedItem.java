package com.example.madhav.starter.model;

public class approvedItem {
    private String head_approved;
    private String desc_approved;

    private String email_approved;
    private String domain_approved;
    private String category_approved;
    private String tou_approved;
    private String pro_git_link_approved;


    public approvedItem(String head_approved, String desc_approved, String email_approved, String domain_approved, String category_approved, String tou_approved, String pro_git_link_approved) {
        this.head_approved = head_approved;
        this.desc_approved = desc_approved;
        this.email_approved = email_approved;
        this.domain_approved = domain_approved;
        this.category_approved = category_approved;
        this.tou_approved = tou_approved;
        this.pro_git_link_approved = pro_git_link_approved;
    }


    public String getHead_approved() {
        return head_approved;
    }

    public String getDesc_approved() {
        return desc_approved;
    }

    public String getEmail_approved() {
        return email_approved;
    }

    public String getDomain_approved() {
        return domain_approved;
    }

    public String getCategory_approved() {
        return category_approved;
    }

    public String getTou_approved() {
        return tou_approved;
    }

    public String getPro_git_link_approved() {
        return pro_git_link_approved;
    }
}
