package com.example.madhav.starter.Home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.login_signup.LoginScreen;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class add_project extends AppCompatActivity {

    Spinner spinner;
   // private ArrayAdapter<String> adapter;
    ArrayList<String> Users;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        Users=new ArrayList<>();

        spinner=(Spinner)findViewById(R.id.users);
       /* adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Users);*/
       // spinner.setAdapter(adapter);
      //  loadSpinnerData();
       // timer.schedule(doTask, 1000);
        //loadSpinnerData();

       mHandler = new Handler();

        startRepeatingTask();

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
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(add_project.this, Dashboard.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private int mInterval = 10000; // 10 seconds refresh

    private Handler mHandler;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                //initializeSpinner();
                //spinner.setAdapter(new ArrayAdapter<String>(add_project.this, android.R.layout.simple_spinner_dropdown_item, Users));
               // spinner.notifyDataSetChanged();
                //adapter.notifyDataSetChanged();

                loadSpinnerData();
              //  Users.clear();


               // Users.not
                //spinner.notifyAll();

            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };


    void startRepeatingTask() {
        //Log.d("called","tip");
        mStatusChecker.run();
    }


    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }




    /*final Handler handler = new Handler();
    Timer timer = new Timer();
    TimerTask doTask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @SuppressWarnings("unchecked")
                public void run() {
                    try {
                        /*Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        loadSpinnerData();
                    }
                    catch (Exception e) {
                        // TODO Auto-generated catch block
                    }
                }
            });
        }
    };*/

//timer.schedule(doTask, 0, "Your time 10 minute");


    private void loadSpinnerData() {

        Users.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mAPI.USER_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        String res="";
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("student");

                            //JSONArray  onwardflights = array.getJSONArray("onwardflights");
                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                //Log.d("test", array.length());
                                // Get current json object
                                JSONObject student = array.getJSONObject(i);
                                //JSONObject tokenObj = new JSONObject(student); //change to this
                                //res = student.optString("statusSeverityDescription", "");
                                res= student.getString("Student_details");
                                JSONObject tokenObj = new JSONObject(res);

                                String token = tokenObj.getString("email");
                               // String a = checkNumber(student, "phone");
                                //String statusSeverityDescription = student.optString("email", "");


                                //Log.d("test", token);
                                Users.add(token);
                                setAdapter();
                                // Get the current student (json object) data
                               // String hea_tip = student.getString("user_details");
                               // String Quotes = '"'+ hea_tip+'"';
                                //String lastName = student.getString("type");
                                // String age = student.getString("age");

                                // Display the formatted json data in text view
                                //mTextView.append(firstName +" " + lastName +"\nage : " + age);
                                // mTextView.append("\n\n");
                                // tipP.setText(response.get("count") + Quotes);
                               // tipP.setText(Quotes);
                               /*Intent n = new Intent(LoginScreenActivity.this, HomeActivity.class);
                               n.putExtra("puttip",Quotes);
                               startActivity(n);*/


                            }
                           // spinner.setAdapter(new ArrayAdapter<String>(add_project.this, android.R.layout.simple_spinner_dropdown_item, Users));
                           // spinner.
                            adapter.notifyDataSetChanged();

                           // spinner.setAdapter(adapter);
                           // dataAdapter.notifyDataSetChanged();
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


        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
    private void setAdapter(){
        if(adapter==null){
            adapter = new ArrayAdapter<String>(add_project.this, android.R.layout.simple_spinner_dropdown_item, Users);
            //adapter = new CustomListAdapter(add_project.this, Users);
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }


}
