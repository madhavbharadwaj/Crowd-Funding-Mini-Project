package com.example.madhav.starter.network;

public class mAPI {

    //get all users(to hit api on splash screen)
    public static String USER_URL  = "https://test-api-man.herokuapp.com/student/";

    //login api
    public static String LOGIN_URL  = "https://test-api-man.herokuapp.com/student/login/";

    //get email api
    public static String EDIT_URL  = "https://test-api-man.herokuapp.com/student/email/";

    //project upload api
    public static String UPLOAD_URL = "https://test-api-man.herokuapp.com/upload/project/email/";

    //signup api
    public static String SIGNUP_URL  = "https://test-api-man.herokuapp.com/student/signup/";

    //forgot pw api
    public static String FORGOT_URL = "https://test-api-man.herokuapp.com/student/forgot";

    //logout api (token destruction)
    public static String LOGOUT_URL =  "https://test-api-man.herokuapp.com/student/logout/";


    //pending projects api (upcoming tab)
    public static String PENDING_URL = "https://test-api-man.herokuapp.com/upload/pending";
}
