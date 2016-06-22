package com.santanderbici.amadav.santanderbici.map;

import com.santanderbici.amadav.santanderbici.map.events.MapEvent;

/**
 * Created by Deathroll on 16/06/2016.
 */
public interface MapPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getBikeStations();
    void onEventMainThread(MapEvent mapEvent);
}
