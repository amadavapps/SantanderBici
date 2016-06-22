package com.santanderbici.amadav.santanderbici.map;

/**
 * Created by Deathroll on 16/06/2016.
 */
public class MapInteractorImpl implements MapInteractor {
    MapRepository repository;

    public MapInteractorImpl(MapRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getMarkers();
    }
}
