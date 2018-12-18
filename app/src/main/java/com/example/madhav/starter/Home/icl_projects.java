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

import com.example.madhav.starter.Home.categories_icl.exploreIcl;
import com.example.madhav.starter.Home.categories_icl.newestIcl;
import com.example.madhav.starter.Home.categories_icl.upcomingIcl;
import com.example.madhav.starter.Home.categories_mca.exploreMca;
import com.example.madhav.starter.Home.categories_mca.newestMca;
import com.example.madhav.starter.Home.categories_mca.upcomingMca;
import com.example.madhav.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class icl_projects extends Fragment {

    private TabLayout tabLayout_icl;
    private ViewPager viewPager_icl;
    public icl_projects() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_icl_projects, container, false);
        viewPager_icl = (ViewPager) v.findViewById(R.id.viewpager_icl);

        addTabs(viewPager_icl);

        tabLayout_icl = (TabLayout) v.findViewById(R.id.tabs_icl);
        tabLayout_icl.setupWithViewPager(viewPager_icl);
        setupTabIcons();

        return v;
    }

    private void setupTabIcons() {
        tabLayout_icl.getTabAt(0);
        tabLayout_icl.getTabAt(1);
        tabLayout_icl.getTabAt(2);

    }
    private void addTabs(ViewPager viewPager) {
    /*    FragmentActivity myContext;
        FragmentManager fragManager = myContext.getSupportFragmentManager();*/
        icl_projects.ViewPagerAdapter adapter = new icl_projects.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new exploreIcl(), "EXPLORE");
        adapter.addFrag(new newestIcl(), "NEWEST");

        adapter.addFrag(new upcomingIcl(), "UPCOMING");

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
