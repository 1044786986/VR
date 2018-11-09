package com.example.ljh.vr.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class ViewpagerAdapterMain extends FragmentStatePagerAdapter {
    private List<Fragment> dataList;

    public ViewpagerAdapterMain(FragmentManager fm, List<Fragment> list) {
        super(fm);
        dataList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }
}
