package org.alancesar.geolocation.address;

import org.alancesar.geolocation.Geometry;
import org.alancesar.geolocation.Location;
import org.alancesar.geolocation.LocationType;
import org.alancesar.geolocation.place.PlaceDetail;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.service.GeolocationService;
import org.alancesar.geolocation.service.PlaceDetailService;
import org.alancesar.geolocation.util.DistanceUtils;

import java.util.List;

public class Address {

    private List<AddressComponents> addressComponents;
    private String formattedAddress;
    private Geometry geometry;
    private String placeId;

    private Address() {
    }

    public List<AddressComponents> getAddressComponents() {
        return addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getPlaceId() {
        return placeId;
    }

    public double distanceTo(Address address) {
        return DistanceUtils.distanceInKm(this.geometry.getLocation(), address.getGeometry().getLocation());
    }

    public List<PlaceDetail> findPlacesNearby(LocationType... filter) {
        try {
            return new PlaceDetailService().findPlacesNearby(geometry.getLocation(), filter);
        } catch (RequestException e) {
            return null;
        }
    }

    public static Address get(String address) {
        try {
            return new GeolocationService().getByAddress(address);
        } catch (RequestException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Address get(Location location) {
        try {
            return new GeolocationService().getByLocation(location);
        } catch (RequestException e) {
            e.printStackTrace();
            return null;
        }
    }
}
