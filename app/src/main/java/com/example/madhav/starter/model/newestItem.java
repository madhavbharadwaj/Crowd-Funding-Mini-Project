package com.example.madhav.starter.model;

public class newestItem {

    private String head_new;
    private String desc_new;

    private String email_new;
    private String domain_new;
    private String category_new;
    private String tou_new;
    private String pro_git_link;

    public newestItem(String head_new, String desc_new, String email_new, String domain_new, String category_new, String tou_new, String pro_git_link) {
        this.head_new = head_new;
        this.desc_new = desc_new;
        this.email_new = email_new;
        this.domain_new = domain_new;
        this.category_new = category_new;
        this.tou_new = tou_new;
        this.pro_git_link = pro_git_link;
    }

    public String getHead_new() {
        return head_new;
    }

    public String getDesc_new() {
        return desc_new;
    }

    public String getEmail_new() {
        return email_new;
    }

    public String getDomain_new() {
        return domain_new;
    }

    public String getCategory_new() {
        return category_new;
    }

    public String getTou_new() {
        return tou_new;
    }

    public String getPro_git_link() {
        return pro_git_link;
    }
}
