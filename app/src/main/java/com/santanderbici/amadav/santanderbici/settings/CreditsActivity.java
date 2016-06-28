package com.santanderbici.amadav.santanderbici.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.santanderbici.amadav.santanderbici.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dgf on 28/05/2016.
 */
public class CreditsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }
}
