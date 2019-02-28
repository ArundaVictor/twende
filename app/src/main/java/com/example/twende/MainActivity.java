package com.example.twende;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //welcome button
    private Button mWelcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring button
        mWelcomeButton = (Button) findViewById(R.id.welcomeButton);

        //click listener for button
        mWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //add a toast pop up above the button
                Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
            }
        });
    }
}
