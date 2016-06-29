package com.santanderbici.amadav.santanderbici.lib.base;

/**
 * Created by Deathroll on 16/06/2016.
 */
public interface EventBus {
    void register(Object suscriber);

    void unregister(Object suscriber);

    void post(Object event);
}
