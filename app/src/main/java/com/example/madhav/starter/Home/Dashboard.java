package com.example.madhav.starter.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.Home.categories.explore;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.R;
import com.example.madhav.starter.controller.VolleySingleton;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.login_signup.profile;
import com.example.madhav.starter.network.mAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    //private TabLayout tabLayout;
   // private ViewPager viewPager;
    private LinearLayout tv_login;

//   ProgressDialog dialog = ProgressDialog.show(getApplicationContext(), "","Loading..Wait.." , true);
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);




     //  tv_login = findViewById(R.id.ll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final TextView navUsername = (TextView) headerView.findViewById(R.id.nlogintext);
        navUsername.setText("Log in");

        navigationView.setNavigationItemSelectedListener(this);
        all_projects fir = new all_projects();
        setTitle("All Projects");


       // GetUser();




        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            //GetUser();
            /*Toast.makeText(Dashboard.this, "Already Logged in",
                    Toast.LENGTH_LONG).show();*/
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("email_pref",MODE_PRIVATE);
            String restoredText = prefs.getString("email", null);
            String name1="";
            if (restoredText != null) {
                name1 = prefs.getString("email", "No name defined");//"No name defined" is the default value.
            }


            //navUsername = (TextView) headerView.findViewById(R.id.nlogintext);
            navUsername.setText(name1);


            //navUsername.setText(name1);
            // Show the ProgressDialog on this thread


        }

        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {


                    //navUsername.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(Dashboard.this, profile.class);


                    startActivity(intent);
                     finish();

                } else {
                  // GetUser();


                Intent intent = new Intent(Dashboard.this, RegLogActivity.class);
                startActivity(intent);

                    finish();
                }

            }
        });

       /* viewPager = (ViewPager) findViewById(R.id.viewpager);

        addTabs(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();*/

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment,fir).commit();



    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          //  super.onBackPressed();
            moveTaskToBack(true);
        }
    }
 /* @Override
  public void onBackPressed() {

  }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_projects) {
            setTitle("All Projects");
            all_projects fir = new all_projects();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,fir).commit();




        }
        else if (id == R.id.nav_mca) {
            // Handle the cgpa action
            setTitle("MCA");
            mca_projects sec = new mca_projects();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,sec).commit();

        }
        else if (id == R.id.nav_icl) {
            // Handle the cgpa action
            setTitle("ICL");
            icl_projects sec = new icl_projects();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,sec).commit();

        }
        else if(id == R.id.nav_statistics)
        {
            setTitle("Analytics");
            stats sec = new stats();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment,sec).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
/*
    private void setupTabIcons() {
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);

    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new explore(), "EXPLORE");
        adapter.addFrag(new newest(), "NEWEST");

        adapter.addFrag(new upcoming(), "UPCOMING");

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/




    private class BackgroundTask extends AsyncTask <Void, Void, Void> {
        private ProgressDialog dialog;

        public BackgroundTask(Dashboard activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Dashboard.this);
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

    }





}

