package com.example.madhav.starter.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.Home.categories.popular;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.R;
import com.example.madhav.starter.Splash_Screen.LaunchScreenActivity;
import com.example.madhav.starter.login_signup.LoginScreen;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.login_signup.PreferencesUtility;
import com.example.madhav.starter.login_signup.profile;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout tv_login;


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
        navigationView.setNavigationItemSelectedListener(this);
        all_projects fir = new all_projects();




        setTitle("All Projects");



        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nlogintext);
       // navUsername.setText("Your Text Here");

        if(SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
            /*Toast.makeText(Dashboard.this, "Already Logged in",
                    Toast.LENGTH_LONG).show();*/
            SharedPreferences prefs = getApplicationContext().getSharedPreferences("email_pref",MODE_PRIVATE);
            String restoredText = prefs.getString("email", null);
            String name="";
            if (restoredText != null) {
                name = prefs.getString("email", "No name defined");//"No name defined" is the default value.
            }


            navUsername.setText(name);

        }
        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(), "Login Successful",
                        Toast.LENGTH_LONG).show();*/

                if (SaveSharedPreference.getLoggedStatus(getApplicationContext())) {
                    Intent intent = new Intent(Dashboard.this, profile.class);


                    startActivity(intent);
                     finish();

                } else {



                Intent intent = new Intent(Dashboard.this, RegLogActivity.class);
                startActivity(intent);
                    finish();
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        addTabs(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment,fir).commit();


/*        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Login Successful",
                        Toast.LENGTH_LONG).show();
            }
        });*/

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);

    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new newest(), "NEWEST");
        adapter.addFrag(new popular(), "POPULAR");
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
    }

}
