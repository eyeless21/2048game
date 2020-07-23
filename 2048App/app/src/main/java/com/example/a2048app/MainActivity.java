package com.example.a2048app;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;


//THE MAIN ACTIVITY STARTS CALLING OUR THREAD
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }
}
