package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.R;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return NeighbourFragment.newInstance();
            case 1: return FavoriteFragment.newInstance();
            default: return null;
        }}

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { return super.getPageTitle(position); }
}