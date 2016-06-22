package com.santanderbici.amadav.santanderbici.map.di;

import com.santanderbici.amadav.santanderbici.lib.di.LibsModule;
import com.santanderbici.amadav.santanderbici.map.MapPresenter;
import com.santanderbici.amadav.santanderbici.map.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Deathroll on 16/06/2016.
 */
@Singleton @Component(modules={MapModule.class, LibsModule.class})
public interface MapComponent {
    void inject(MainActivity Activity);
}
