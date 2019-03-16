package com.example.twende.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.twende.Constants;
import com.example.twende.adapters.EventListAdapter;
import com.example.twende.services.EventBriteService;
import com.example.twende.R;
import com.example.twende.models.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventListActivity extends AppCompatActivity {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getEvents(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

    //method to write data to shared preferences
    private void addToSharedPreferences (String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
