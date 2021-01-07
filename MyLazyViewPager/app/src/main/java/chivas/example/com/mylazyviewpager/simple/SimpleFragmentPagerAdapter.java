package chivas.example.com.mylazyviewpager.simple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author 享学课堂 Alvin
 * @package com.xiangxue.alvin.mylazyloadingfragment
 * @fileName MyFragmentPagerAdapter
 * @date on 2019/6/10
 * @qq 2464061231
 **/
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "SimpleFragmentPagerAdapter";

    private List<Fragment> fragmentList;
    public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
