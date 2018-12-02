package com.example.madhav.starter.login_signup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.madhav.starter.model.mLogin;
import com.example.madhav.starter.network.mAPI;

import com.example.madhav.starter.login_signup.RegLogActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;



public class LoginScreen extends AppCompatActivity {

    EditText emailText;
    EditText pwText;
    Button lgnButton;
    TextView status;
    private ProgressBar pdia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        TextView forgotP;
      //  b1 = findViewById(R.id.button_login);
       // Button b1 = findViewById(R.id.button_login);
       // Button b2 = findViewById(R.id.button_signup);

        emailText = findViewById(R.id.input_email);
        pwText = findViewById(R.id.input_password);

        lgnButton = findViewById(R.id.btn_login);
        status = findViewById(R.id.status);
        pdia = findViewById(R.id.progB);
        pdia.setVisibility(View.GONE);
        emailText.requestFocus();
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
                finish();

            }
        });


        forgotP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, ForgotPassword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


       /* if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
           // Intent intent = new Intent(LoginScreen.this, Dashboard.class);

           // b1.setVisibility(View.GONE);
            //startActivity(intent);
           // finish();

            Toast.makeText(LoginScreen.this, "Already Logged in",
                    Toast.LENGTH_LONG).show();
            RegLogActivity.b1
                    .setVisibility(View.GONE);
        }*/

        lgnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setText("");

                if( TextUtils.isEmpty(emailText.getText()) || TextUtils.isEmpty(pwText.getText()) ) {
                    status.setText("Fields cannot be left blank");


                }
                else
                {


                    if(CheckInternet())
                    {


                          //  new LoginLongOperation().execute("");
                        login();
                    }
                    else
                    {
                        status.setTextColor(Color.RED);
                        status.setText("No Internet Connection");

                    }


                }
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(LoginScreen.this, RegLogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    private void login() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating ...");
        progressDialog.show();
        final mLogin mlog = new mLogin(emailText.getText().toString(),pwText.getText().toString());

        StringRequest postRequest = new StringRequest(Request.Method.POST, mAPI.LOGIN_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        progressDialog.dismiss();
                        Log.d("Response", response);

                        //getTip();

                        SaveSharedPreference.setLoggedIn(getApplicationContext(), true);

                        Intent intent = new Intent(LoginScreen.this, Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK |FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginScreen.this, "Login Successful",
                                Toast.LENGTH_LONG).show();




                     //   getTip();
                        SharedPreferences.Editor editor = getSharedPreferences("email_pref", MODE_PRIVATE).edit();
                        editor.putString("email", emailText.getText().toString());
                        editor.apply();

                       // GetUser();


                        GetToken();





                        emailText.setText("");
                        pwText.setText("");
                        emailText.requestFocus();
                        //Intent n = new Intent(LoginScreenActivity.this, HomeActivity.class);
                        // n.putExtra("puttip",Quotes);

                        // startActivity(n);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        progressDialog.dismiss();
                        Log.d("Error.Response", String.valueOf(error));
                        /*Toast.makeText(LoginScreenActivity.this, "Email or Password is Invalid",
                                Toast.LENGTH_LONG).show();*/
                        status.setText("Email or Password is Invalid");

                      //  pdia.setVisibility(View.GONE);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", mlog.getmEmail());

                params.put("password", mlog.getmPassword());

                return params;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }



    private void GetToken() {

        SharedPreferences prefs = this.getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mAPI.EDIT_URL + restoredText,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("Student_details");
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){

                                JSONObject student = array.getJSONObject(i);

                                String login_token = student.getString("tokky");


                                SharedPreferences.Editor editor = getSharedPreferences("token_pref", MODE_PRIVATE).edit();
                                editor.putString("tokky",login_token);
                                editor.apply();


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


    private class LoginLongOperation extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            login();

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }


            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            pdia.setVisibility(View.GONE);

        }

        @Override
        protected void onPreExecute() {

            pdia.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    public boolean CheckInternet()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        return connected;
    }


    private void GetUser() {
        SharedPreferences prefs = this.getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mAPI.EDIT_URL + restoredText,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("user_details");
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);

                                String usn_token = student.getString("username");


                                SharedPreferences.Editor editor = getSharedPreferences("usn_pref", MODE_PRIVATE).edit();
                                editor.putString("username",usn_token);
                                editor.apply();


                                Log.d("usn_token_user : ", usn_token);



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
}
