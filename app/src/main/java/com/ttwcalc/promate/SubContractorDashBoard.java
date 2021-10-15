package com.ttwcalc.promate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SubContractorDashBoard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_contractor_dash_board);

        toolbar = findViewById(R.id.sub_contractor_toolbar);
        drawerLayout = findViewById(R.id.subcontractor_drawer);
        navigationView = findViewById(R.id.sub_contractor_nav_menu);

       actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.subcontractor_dash, new SubContractorsFragment())
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
                        fragment = new SubContractorsFragment();
                        break;
                    case R.id.logout :
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.getCurrentUser().delete();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(SubContractorDashBoard.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }

//                getSupportFragmentManager().beginTransaction().replace(R.id.subcontractor_dash, fragment).commit();
//                drawerLayout.closeDrawer(GravityCompat.START);
                  return true;
            }
        });
    }
}