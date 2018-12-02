package com.example.madhav.starter.login_signup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.madhav.starter.Home.Dashboard;
import com.example.madhav.starter.Home.all_projects;
import com.example.madhav.starter.Home.categories.explore;
import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.project_status.approved;
import com.example.madhav.starter.login_signup.project_status.pending;

import java.util.ArrayList;
import java.util.List;

public class my_projects extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_mp);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_arrow_back));
        getSupportActionBar().setTitle("My Projects");


        //back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  onBackPressed();
                Intent intent = new Intent(my_projects.this, profile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager_my_proj);

        addTabs(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_my_proj);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);


    }
    private void addTabs(ViewPager viewPager) {
    /*    FragmentActivity myContext;
        FragmentManager fragManager = myContext.getSupportFragmentManager();*/
        my_projects.ViewPagerAdapter adapter = new my_projects.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new approved(), "APPROVED");
        adapter.addFrag(new pending(), "PENDING");


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

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(my_projects.this, profile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
