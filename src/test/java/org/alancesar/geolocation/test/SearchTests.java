package org.alancesar.geolocation.test;

import org.alancesar.geolocation.address.Address;
import org.alancesar.geolocation.place.Place;
import org.alancesar.geolocation.place.PlaceDetail;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SearchTests {
	
    @Test
    public void findPlaces() throws IOException {
        List<Place> places = Place.find("Shopping Dom Pedro Campinas");
        Address address = Address.get(places.get(0).getFormattedAddress());
        List<PlaceDetail> placesDetail = address.findPlacesNearby();
        
        System.out.println(address.getFormattedAddress());
        System.out.println();

        places.forEach(p -> {
            System.out.println(p.getFormattedAddress());
            System.out.println(p.getName());
            System.out.println();
        });

        placesDetail.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getVicinity());
            System.out.println();
        });
    }
}
