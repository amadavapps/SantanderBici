package com.santanderbici.amadav.santanderbici.map.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    @BindView(R.id.txtTotalBikes)
    TextView txtTotalBikes;
    @BindView(R.id.txtFreeBikes)
    TextView txtFreeBikes;
    @BindView(R.id.txtFreePlaces)
    TextView txtFreePlaces;
    @BindView(R.id.txtDirection)
    TextView txtDirection;

    private LayoutInflater inflater;
    private ArrayList<StateBikeStation> listStateBikeStation;

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

        int pos = Integer.parseInt(marker.getSnippet());
        int totalBikes = Integer.parseInt(listStateBikeStation.get(pos).getFreePlaces()) +
                Integer.parseInt(listStateBikeStation.get(pos).getFreeBikes());

        txtDirection.setText(marker.getTitle());
        txtTotalBikes.setText(String.valueOf(totalBikes));
        txtFreeBikes.setText(listStateBikeStation.get(pos).getFreeBikes());
        txtFreePlaces.setText(listStateBikeStation.get(pos).getFreePlaces());

        return v;
    }

    public void addMarkers(ArrayList<BikeStation> listBikeStation, ArrayList<StateBikeStation> listStateBikeStation, GoogleMap mMap) {
        for (int i = 0; i < listBikeStation.size(); i++) {
            LatLng latLng = new LatLng(listBikeStation.get(i).getLatitude(), listBikeStation.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(listBikeStation.get(i).getDirecction())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_zone))
                    .snippet(String.valueOf(i)));

        }
        this.listStateBikeStation = listStateBikeStation;
    }
}
