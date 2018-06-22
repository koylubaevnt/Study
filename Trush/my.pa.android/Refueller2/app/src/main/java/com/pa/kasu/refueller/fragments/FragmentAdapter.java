package com.pa.kasu.refueller.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pa.kasu.refueller.R;

/**
 * Created by koylu on 19.10.2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MyFuellingPointsFragment();
            case 1:
                return new AllFuellingPointsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0 ) {
            return mContext.getString(R.string.my_fuelling_point);
        } else {
            return mContext.getString(R.string.all_fuelling_point);
        }
    }
}
