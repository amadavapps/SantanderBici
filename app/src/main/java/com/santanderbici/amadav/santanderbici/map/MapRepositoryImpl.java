package com.santanderbici.amadav.santanderbici.map;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    private final String bicis = "http://datos.santander.es/api/rest/datasets/tusbic_puestos_libres.json" +
            "?data=ayto:bicicletas_libres,ayto:puestos_libres,dc:modified,dc:identifier";
    private final String estaciones = "http://datos.santander.es/api/rest/datasets/tusbic_estaciones.json" +
            "?data=ayto:direccion,ayto:longitud,ayto:latitud,dc:identifier";
    private final String errorResponse = "Fallo de conexion con el servidor";        //@string
    private final String errorData = "Datos recibidos incorrectos";     //@string

    public MapRepositoryImpl(EventBus eventBus, RequestQueue requestQueue) {
        this.eventBus = eventBus;
        this.requestQueue = requestQueue;
    }

    @Override
    public void getMarkers() {
        getBikeStation();
    }

    public void getBikeStation() {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, estaciones, null, new Response.Listener<JSONObject>() {

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
                            post(errorData);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        post(errorResponse);
                    }
                }) {
        };
        requestQueue.add(jsObjRequest);
    }

    public void getStateBikeStation(final ArrayList<BikeStation> listBikeStation) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, bicis, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Type listType = new TypeToken<ArrayList<StateBikeStation>>() {
                        }.getType();
                        try {
                            Gson gson = new Gson();
                            ArrayList<StateBikeStation> lisStateBikeStation = gson.fromJson(response.getJSONArray("resources").toString(), listType);
                            Collections.sort(lisStateBikeStation);
                            post(listBikeStation, lisStateBikeStation);
                        } catch (JSONException e) {
                            post(errorData);
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        post(errorResponse);
                    }
                }) {
        };
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
