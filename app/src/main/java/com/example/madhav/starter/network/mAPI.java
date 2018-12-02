package com.example.madhav.starter.network;

public class mAPI {

    //get all users(to hit api on splash screen)
    public static String USER_URL  = "https://crowd-src.herokuapp.com/student/";

    //login api
    public static String LOGIN_URL  = "https://crowd-src.herokuapp.com/student/login/";

    //get email api
    public static String EDIT_URL  = "https://crowd-src.herokuapp.com/student/email/";

    //project upload api
    public static String UPLOAD_URL = "https://crowd-src.herokuapp.com/upload/project/email/";

    //signup api
    public static String SIGNUP_URL  = "https://crowd-src.herokuapp.com/student/signup/";

    //forgot pw api
    public static String FORGOT_URL = "https://crowd-src.herokuapp.com/student/forgot";

    //logout api (token destruction)
    public static String LOGOUT_URL =  "https://crowd-src.herokuapp.com/student/logout/";

    //pending projects api (upcoming tab)
    public static String PENDING_URL = "https://crowd-src.herokuapp.com/upload/pending";

    //approved projects api (newest tab)
    public static String NEWEST_URL = "https://crowd-src.herokuapp.com/upload/newapproved";

    //approved projects api (explore tab)
    public static String APPROVED_EXP_URL = "https://crowd-src.herokuapp.com/upload/approved";

    // profile (my projects) approved count
    public static String APP_COUNT_USER = "https://crowd-src.herokuapp.com/upload/appstatus/";

    // profile (my projects) pending count
    public static String PEND_COUNT_USER = "https://crowd-src.herokuapp.com/upload/penstatus/";
}
