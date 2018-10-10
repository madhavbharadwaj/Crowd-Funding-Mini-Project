package com.example.madhav.starter.login_signup;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;

public class RegLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);




        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
      //  toolbar.setTitle("");
        getSupportActionBar().setTitle("Log in or sign up");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  onBackPressed();

                Intent intent = new Intent(RegLogActivity.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegLogActivity.this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
