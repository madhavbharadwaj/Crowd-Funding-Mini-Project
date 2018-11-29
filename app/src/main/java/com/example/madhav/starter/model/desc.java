package com.example.madhav.starter.model;


import android.content.Intent;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.LoginScreen;
import com.example.madhav.starter.login_signup.RegLogActivity;


public class desc extends AppCompatActivity {


    public desc() {
        // Required empty public constructor
    }

    TextView proj_title;
    TextView proj_desc;
    TextView proj_email;
    TextView proj_domain;
    TextView proj_category;
    TextView proj_uploadTime;
    TextView proj_git_link;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_desc);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Project Description");

        proj_title = findViewById(R.id.proj_title);
        proj_desc =findViewById(R.id.proj_desc);
        proj_email= findViewById(R.id.proj_email);
        proj_domain = findViewById(R.id.proj_domain);
        proj_category = findViewById(R.id.proj_category);
        proj_uploadTime = findViewById(R.id.proj_uploadTime);
        proj_git_link = findViewById(R.id.proj_git_link);


        proj_title.setText("Title : "+ getIntent().getStringExtra("header"));
        proj_desc.setText("Description : "+getIntent().getStringExtra("description"));
        proj_email.setText("Uploaded by : "+getIntent().getStringExtra("email"));
        proj_domain.setText("Domain : "+getIntent().getStringExtra("domain"));
        proj_category.setText("Category : "+getIntent().getStringExtra("category"));
        proj_uploadTime.setText("Date/Time : "+getIntent().getStringExtra("tou"));
        proj_git_link.setText("Github link : "+getIntent().getStringExtra("pgl"));





        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();

            }
        });

    }
}
