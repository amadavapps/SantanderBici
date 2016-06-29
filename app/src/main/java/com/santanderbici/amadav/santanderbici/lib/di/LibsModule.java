package com.santanderbici.amadav.santanderbici.lib.di;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.santanderbici.amadav.santanderbici.lib.GoogleVolley;
import com.santanderbici.amadav.santanderbici.lib.GreenRobotEventBus;
import com.santanderbici.amadav.santanderbici.lib.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Deathroll on 16/06/2016.
 */
@Module
public class LibsModule {
    Context context;

    public LibsModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventbus) {
        return new GreenRobotEventBus(eventbus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    RequestQueue providesRequestQueue(GoogleVolley volley) {
        return volley.getRequestQueue();
    }

    @Provides
    @Singleton
    GoogleVolley providesGoogleVolley() {
        return GoogleVolley.getInstance(context);
    }
}
