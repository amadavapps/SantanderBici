package com.santanderbici.amadav.santanderbici.map.events;

import com.santanderbici.amadav.santanderbici.entities.BikeStation;
import com.santanderbici.amadav.santanderbici.entities.StateBikeStation;

import java.util.ArrayList;

/**
 * Created by Deathroll on 16/06/2016.
 */
public class MapEvent {
    private String error;
    private ArrayList<BikeStation> listBikeStation;
    private ArrayList<StateBikeStation> listStateBikeStation;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<BikeStation> getListBikeStation() {
        return listBikeStation;
    }

    public void setListBikeStation(ArrayList<BikeStation> listBikeStation) {
        this.listBikeStation = listBikeStation;
    }

    public ArrayList<StateBikeStation> getListStateBikeStation() {
        return listStateBikeStation;
    }

    public void setListStateBikeStation(ArrayList<StateBikeStation> listStateBikeStation) {
        this.listStateBikeStation = listStateBikeStation;
    }
}
