package com.lytyfy.deviab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.materialdrawer.DrawerBuilder;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new DrawerBuilder().withActivity(this).build();

    }
}
