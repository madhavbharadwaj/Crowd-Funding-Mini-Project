package com.example.madhav.starter.login_signup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.network.mAPI;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {

    private EditText fp_email;
    private Button fp_button;
    private TextView fp_status;
    private ProgressBar fp_pbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        fp_email = findViewById(R.id.input_email_fpw);
        fp_button = findViewById(R.id.btn_forgotpw);
        fp_status = findViewById(R.id.fp_status);
        fp_pbar = findViewById(R.id.fp_progB);
        fp_pbar.setVisibility(View.GONE);

        fp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fp_status.setText("");

                if( TextUtils.isEmpty(fp_email.getText())){
                    fp_status.setText("Email ID cannot be left blank");
                 //   fp_status.setTextColor(Color.RED);
                }
                else
                {
                    if(CheckInternet())
                    {


                       fpw();
                    }
                    else
                    {
                        fp_status.setTextColor(Color.RED);
                        fp_status.setText("No Internet Connection");

                    }

                }
            }
        });
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_fpS);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Forgot your password?");




        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(ForgotPassword.this, LoginScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
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
    private void fpw() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        // final mLogin mlog = new mLogin(emailText.getText().toString(),pwText.getText().toString());

        StringRequest postRequest = new StringRequest(Request.Method.POST, mAPI.FORGOT_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        // response
                        Log.d("Response", response);
                        Toast.makeText(getApplicationContext(), "Email has been sent successfully",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgotPassword.this, LoginScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                     //   getDialog().dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                        fp_status.setText("Email address doesn't exist");
                    //    fp_status.setTextColor(Color.RED);
                       // fp_pbar.setVisibility(View.GONE);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", fp_email.getText().toString());
                return params;
            }

        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }

    private class LongOperationForgot extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            fpw();
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

            fp_pbar.setVisibility(View.GONE);

        }

        @Override
        protected void onPreExecute() {

            fp_pbar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ForgotPassword.this, LoginScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
