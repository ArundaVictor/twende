package com.example.twende.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.twende.models.Event;
import com.example.twende.ui.EventDetailFragment;

import java.util.ArrayList;

public class EventPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Event> mEvents;

    public EventPagerAdapter (FragmentManager fm, ArrayList<Event> events){

        super(fm);
        mEvents = events;
    }

    @Override
    public Fragment getItem (int position){
        return EventDetailFragment.newInstance(mEvents.get(position));
    }


    @Override

    public int getCount(){
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mEvents.get(position).getName();
    }

}
