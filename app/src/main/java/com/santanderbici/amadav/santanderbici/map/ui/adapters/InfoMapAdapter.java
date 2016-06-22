package com.santanderbici.amadav.santanderbici.map.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.santanderbici.amadav.santanderbici.R;
import com.santanderbici.amadav.santanderbici.entities.BikeStation;
import com.santanderbici.amadav.santanderbici.entities.StateBikeStation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Deathroll on 15/06/2016.
 */
public class InfoMapAdapter implements GoogleMap.InfoWindowAdapter {
    LayoutInflater inflater;
    @BindView(R.id.txtTotalBikes)
    TextView txtTotalBikes;
    @BindView(R.id.txtFreeBikes)
    TextView txtFreeBikes;
    @BindView(R.id.txtFreePlaces)
    TextView txtFreePlaces;
    @BindView(R.id.txtDirection)
    TextView txtDirection;

    public InfoMapAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v = inflater.inflate(R.layout.infomap_adapter, null);
        ButterKnife.bind(this, v);

        String snippet[]=marker.getSnippet().split("\\.");
        txtDirection.setText(marker.getTitle().toString());;
        txtTotalBikes.setText(snippet[0]);
        txtFreeBikes.setText(snippet[1]);
        txtFreePlaces.setText(snippet[2]);

        return v;
    }

    public void addMarkers(ArrayList<BikeStation> listBikeStation, ArrayList<StateBikeStation> listStateBikeStation,GoogleMap mMap) {
            for (int i = 0; i < listBikeStation.size(); i++) {
                int totalPlazas=Integer.parseInt(listStateBikeStation.get(i).getFreePlaces()) +  Integer.parseInt(listStateBikeStation.get(i).getFreeBikes());
                LatLng latLng = new LatLng(listBikeStation.get(i).getLatitude(), listBikeStation.get(i).getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(listBikeStation.get(i).getDirecction()).snippet(
                        totalPlazas+ "." + listStateBikeStation.get(i).getFreePlaces() +
                                "." + listStateBikeStation.get(i).getFreeBikes()));
            }
    }
}
