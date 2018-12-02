package com.example.madhav.starter.login_signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.w3c.dom.Text;


public class profile extends AppCompatActivity {


    TextView prof_appr_count;
    TextView prof_pend_count;

    TextView editBT;

    CardView my_prof_card;


    public static TextView b3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        prof_appr_count = findViewById(R.id.prof_appr_count);
        prof_pend_count = findViewById(R.id.prof_pend_count);
        my_prof_card = findViewById(R.id.my_prof_card);
        editBT = findViewById(R.id.editBT);
        b3 = findViewById(R.id.button_logout);


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_pro);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Profile");
        app_user_count();
        pend_user_count();

        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(profile.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        my_prof_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, my_projects.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }



    public void logout()
    {


        SaveSharedPreference.setLoggedIn(getApplicationContext(), false);
        Toast.makeText(getApplicationContext(), "You have been successfully logged out!",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        /*SharedPreferences preferences = getSharedPreferences("usn_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();*/


        LogOutToken();
        startActivity(intent);

        //Log.d("Token man ",y);
        finish();

    }

    private void LogOutToken() {
        SharedPreferences prefs = this.getSharedPreferences("token_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("tokky", null);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mAPI.LOGOUT_URL+restoredText,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());
                        Log.d("Success.Response", String.valueOf(response));
                        // Process the JSON


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

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(profile.this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    private void app_user_count()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        SharedPreferences prefs = this.getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);

        //Log.d("email",restoredText);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.APP_COUNT_USER + restoredText,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                            //Log.d("explore count", String.valueOf(array.length()));

                            String pac = String.valueOf(array.length());
                            //send(array.length());
                            prof_appr_count.setText(pac);

                            // yvalues.add(new Entry(new_float, 1));
                            // yvalues.add(new Entry(pend_float, 2));

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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    private void pend_user_count()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        SharedPreferences prefs = this.getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);

       // Log.d("email",restoredText);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                mAPI.PEND_COUNT_USER + restoredText,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("COMPLETE_DETAILS");
                            //Log.d("explore count", String.valueOf(array.length()));

                            String ppc = String.valueOf(array.length());
                            //send(array.length());
                            prof_pend_count.setText(ppc);

                            // yvalues.add(new Entry(new_float, 1));
                            // yvalues.add(new Entry(pend_float, 2));

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
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
