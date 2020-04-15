package com.example.zero2one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    //After completion of 2000 ms, the next activity will get started.
    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This method is used so that your splash activity can cover the entire screen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //Intent is used to switch from one activity to another.
                Intent i = new Intent(MainActivity.this,IndexActivity.class);
                //invoke the SecondActivity.
                startActivity(i);
                //the current activity will get finished.
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
