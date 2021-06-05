package com.ttwcalc.promate.subcontractor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ttwcalc.promate.R;
import com.ttwcalc.promate.SubContractorFragment;
import com.ttwcalc.promate.SubContractorsFragment;

public class DetailsMainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        toolbar = findViewById(R.id.contractor_toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new SubContractorFragment()).
                commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.contractor:
                    selectedFragment = new SubContractorFragment();
                    break;

                case R.id.client:
                    selectedFragment = new SubContractorsFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();

            return true;
        }
    };
}