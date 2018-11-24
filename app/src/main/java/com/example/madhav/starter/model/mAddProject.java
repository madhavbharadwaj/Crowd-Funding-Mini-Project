package com.example.madhav.starter.model;

public class mAddProject {

    private String pu_title;
    //github project link
    private String pu_git_proj_link;
    private String pu_description;
    private String pu_domain;
    private String pu_category;

    public mAddProject(String pu_title, String pu_git_proj_link, String pu_description, String pu_domain, String pu_category) {
        this.pu_title = pu_title;
        this.pu_git_proj_link = pu_git_proj_link;
        this.pu_description = pu_description;
        this.pu_domain = pu_domain;
        this.pu_category = pu_category;
    }

    public String getPu_title() {
        return pu_title;
    }

    public String getPu_git_proj_link() {
        return pu_git_proj_link;
    }

    public String getPu_description() {
        return pu_description;
    }

    public String getPu_domain() {
        return pu_domain;
    }

    public String getPu_category() {
        return pu_category;
    }
}
