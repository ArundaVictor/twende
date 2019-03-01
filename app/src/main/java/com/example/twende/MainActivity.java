package com.example.twende;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //welcome button
    @BindView(R.id.welcomeButton) Button mWelcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring button
        ButterKnife.bind(this);

        //click listener for button
        mWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add a toast pop up above the button
                Toast.makeText(MainActivity.this, "Welcome to Twende", Toast.LENGTH_LONG).show();

                //intent that will lead to welcome page
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
