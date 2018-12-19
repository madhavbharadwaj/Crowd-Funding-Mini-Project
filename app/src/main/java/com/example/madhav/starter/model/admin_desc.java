package com.example.madhav.starter.model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.login_signup.LoginScreen;
import com.example.madhav.starter.login_signup.admin_page;
import com.example.madhav.starter.login_signup.edit_profile;
import com.example.madhav.starter.login_signup.profile;
import com.example.madhav.starter.network.mAPI;

import java.util.HashMap;
import java.util.Map;

public class admin_desc extends AppCompatActivity {

    TextView proj_id_admin;
    TextView proj_title_admin;
    TextView proj_desc_admin;
    TextView proj_email_admin;
    TextView proj_domain_admin;
    TextView proj_category_admin;
    TextView proj_uploadTime_admin;
    TextView proj_git_link_admin;
    Button button_approve;
    Button button_reject;

    String id_param,email_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_desc);



        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_admin_desc);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Pending Projects");

        proj_id_admin = findViewById(R.id.proj_id_admin);
        proj_title_admin =findViewById(R.id.proj_title_admin);
        proj_desc_admin= findViewById(R.id.proj_desc_admin);
        proj_email_admin = findViewById(R.id.proj_email_admin);
        proj_domain_admin = findViewById(R.id.proj_domain_admin);
        proj_category_admin = findViewById(R.id.proj_category_admin);
        proj_uploadTime_admin = findViewById(R.id.proj_uploadTime_admin);
        proj_git_link_admin = findViewById(R.id.proj_git_link_admin);

        button_approve = findViewById(R.id.button_approve);
        button_reject = findViewById(R.id.button_reject);

        proj_id_admin.setText("ID : "+ getIntent().getStringExtra("id"));
        proj_id_admin.setVisibility(View.GONE);
        proj_title_admin.setText("Title : "+ getIntent().getStringExtra("header"));
        proj_desc_admin.setText("Description : "+getIntent().getStringExtra("description"));
        proj_email_admin.setText("Uploaded by : "+getIntent().getStringExtra("email"));
        proj_domain_admin.setText("Domain : "+getIntent().getStringExtra("domain"));
        proj_category_admin.setText("Department : "+getIntent().getStringExtra("category"));
        proj_uploadTime_admin.setText("Date/Time : "+getIntent().getStringExtra("tou"));
        proj_git_link_admin.setText("Github link : "+getIntent().getStringExtra("pgl"));


       id_param = getIntent().getStringExtra("id");
        email_body =getIntent().getStringExtra("email");
        button_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(), id_param,
                        Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), email_body,
                        Toast.LENGTH_LONG).show();*/
                approve();
                Intent intent = new Intent(admin_desc.this, admin_page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        button_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reject();

            }
        });

        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
    }

    private void approve() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Approving ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
     //   String edit_email = email_ep.getText().toString();
        //final mSignup msign = new mSignup(ins.getText().toString(),ies.getText().toString(),ips.getText().toString());
        //Log.d("Tset",y);
        StringRequest postRequest = new StringRequest(Request.Method.PUT, mAPI.APPROVE_URL + id_param ,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // response
                        // Log.d("Response", response);
                        Toast.makeText(admin_desc.this, "Project Approved",
                                Toast.LENGTH_LONG).show();



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        progressDialog.dismiss();
                        // Log.d("Error.Response", String.valueOf(error));
                        Toast.makeText(getApplicationContext(), String.valueOf(error),
                                Toast.LENGTH_LONG).show();

                    }
                }

        )

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email_body);
                params.put("status", "approve");
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }

    private void reject() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Rejecting ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.DELETE, mAPI.REJECT_URL + id_param,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // response
                        // Log.d("Response", response);
                        Toast.makeText(admin_desc.this, "Project Declined",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(admin_desc.this, admin_page.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        progressDialog.dismiss();
                        // Log.d("Error.Response", String.valueOf(error));
                        Toast.makeText(admin_desc.this, String.valueOf(error),
                                Toast.LENGTH_LONG).show();

                    }
                }

        );



        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
}
