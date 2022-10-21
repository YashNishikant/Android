package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragmentViewPagerAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> itemlist;

    public FragmentViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        itemlist = new ArrayList<Fragment>();
        itemlist.add(new pageWorkoutList());
        itemlist.add(new pageGraph());
        itemlist.add(new pageMap());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return itemlist.get(position);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }
}
