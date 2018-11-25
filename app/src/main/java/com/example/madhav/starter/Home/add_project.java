package com.example.madhav.starter.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.login_signup.LoginScreen;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SignUp;
import com.example.madhav.starter.model.mAddProject;
import com.example.madhav.starter.model.mSignup;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class add_project extends AppCompatActivity {

    private EditText pu_title;
    private EditText pu_git_proj_link;
    private EditText pu_description;
    private EditText pu_domain;
    private EditText pu_category;

    private Button upload;
    private TextView input_status_pu;
    private ProgressBar pdia_pu;



    Spinner spinner;

    ArrayList<String> Users;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_addpS);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("Add Project");


        // timer.schedule();
        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(add_project.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


        pu_title =findViewById(R.id.pu_title);
        pu_git_proj_link = findViewById(R.id.pu_git_proj_link);
        pu_description = findViewById(R.id.pu_description);
        pu_domain = findViewById(R.id.pu_domain);
        pu_category = findViewById(R.id.pu_category);
        upload = findViewById(R.id.btn_upload);
        input_status_pu = findViewById(R.id.pu_status);
        pdia_pu = findViewById(R.id.pu_progB);
        pdia_pu.setVisibility(View.GONE);
     //   Users=new ArrayList<>();

        //spinner=(Spinner)findViewById(R.id.users);

      // mHandler = new Handler();

       // startRepeatingTask();



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_status_pu.setText("");

                if( TextUtils.isEmpty(pu_title.getText()) || TextUtils.isEmpty(pu_git_proj_link.getText()) || TextUtils.isEmpty(pu_description.getText()) || TextUtils.isEmpty(pu_domain.getText()) || TextUtils.isEmpty(pu_category.getText())) {
                    input_status_pu.setTextColor(Color.RED);
                    input_status_pu.setText("Fields cannot be left blank");

                }
                else
                if(CheckInternet())
                {


                    new LongOperationUpload().execute();

                }
                else
                {
                    input_status_pu.setTextColor(Color.RED);
                    input_status_pu.setText("No Internet Connection");

                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(add_project.this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void upload_project() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("email_pref",MODE_PRIVATE);
        String restoredText = prefs.getString("email", null);


        String name="";
        if (restoredText != null) {
            name = prefs.getString("email", "No name defined");//"No name defined" is the default value.
            //Log.d("EMAIL MACHA",name);



        }
        final mAddProject mUpload = new mAddProject(pu_title.getText().toString(),pu_git_proj_link.getText().toString(),pu_description.getText().toString(),pu_domain.getText().toString(),pu_category.getText().toString());

        //Log.d("email", name);
        //input_status_pu.setText(name);

        StringRequest postRequest = new StringRequest(Request.Method.POST, mAPI.UPLOAD_URL + name,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        pu_title.setText("");
                        pu_git_proj_link.setText("");
                        pu_description.setText("");
                        pu_domain.setText("");
                        pu_category.setText("");
                        //ins.requestFocus();
                        /*Toast.makeText(getApplicationContext(), "Account has been created successfully",
                                Toast.LENGTH_SHORT).show();
                        input_status_su.setTextColor(Color.GRAY);
                        input_status_su.setText("Check your Mail and Set your Password");*/
                        Toast.makeText(getApplicationContext(), "Project uploaded successfully.Please wait for the administrator to approve",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(add_project.this, Dashboard.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                        //getDialog().dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                        input_status_pu.setTextColor(Color.RED);
                        input_status_pu.setText("Error !");

                        input_status_pu.setVisibility(View.GONE);
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("title", mUpload.getPu_title());
                params.put("git_proj_link", mUpload.getPu_git_proj_link());
                params.put("description", mUpload.getPu_description());
                params.put("domain", mUpload.getPu_domain());
                params.put("category", mUpload.getPu_category());
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
    }
    private class LongOperationUpload extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            upload_project();
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
            pdia_pu.setVisibility(View.GONE);
        }

        @Override
        protected void onPreExecute() {

            pdia_pu.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }























    //start of dynamic category

    /*

    private int mInterval = 10000; // 10 seconds refresh

    private Handler mHandler;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {

                loadSpinnerData();

            } finally {

                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };


    void startRepeatingTask() {

        mStatusChecker.run();
    }


    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
*/

/*
    private void loadSpinnerData() {

        Users.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mAPI.USER_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String res="";
                        try{

                            JSONArray array = response.getJSONArray("student");


                            for(int i=0;i<array.length();i++){
                                JSONObject student = array.getJSONObject(i);
                                res= student.getString("Student_details");
                                JSONObject tokenObj = new JSONObject(res);
                                String token = tokenObj.getString("email");
                                Users.add(token);
                                setAdapter();
                            }

                            adapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );


        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
    private void setAdapter(){
        if(adapter==null){
            adapter = new ArrayAdapter<String>(add_project.this, android.R.layout.simple_spinner_dropdown_item, Users);
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }

*/


//end of caterogy

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
}
