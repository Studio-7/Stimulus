package com.example.stimulus.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.stimulus.Fragment.FragmentDisplayArticles;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FragmentDisplayArticles().newInstance("true");
            case 1: return new FragmentDisplayArticles().newInstance("false");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "FEED";
            case 1:
                return "MINING";
            default:
                return null;
        }
    }

    }
