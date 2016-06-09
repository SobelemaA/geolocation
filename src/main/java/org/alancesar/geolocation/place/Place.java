package org.alancesar.geolocation.place;

import java.util.List;

import org.alancesar.geolocation.Geometry;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.service.PlaceService;

public class Place {
    private String formattedAddress;
    private Geometry geometry;
    private String icon;
    private String name;
    private String placeId;
    
    private Place() {}

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getPlaceId() {
        return placeId;
    }
    
    public static List<Place> find(String query) {
        try {
            return new PlaceService().findPlaces(query);
        } catch (RequestException e) {
            e.printStackTrace();
            return null;
        }
    }
}
