package com.example.twende.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class EventsActivity extends AppCompatActivity {
    public static final String TAG = EventsActivity.class.getSimpleName();
   @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.textView) TextView mTextView;

    //array for all events
    public ArrayList <Event> mEvents = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);





        //gathering data from intent extras
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mTextView.setText("Here are all the events happening in " + location);

        getEvents(location);



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

                mEvents = eventBriteService.processResults(response);

                //add runnable to share code between interfaces
                EventsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //to display list of events for the user
                        String[] eventNames = new String[mEvents.size()];
                        for (int i = 0; i<eventNames.length; i++){
                            eventNames[i] = mEvents.get(i).getName();
                        }

                        //array adapter to display the list of events to user
                        ArrayAdapter adapter = new ArrayAdapter(EventsActivity.this, android.R.layout.simple_list_item_1,eventNames);
                        mListView.setAdapter(adapter);


                        for (Event event : mEvents){

                            Log.d(TAG, "Name" + event.getName());
                            Log.d(TAG, "Description" + event.getDescription());
                            Log.d(TAG, "Status" + event.getStatus());
                            Log.d(TAG, "Url" + event.getUrl());
                            Log.d(TAG, "Currency" + event.getCurrency());
                        }






                    }
                });

//
            }
        });
    }
}
