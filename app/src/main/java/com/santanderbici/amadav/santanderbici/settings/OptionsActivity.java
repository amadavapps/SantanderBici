package com.santanderbici.amadav.santanderbici.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.santanderbici.amadav.santanderbici.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OptionsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCurrentFragments(savedInstanceState);
    }

    public void getCurrentFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            showFragment();
        } else {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_opciones);
            if (currentFragment instanceof SettingsFragment) {
                settingsFragment = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.content_opciones);
            }
        }
    }

    public void showFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        settingsFragment = new SettingsFragment();
        fragmentTransaction.add(R.id.content_opciones, settingsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}


