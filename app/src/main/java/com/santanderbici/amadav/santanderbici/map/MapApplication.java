package com.santanderbici.amadav.santanderbici.map;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.santanderbici.amadav.santanderbici.lib.di.LibsModule;
import com.santanderbici.amadav.santanderbici.map.di.DaggerMapComponent;
import com.santanderbici.amadav.santanderbici.map.di.MapComponent;
import com.santanderbici.amadav.santanderbici.map.di.MapModule;
import com.santanderbici.amadav.santanderbici.map.ui.MapView;

/**
 * Created by Deathroll on 16/06/2016.
 */
public class MapApplication extends Application{

    public MapComponent getMapsComponent(MapView view, LayoutInflater inflater, Context context){
        return DaggerMapComponent.builder()
                .libsModule(new LibsModule(context))
                .mapModule(new MapModule(view,inflater))
                .build();
    }

}
