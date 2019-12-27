package com.example.apporderfood.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.apporderfood.R;
import com.google.android.material.navigation.NavigationView;


public class HomePageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = HomePageActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private TextView mTvEmplName;
    private View mView;

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
        mView = mNavigationView.getHeaderView(0);
        mTvEmplName = mView.findViewById(R.id.tv_name_navi);
        configureToolbar();
        configureNavigationDrawer();
    }

    private void addEvent() {
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        Log.d(TAG, "addEvent: " + account);
        mTvEmplName.setText(account);
    }

    @SuppressLint("RestrictedApi")
    private void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDefaultDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    private void configureNavigationDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
