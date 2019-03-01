package com.example.twende;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    public static final String TAG = WelcomeActivity.class.getSimpleName();
   @BindView(R.id.eventsButton) Button mEventsButton;
   @BindView(R.id.editText) EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location = mEditText.getText().toString();
                Log.d(TAG, location);
                Toast.makeText(WelcomeActivity.this, "Success", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(WelcomeActivity.this, EventsActivity.class);

                //passing data with intent
                intent.putExtra("location", location);
                startActivity(intent);



            }
        });
    }
}
