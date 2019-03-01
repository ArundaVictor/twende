package com.example.twende;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //welcome button
    @BindView(R.id.welcomeButton) Button mWelcomeButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);


        mWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, "Welcome to Twende", Toast.LENGTH_LONG).show();


                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
                Typeface walkway = Typeface.createFromAsset(getAssets(), "Fonts/Walkway_Black.ttf");
                mAppNameTextView.setTypeface(walkway);
            }
        });
    }
}
