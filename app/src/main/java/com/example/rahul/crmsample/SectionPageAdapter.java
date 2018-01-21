package com.example.rahul.crmsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 20/1/18.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {

    public List<Fragment> fragmentList=new ArrayList<>();
    public List<String> titleList=new ArrayList<>();

    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
