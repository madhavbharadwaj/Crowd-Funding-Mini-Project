package com.example.madhav.starter.login_signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity {

    TextView email_ep;
    TextView usn_ep;
    TextView username_ep;
    TextView mobile_ep;
    Button btn_editP;
   // TextView ep_status;
    //ProgressBar ep_progB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        email_ep = findViewById(R.id.email_ep);
        usn_ep = findViewById(R.id.usn_ep);
        username_ep = findViewById(R.id.username_ep);
        mobile_ep = findViewById(R.id.mobile_ep);
        btn_editP = findViewById(R.id.btn_editP);
      // ep_status = findViewById(R.id.ep_status);
        //ep_progB = findViewById(R.id.ep_progB);

        getData();


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_edit);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Edit Profile");


        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(edit_profile.this, profile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        btn_editP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfile();

            }
        });
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data ...");
        progressDialog.show();


        SharedPreferences prefs = this.getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);

        //Log.d("email : ",restoredText);

        String name="";
        if (restoredText != null) {
            name = prefs.getString("email", "No name defined");//"No name defined" is the default value.
            //Log.d("EMAIL MACHA",name);

            email_ep.setText(name);

        }
        email_ep.setEnabled(false);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

                Request.Method.GET,
                mAPI.EDIT_URL + name ,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();


                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("Student_details");
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);

                                String a = checkKey(student, "usn");
                                String x = checkKey(student, "username");
                                String y = checkNumber(student, "mobile");


                                usn_ep.setText(a);
                                usn_ep.setEnabled(false);
                                username_ep.setText(x);
                                mobile_ep.setText(y);

                            }
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

        // Add JsonObjectRequest to the RequestQueue
        // requestQueue.add(jsonObjectRequest);
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public String checkKey(JSONObject student, String s) throws JSONException {
        String res="";
        if(student.has(s))
        {
            res= student.getString(s);
        }
        else
            res = "";

        return res ;
    }

    public String checkNumber(JSONObject student, String s) throws JSONException {
        String res="";

        if(student.has(s))
        {
            res= student.getString(s);
            if(res == "null")
                res = String.valueOf(91);



        }
        else
        {
            res = String.valueOf(91);

        }


        return res ;
    }
    private void editProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating ...");
        progressDialog.show();
        String edit_email = email_ep.getText().toString();
        //final mSignup msign = new mSignup(ins.getText().toString(),ies.getText().toString(),ips.getText().toString());
        //Log.d("Tset",y);
        StringRequest postRequest = new StringRequest(Request.Method.PUT, mAPI.EDIT_URL + edit_email ,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // response
                       // Log.d("Response", response);
                        Toast.makeText(edit_profile.this, "Profile updated",
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(edit_profile.this, profile.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        progressDialog.dismiss();
                       // Log.d("Error.Response", String.valueOf(error));
                        Toast.makeText(edit_profile.this, String.valueOf(error),
                                Toast.LENGTH_LONG).show();

                    }
                }

        )

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username_ep.getText().toString());
                params.put("mobile", mobile_ep.getText().toString());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(edit_profile.this, profile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
