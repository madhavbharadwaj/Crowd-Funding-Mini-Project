package com.example.madhav.starter.login_signup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;

public class RegLogActivity extends AppCompatActivity {

    public static Button b1 ;
    public static Button b2 ;
    public static Button b3 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
         b1 = findViewById(R.id.button_login);
         b2 = findViewById(R.id.button_signup);




        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Log in or sign up");




        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
           // Intent intent = new Intent(RegLogActivity.this, Dashboard.class);

            // b1.setVisibility(View.GONE);
            //startActivity(intent);
            //finish();
            //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

            //View headerView = navigationView.getHeaderView(0);
           // final TextView Name_nav = (TextView) headerView.findViewById(R.id.nlogintext);
            //Name_nav.setText("maddy");
            Toast.makeText(RegLogActivity.this, "Already Logged in",
                    Toast.LENGTH_LONG).show();
            b1.setVisibility(View.GONE);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RegLogActivity.this, LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegLogActivity.this, SignUp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  onBackPressed();
                Intent intent = new Intent(RegLogActivity.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });







    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegLogActivity.this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
