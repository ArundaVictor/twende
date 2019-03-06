package com.example.twende;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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


    private String[] events = new String[] {"Koroga Festival", "Sun Glasses",
            "Blankets and wine", "Ngoma Fest", "JumpOff", "Daylight Insomnia", "Safaricom Jazz Festival", "Feastival of love",
    "Nairobi Tech Week", "Nairobi fashion week", "Safaricom Sevens", "Jameson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);



        //array adapter to display lists
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);
        mListView.setAdapter(adapter);

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

                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                    if (response.isSuccessful()){
                        Log.v(TAG, jsonData);

                        mEvents = eventBriteService.processResults(response);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
    }
}
