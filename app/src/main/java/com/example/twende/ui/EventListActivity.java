package com.example.twende.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.twende.Constants;
import com.example.twende.adapters.EventListAdapter;
import com.example.twende.services.EventBriteService;
import com.example.twende.R;
import com.example.twende.models.Event;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private String mLocation;

    public static final String TAG = EventListActivity.class.getSimpleName();
   @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;

    //array for all events
    public ArrayList <Event> events = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);





        //gathering data from intent extras
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getEvents(location);

        //log location entered
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        if (mLocation != null) {
            getEvents(mLocation);
        }



    }

    //callback method
    private void getEvents(String location){

        final EventBriteService eventBriteService = new EventBriteService();
        eventBriteService.findEvents(location, new Callback() {

            //triggered when our request fails
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            //triggered when request is successful
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                events = eventBriteService.processResults(response);

                //add runnable to share code between interfaces
                EventListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new EventListAdapter(getApplicationContext(), events);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EventListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);






                    }
                });

//
            }
        });
    }
}
