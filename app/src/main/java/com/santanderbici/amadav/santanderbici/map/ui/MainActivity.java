package com.santanderbici.amadav.santanderbici.map.ui;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.santanderbici.amadav.santanderbici.map.MapApplication;
import com.santanderbici.amadav.santanderbici.R;
import com.santanderbici.amadav.santanderbici.entities.BikeStation;
import com.santanderbici.amadav.santanderbici.entities.StateBikeStation;
import com.santanderbici.amadav.santanderbici.map.MapPresenter;
import com.santanderbici.amadav.santanderbici.map.di.MapComponent;
import com.santanderbici.amadav.santanderbici.map.ui.adapters.InfoMapAdapter;
import com.santanderbici.amadav.santanderbici.settings.OptionsActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, MapView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    InfoMapAdapter adapter;
    @Inject
    MapPresenter presenter;

    private GoogleMap mMap;
    private final int ZOOM_MAP = 12;
    private final LatLng SANTANDER_COORDINATES = new LatLng(43.4722475797229, -3.8199358808);
    private MenuItem refreshItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupMap();
        setupInjection();
    }

    private void setupInjection() {
        MapApplication app = (MapApplication) getApplication();
        MapComponent mapComponent = app.getMapsComponent(this, getLayoutInflater(), getApplicationContext());
        mapComponent.inject(this);
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(adapter);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SANTANDER_COORDINATES, ZOOM_MAP));
        presenter.getBikeStations();
    }

    @Override
    public void showMarkers(ArrayList<BikeStation> listBikeStation, ArrayList<StateBikeStation> listStateBikeStation) {
        adapter.addMarkers(listBikeStation, listStateBikeStation, mMap);
    }

    @Override
    public void hideMarkers() {
        mMap.clear();
    }

    @Override
    public void showError(int error) {
        if (error == 1) {
            Toast.makeText(this, getString(R.string.mapmain_data_error), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getString(R.string.mapmain_response_error), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void showProgressUpdate() {
        if(refreshItem!=null) {
            refreshItem.setActionView(R.layout.toolbar_progress);
        }
    }

    @Override
    public void hideProgressUpdate() {
        if(refreshItem!=null) {
            refreshItem.setActionView(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        refreshItem = menu.findItem(R.id.action_update);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            hideProgressUpdate();
            startActivity(new Intent(this, OptionsActivity.class));
        }
        if (id == R.id.action_update) {
            presenter.getBikeStations();
        }
        return super.onOptionsItemSelected(item);
    }
}
