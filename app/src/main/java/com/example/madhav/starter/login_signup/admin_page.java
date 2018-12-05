package com.example.madhav.starter.login_signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.model.adapter_admin;
import com.example.madhav.starter.model.adapter_pending;
import com.example.madhav.starter.model.adminItem;
import com.example.madhav.starter.model.pendingItem;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class admin_page extends AppCompatActivity {

    private RecyclerView recyclerView_admin;
    private RecyclerView.Adapter adapter_admin;
    private List<adminItem> adminItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_admin_page);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Admin Panel");

        recyclerView_admin = (RecyclerView) findViewById(R.id.recyclerView_admin);
        recyclerView_admin.setHasFixedSize(true);
        recyclerView_admin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //app_count = v.findViewById(R.id.app_count);

        adminItems = new ArrayList<>();

        loadRecyclerViewData_admin();




        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(admin_page.this, profile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
    }
    private void loadRecyclerViewData_admin()
    {
       final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /*SharedPreferences prefs = getActivity().getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);
        Log.d("email",restoredText);*/
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.GET_ALL_PEND_PROJ,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                            for(int i=0;i<array.length();i++){

                                JSONObject student = array.getJSONObject(i);
                                // JSONObject jo = student.getJSONObject("Student_details");

                                adminItem ui = new adminItem(
                                        //array1.getString("email"),
                                        student.getString("title"),
                                        student.getString("_id"),
                                        student.getString("description"),
                                        student.getString("email"),
                                        student.getString("domain"),
                                        student.getString("category"),
                                        student.getString("upload_time"),
                                        student.getString("git_proj_link")
                                );
                                adminItems.add(ui);
                            }
                            adapter_admin = new adapter_admin(adminItems,getApplicationContext());
                            //app_count.setText("Approved Projects : "+array.length());
                            recyclerView_admin.setAdapter(adapter_admin);



                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
