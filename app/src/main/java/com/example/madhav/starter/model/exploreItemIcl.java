package com.example.madhav.starter.model;

public class exploreItemIcl {

    private String head_exp_icl;
    private String desc_exp_icl;

    private String email_exp_icl;
    private String domain_exp_icl;
    private String category_exp_icl;
    private String tou_exp_icl;
    private String pro_git_link_exp_icl;


    public exploreItemIcl(String head_exp_icl, String desc_exp_icl, String email_exp_icl, String domain_exp_icl, String category_exp_icl, String tou_exp_icl, String pro_git_link_exp_icl) {
        this.head_exp_icl = head_exp_icl;
        this.desc_exp_icl = desc_exp_icl;
        this.email_exp_icl = email_exp_icl;
        this.domain_exp_icl = domain_exp_icl;
        this.category_exp_icl = category_exp_icl;
        this.tou_exp_icl = tou_exp_icl;
        this.pro_git_link_exp_icl = pro_git_link_exp_icl;
    }

    public String getHead_exp_icl() {
        return head_exp_icl;
    }

    public String getDesc_exp_icl() {
        return desc_exp_icl;
    }

    public String getEmail_exp_icl() {
        return email_exp_icl;
    }

    public String getDomain_exp_icl() {
        return domain_exp_icl;
    }

    public String getCategory_exp_icl() {
        return category_exp_icl;
    }

    public String getTou_exp_icl() {
        return tou_exp_icl;
    }

    public String getPro_git_link_exp_icl() {
        return pro_git_link_exp_icl;
    }
}
