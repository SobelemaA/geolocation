package org.alancesar.geolocation.place;

import org.alancesar.geolocation.Geometry;
import org.alancesar.geolocation.LocationType;

import java.util.List;

public class PlaceDetail {

    private Geometry geometry;
    private String icon;
    private String name;
    private String placeId;
    private List<LocationType> types;
    private String vicinity;

    private PlaceDetail() {
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

    public List<LocationType> getType() {
        return types;
    }

    public String getVicinity() {
        return vicinity;
    }
}
