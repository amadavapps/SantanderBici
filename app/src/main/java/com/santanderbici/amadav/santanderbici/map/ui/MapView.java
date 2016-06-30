package com.santanderbici.amadav.santanderbici.map.ui;

import com.santanderbici.amadav.santanderbici.entities.BikeStation;
import com.santanderbici.amadav.santanderbici.entities.StateBikeStation;

import java.util.ArrayList;

/**
 * Created by Deathroll on 16/06/2016.
 */
public interface MapView {
    void showMarkers(ArrayList<BikeStation> listBikeStation, ArrayList<StateBikeStation> listStateBikeStation);

    void hideMarkers();

    void showError(String error);

    void showProgressUpdate();

    void hideProgressUpdate();
}
