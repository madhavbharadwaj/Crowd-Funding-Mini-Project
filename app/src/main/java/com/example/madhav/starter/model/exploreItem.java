package com.example.madhav.starter.model;

public class exploreItem {

    private String head_exp;
    private String desc_exp;

    private String email_exp;
    private String domain_exp;
    private String category_exp;
    private String tou_exp;
    private String pro_git_link_exp;

    public exploreItem(String head_exp, String desc_exp, String email_exp, String domain_exp, String category_exp, String tou_exp, String pro_git_link_exp) {
        this.head_exp = head_exp;
        this.desc_exp = desc_exp;
        this.email_exp = email_exp;
        this.domain_exp = domain_exp;
        this.category_exp = category_exp;
        this.tou_exp = tou_exp;
        this.pro_git_link_exp = pro_git_link_exp;
    }


    public String getHead_exp() {
        return head_exp;
    }

    public String getDesc_exp() {
        return desc_exp;
    }

    public String getEmail_exp() {
        return email_exp;
    }

    public String getDomain_exp() {
        return domain_exp;
    }

    public String getCategory_exp() {
        return category_exp;
    }

    public String getTou_exp() {
        return tou_exp;
    }

    public String getPro_git_link_exp() {
        return pro_git_link_exp;
    }
}
