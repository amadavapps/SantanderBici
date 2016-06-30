package com.santanderbici.amadav.santanderbici.map.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.android.volley.RequestQueue;
import com.santanderbici.amadav.santanderbici.lib.base.EventBus;
import com.santanderbici.amadav.santanderbici.map.MapInteractor;
import com.santanderbici.amadav.santanderbici.map.MapInteractorImpl;
import com.santanderbici.amadav.santanderbici.map.MapPresenter;
import com.santanderbici.amadav.santanderbici.map.MapPresenterImpl;
import com.santanderbici.amadav.santanderbici.map.MapRepository;
import com.santanderbici.amadav.santanderbici.map.MapRepositoryImpl;
import com.santanderbici.amadav.santanderbici.map.ui.MapView;
import com.santanderbici.amadav.santanderbici.map.ui.adapters.InfoMapAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Deathroll on 16/06/2016.
 */
@Module
public class MapModule {
    private MapView view;
    private LayoutInflater inflater;
    private Context context;

    public MapModule(MapView view, LayoutInflater inflater,Context context) {
        this.view = view;
        this.inflater = inflater;
        this.context=context;
    }

    @Provides
    @Singleton
    InfoMapAdapter providesAdapter(LayoutInflater inflater) {
        return new InfoMapAdapter(inflater);
    }

    @Provides
    @Singleton
    LayoutInflater providesLayoutInflater() {
        return this.inflater;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return this.context;
    }

    @Provides
    @Singleton
    MapPresenter providesMapPresenter(EventBus eventBus, MapView view, MapInteractor interactor) {
        return new MapPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    MapView providesMapView() {
        return this.view;
    }

    @Provides
    @Singleton
    MapInteractor providesMapInteractor(MapRepository repository) {
        return new MapInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MapRepository providesMapRepository(EventBus eventBus, RequestQueue requestQueue,Context context) {
        return new MapRepositoryImpl(eventBus, requestQueue,context);
    }


}
