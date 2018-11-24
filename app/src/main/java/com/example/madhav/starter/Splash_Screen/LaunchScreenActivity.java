package com.example.madhav.starter.Splash_Screen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.R;

public class LaunchScreenActivity extends AppCompatActivity {

    private ProgressBar pdia_ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        pdia_ls = findViewById(R.id.progB_ls);
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask {
        Intent intent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia_ls.setVisibility(View.VISIBLE);
            intent = new Intent(LaunchScreenActivity.this,Dashboard.class);

        }

        @Override
        protected Object doInBackground(Object[] params) {

            /*  Use this method to load background
             * data that your app needs. */

            try {
                RequestQueue queue = Volley.newRequestQueue(LaunchScreenActivity.this);
                String url ="https://test-api-man.herokuapp.com/student/";

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Log.d("Response is: ",response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response is:","That didn't work!");
                    }
                });

// Add the request to the RequestQueue.
                queue.add(stringRequest);

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            Pass your loaded data here using Intent
            pdia_ls.setVisibility(View.GONE);
//            intent.putExtra("data_key", "");
            startActivity(intent);
            finish();
        }
    }


}
