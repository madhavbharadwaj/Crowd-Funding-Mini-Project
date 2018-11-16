package com.example.madhav.starter.model;

public class mSignup {


    private String mSEmail;
    private String mSUSN;
    private String mSMobile;
    private String mSGithub;
    private String mSUsername;

    public mSignup(String mSEmail, String mSUSN, String mSMobile, String mSGithub, String mSUsername) {
        this.mSEmail = mSEmail;
        this.mSUSN = mSUSN;
        this.mSMobile = mSMobile;
        this.mSGithub = mSGithub;
        this.mSUsername = mSUsername;
    }



    public String getmSEmail() {
        return mSEmail;
    }

    public String getmSUSN() {
        return mSUSN;
    }

    public String getmSMobile() {
        return mSMobile;
    }

    public String getmSGithub() {
        return mSGithub;
    }

    public String getmSUsername() {
        return mSUsername;
    }


}
