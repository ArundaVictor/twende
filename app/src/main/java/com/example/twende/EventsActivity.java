package com.example.twende;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class EventsActivity extends AppCompatActivity {
    private ListView mListView;
    private TextView mTextView;
    private String[] events = new String[] {"Koroga Festival", "Sun Glasses",
            "Blankets and wine", "Ngoma Fest", "JumpOff", "Daylight Insomnia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        mListView = (ListView) findViewById(R.id.listView);
        mTextView = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mTextView.setText("Here are all the events happening in " + location);

    }
}
