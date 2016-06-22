package com.santanderbici.amadav.santanderbici.map;


import com.santanderbici.amadav.santanderbici.lib.base.EventBus;
import com.santanderbici.amadav.santanderbici.map.events.MapEvent;
import com.santanderbici.amadav.santanderbici.map.ui.MapView;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by Deathroll on 16/06/2016.
 */
public class MapPresenterImpl implements MapPresenter {
    private EventBus eventBus;
    private MapView view;
    private MapInteractor interactor;

    public MapPresenterImpl(EventBus eventBus, MapView view, MapInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getBikeStations() {
        if (view != null) {
            view.hideMarkers();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(MapEvent mapEvent) {
        int errorNumber = mapEvent.getError();
        if (view != null) {
            if (errorNumber != 0) {
                view.showError(errorNumber);
            } else {
                view.showMarkers(mapEvent.getListBikeStation(), mapEvent.getListStateBikeStation());
            }

        }
    }
}


