package com.example.madhav.starter.login_signup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        TextView forgotP;


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_loginS);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Log in");

        //underline forgot password
        forgotP = findViewById(R.id.forgotP);
        forgotP.setPaintFlags(forgotP.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(LoginScreen.this, RegLogActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        forgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, ForgotPassword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(LoginScreen.this, RegLogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
