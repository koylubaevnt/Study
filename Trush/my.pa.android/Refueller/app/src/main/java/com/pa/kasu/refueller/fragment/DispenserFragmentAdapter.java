package com.pa.kasu.refueller.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.pa.kasu.refueller.R;
import com.pa.kasu.refueller.dto.Dispenser;

import java.util.ArrayList;
import java.util.List;

public class DispenserFragmentAdapter extends FragmentPagerAdapter {

    /** Контекст приложения  */
    private Context mContext;
    /** Список Все ТРК*/
    private List<Dispenser> mAllDispensers;
    /** Список Мои ТРК*/
    private List<Dispenser> mMyDispensers;

    /**
     * Создает новый {@link DispenserFragmentAdapter} объект.
     *
     * @param context контекст приложения
     * @param fm Менеджер фрагментов
     */
    public DispenserFragmentAdapter(Context context, FragmentManager fm, List<Dispenser> dispensers) {
        super(fm);
        mContext = context;
        mAllDispensers = dispensers;
        mMyDispensers = new ArrayList<>(mAllDispensers.size());
        for (Dispenser dispenser : mAllDispensers) {
            if(dispenser.isMy()) {
                mMyDispensers.add(dispenser);
            }
        }
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllDispenserFragment(mAllDispensers);
        } else {
            return new MyDispenserFragment(mMyDispensers);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.tab_dispenser_all);
        } else {
            return mContext.getString(R.string.tab_dispenser_my);
        }
    }
}
