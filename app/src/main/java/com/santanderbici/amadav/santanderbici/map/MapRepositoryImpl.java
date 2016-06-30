package com.santanderbici.amadav.santanderbici.map;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.santanderbici.amadav.santanderbici.R;
import com.santanderbici.amadav.santanderbici.entities.BikeStation;
import com.santanderbici.amadav.santanderbici.entities.StateBikeStation;
import com.santanderbici.amadav.santanderbici.lib.base.EventBus;
import com.santanderbici.amadav.santanderbici.map.events.MapEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Deathroll on 16/06/2016.
 */
public class MapRepositoryImpl implements MapRepository {
    private EventBus eventBus;
    private RequestQueue requestQueue;
    private Context context;

    private final String ERROR_DATA;
    private final String ERROR_RESPONSE;


    public MapRepositoryImpl(EventBus eventBus, RequestQueue requestQueue,Context context) {
        this.eventBus = eventBus;
        this.requestQueue = requestQueue;
        this.context=context;
        ERROR_DATA = this.context.getString(R.string.mapmain_data_error);
        ERROR_RESPONSE = this.context.getString(R.string.mapmain_response_error);
    }

    @Override
    public void getMarkers() {
        getBikeStation();
    }

    public void getBikeStation() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL_STATIONS, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Type listType = new TypeToken<ArrayList<BikeStation>>() {
                        }.getType();

                        try {
                            Gson gson = new Gson();
                            ArrayList<BikeStation> listBikeStation = gson.fromJson(response.getJSONArray("resources").toString(), listType);
                            Collections.sort(listBikeStation);
                            getStateBikeStation(listBikeStation);
                        } catch (JSONException e) {
                            post(ERROR_DATA);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        post(ERROR_RESPONSE);
                    }
                });

        requestQueue.add(jsObjRequest);
    }

    public void getStateBikeStation(final ArrayList<BikeStation> listBikeStation) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL_BIKES, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Type listType = new TypeToken<ArrayList<StateBikeStation>>() {
                        }.getType();
                        try {
                            Gson gson = new Gson();
                            ArrayList<StateBikeStation> lisStateBikeStation = gson.fromJson(response.getJSONArray("resources").toString(), listType);
                            Collections.sort(lisStateBikeStation);

                            if (!lisStateBikeStation.isEmpty() || !listBikeStation.isEmpty()) {
                                post(listBikeStation, lisStateBikeStation);
                            } else {
                                post(ERROR_DATA);
                            }
                        } catch (JSONException e) {
                            post(ERROR_DATA);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        post(ERROR_RESPONSE);
                    }
                });

        requestQueue.add(jsObjRequest);
    }

    private void post(ArrayList<BikeStation> bikeStations, ArrayList<StateBikeStation> stateBikeStations) {
        post(bikeStations, stateBikeStations, null);
    }

    private void post(String error) {
        post(null, null, error);
    }

    private void post(ArrayList<BikeStation> bikeStations, ArrayList<StateBikeStation> stateBikeStations, String error) {
        MapEvent event = new MapEvent();
        event.setError(error);
        event.setListBikeStation(bikeStations);
        event.setListStateBikeStation(stateBikeStations);
        eventBus.post(event);
    }
}
