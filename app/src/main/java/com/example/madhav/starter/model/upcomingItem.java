package com.example.madhav.starter.model;


/*
 File for Categories -> UpComing

 */
public class upcomingItem {
  /*  private String head;
    private String desc;

    public upcomingItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }



    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }*/

    private String head_upcg;
    private String desc_upcg;

    private String email_upcg;
    private String domain_upcg;
    private String category_upcg;
    private String tou_upcg;
    private String pro_git_link_upcg;

    public upcomingItem(String head_upcg, String desc_upcg, String email_upcg, String domain_upcg, String category_upcg, String tou_upcg, String pro_git_link_upcg) {
        this.head_upcg = head_upcg;
        this.desc_upcg = desc_upcg;
        this.email_upcg = email_upcg;
        this.domain_upcg = domain_upcg;
        this.category_upcg = category_upcg;
        this.tou_upcg = tou_upcg;
        this.pro_git_link_upcg = pro_git_link_upcg;
    }

    public String getHead_upcg() {
        return head_upcg;
    }

    public String getDesc_upcg() {
        return desc_upcg;
    }

    public String getEmail_upcg() {
        return email_upcg;
    }

    public String getDomain_upcg() {
        return domain_upcg;
    }

    public String getCategory_upcg() {
        return category_upcg;
    }

    public String getTou_upcg() {
        return tou_upcg;
    }

    public String getPro_git_link_upcg() {
        return pro_git_link_upcg;
    }
}
