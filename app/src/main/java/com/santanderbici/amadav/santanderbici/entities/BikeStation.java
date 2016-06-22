package com.santanderbici.amadav.santanderbici.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dgago on 14/06/2016.
 */
public class BikeStation implements Comparable<BikeStation> {

    @SerializedName("ayto:direcction")
    @Expose
    private String direcction;
    @SerializedName("dc:identifier")
    @Expose
    private int id;
    @SerializedName("ayto:latitude")
    @Expose
    private double latitude;
    @SerializedName("ayto:longitude")
    @Expose
    private double longitude;

    public BikeStation(int id, String direcction, double latitude, double longitude) {
        this.direcction = direcction;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDirecction() {
        return direcction;
    }

    public void setDirecction(String direcction) {
        this.direcction = direcction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude){

    }

    @Override
    public int compareTo(BikeStation another) {
        if (another.getId() > this.getId()) {
            return -1;
        } else if (another.getId() < this.getId()) {
            return 1;
        }
        return 0;
    }
}