package com.gsk.sup.gskmt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gsk.sup.gskmt.fragment.MyperformanceFragment;
import com.gsk.sup.gskmt.fragment.TeamPerformanceFragment;

/**
 * Created by jeevanp on 26-05-2017.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyperformanceFragment();
            case 1:
                return new TeamPerformanceFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
