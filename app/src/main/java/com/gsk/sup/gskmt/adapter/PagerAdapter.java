package com.gsk.sup.gskmt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gsk.sup.gskmt.fragment.MyperformanceFragment;
import com.gsk.sup.gskmt.fragment.TeamPerformanceFragment;

/**
 * Created by jeevanp on 26-05-2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                MyperformanceFragment tab1 = new MyperformanceFragment();
                return tab1;
            case 1:
                TeamPerformanceFragment tab2 = new TeamPerformanceFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
