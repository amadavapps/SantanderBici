package com.santanderbici.amadav.santanderbici.map.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.ArrayList;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, MapView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private GoogleMap mMap;
    @Inject
    InfoMapAdapter adapter;
    @Inject
    MapPresenter presenter;

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
        MapApplication app=(MapApplication)getApplication();
        MapComponent mapComponent=app.getMapsComponent(this,getLayoutInflater(),getApplicationContext());
        mapComponent.inject(this);
    }

    private void setupMap(){
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
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
        LatLng Santander = new LatLng(43.4722475797229, -3.8199358808);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Santander, 12));
        presenter.getBikeStations();
    }

    @Override
    public void showMarkers(ArrayList<BikeStation> listBikeStation, ArrayList<StateBikeStation> listStateBikeStation) { //deberia ir en el adaptador
        adapter.addMarkers(listBikeStation,listStateBikeStation,mMap);
    }

    @Override
    public void hideMarkers() {
        mMap.clear();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }
}
