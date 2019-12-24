package com.example.apporderfood.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.apporderfood.R;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity {

    private static final String TAG = HomePageActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        init();
    }

    private void init() {
        initView();
        addEvent();
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.dl_homepage);
        mNavigationView = findViewById(R.id.nv_homepage);
        mToolbar = findViewById(R.id.tb_homepage);
        configureToolbar();
    }

    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_home_page);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    private void addEvent() {
        Intent intent = new Intent();
        String account = intent.getStringExtra("account");
    }
}
