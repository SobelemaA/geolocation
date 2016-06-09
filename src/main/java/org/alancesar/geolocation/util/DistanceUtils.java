package org.alancesar.geolocation.util;

import org.alancesar.geolocation.Location;

public class DistanceUtils {
    private static final int EARTH_RADIUS_KM = 6371;

    public static double distanceInKm(Location origin, Location destination) {
        return distanceInKm(origin.getLat(), origin.getLng(),
        		destination.getLat(), destination.getLng());
    }

    private static double distanceInKm(double latOrig, double lngOrig,
    		double latDest, double lngDest) {
        double destLatToRad = Math.toRadians(latOrig);
        double origLatToRad = Math.toRadians(latDest);
        double dLngToRad = Math.toRadians(lngDest - lngOrig);

        return Math.acos(Math.cos(destLatToRad) * Math.cos(origLatToRad) * Math.cos(dLngToRad)
                + Math.sin(destLatToRad) * Math.sin(origLatToRad)) * EARTH_RADIUS_KM;
    }
}
