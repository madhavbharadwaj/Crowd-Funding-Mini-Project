package com.example.madhav.starter.model;

public class exploreItemMca {

    private String head_exp_mca;
    private String desc_exp_mca;

    private String email_exp_mca;
    private String domain_exp_mca;
    private String category_exp_mca;
    private String tou_exp_mca;
    private String pro_git_link_exp_mca;


    public exploreItemMca(String head_exp_mca, String desc_exp_mca, String email_exp_mca, String domain_exp_mca, String category_exp_mca, String tou_exp_mca, String pro_git_link_exp_mca) {
        this.head_exp_mca = head_exp_mca;
        this.desc_exp_mca = desc_exp_mca;
        this.email_exp_mca = email_exp_mca;
        this.domain_exp_mca = domain_exp_mca;
        this.category_exp_mca = category_exp_mca;
        this.tou_exp_mca = tou_exp_mca;
        this.pro_git_link_exp_mca = pro_git_link_exp_mca;
    }

    public String getHead_exp_mca() {
        return head_exp_mca;
    }

    public String getDesc_exp_mca() {
        return desc_exp_mca;
    }

    public String getEmail_exp_mca() {
        return email_exp_mca;
    }

    public String getDomain_exp_mca() {
        return domain_exp_mca;
    }

    public String getCategory_exp_mca() {
        return category_exp_mca;
    }

    public String getTou_exp_mca() {
        return tou_exp_mca;
    }

    public String getPro_git_link_exp_mca() {
        return pro_git_link_exp_mca;
    }
}
