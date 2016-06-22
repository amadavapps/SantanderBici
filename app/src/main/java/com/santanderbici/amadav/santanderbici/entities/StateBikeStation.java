package com.santanderbici.amadav.santanderbici.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dgago on 14/06/2016.
 */
public class StateBikeStation implements Comparable<StateBikeStation>{
    @SerializedName("ayto:bicicletas_libres")
    private String freeBikes;
    @SerializedName("ayto:puestos_libres")
    private String freePlaces;
    @SerializedName("dc:modified")
    private String lastUpdate;
    @SerializedName("dc:identifier")
    private int id;

    public StateBikeStation(String freeBikes, String freePlaces, int id, String lastUpdate) {
        this.freeBikes = freeBikes;
        this.freePlaces = freePlaces;
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    public String getFreeBikes() {
        return freeBikes;
    }

    public void setFreeBikes(String freeBikes) {
        this.freeBikes = freeBikes;
    }

    public String getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(String freePlaces) {
        this.freePlaces = freePlaces;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(StateBikeStation another) {
        if (another.getId() > this.getId()) {
            return -1;
        } else if (another.getId() < this.getId()) {
            return 1;
        }
        return 0;
    }
}
