package com.example.madhav.starter.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madhav.starter.Home.categories.explore;
import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.R;
import com.example.madhav.starter.login_signup.RegLogActivity;
import com.example.madhav.starter.login_signup.SaveSharedPreference;
import com.example.madhav.starter.login_signup.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class all_projects extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public all_projects() {
        // Required empty public constructor
    }



    Button b1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_all_projects, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager_ap);

        addTabs(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs_ap);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        return v;



    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0);
        tabLayout.getTabAt(1);
        tabLayout.getTabAt(2);

    }
    private void addTabs(ViewPager viewPager) {
    /*    FragmentActivity myContext;
        FragmentManager fragManager = myContext.getSupportFragmentManager();*/
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
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
    }


}
