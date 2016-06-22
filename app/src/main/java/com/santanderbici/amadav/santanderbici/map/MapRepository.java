package com.santanderbici.amadav.santanderbici.map;

/**
 * Created by Deathroll on 16/06/2016.
 */
public interface MapRepository {
    String URL_BIKES = "http://datos.santander.es/api/rest/datasets/tusbic_puestos_libres.json" +
            "?data=ayto:bicicletas_libres,ayto:puestos_libres,dc:modified,dc:identifier";
    String URL_STATIONS = "http://datos.santander.es/api/rest/datasets/tusbic_estaciones.json" +
            "?data=ayto:direccion,ayto:longitud,ayto:latitud,dc:identifier";

    void getMarkers();
}
