package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class EngineerDashBoardFragment extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer_dash_board_fragment);


        toolbar = findViewById(R.id.engineer_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.engineer_drawer_layout);
        navigationView = findViewById(R.id.engineer_nav_menu);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new EngineerDashFragment())
                .commit();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().
                getColor(R.color.colorPrimary));
        navigationView.setCheckedItem(R.id.all);

        navigationView.setNavigationItemSelectedListener(new NavigationView.
                OnNavigationItemSelectedListener() {

            Fragment fragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.all:
                        fragment = new EngineerDashFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
}