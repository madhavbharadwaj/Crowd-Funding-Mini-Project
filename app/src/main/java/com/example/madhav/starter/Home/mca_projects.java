package com.example.madhav.starter.Home;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madhav.starter.Home.categories.explore;
import com.example.madhav.starter.Home.categories.newest;
import com.example.madhav.starter.Home.categories.upcoming;
import com.example.madhav.starter.Home.categories_mca.exploreMca;
import com.example.madhav.starter.Home.categories_mca.newestMca;
import com.example.madhav.starter.Home.categories_mca.upcomingMca;
import com.example.madhav.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class mca_projects extends Fragment {

    private TabLayout tabLayout_mca;
    private ViewPager viewPager_mca;

    public mca_projects() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mca_projects, container, false);
        viewPager_mca = (ViewPager) v.findViewById(R.id.viewpager_mca);

        addTabs(viewPager_mca);

        tabLayout_mca = (TabLayout) v.findViewById(R.id.tabs_mca);
        tabLayout_mca.setupWithViewPager(viewPager_mca);
        setupTabIcons();

        return v;
    }
    private void setupTabIcons() {
        tabLayout_mca.getTabAt(0);
        tabLayout_mca.getTabAt(1);
        tabLayout_mca.getTabAt(2);

    }
    private void addTabs(ViewPager viewPager) {
    /*    FragmentActivity myContext;
        FragmentManager fragManager = myContext.getSupportFragmentManager();*/
        mca_projects.ViewPagerAdapter adapter = new mca_projects.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new exploreMca(), "EXPLORE");
        adapter.addFrag(new newestMca(), "NEWEST");

        adapter.addFrag(new upcomingMca(), "UPCOMING");

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
