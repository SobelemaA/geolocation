package org.alancesar.geolocation.test;

import java.io.IOException;
import java.util.List;

import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.address.Address;
import org.alancesar.geolocation.place.Place;
import org.alancesar.geolocation.place.PlaceDetail;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests {

    @Test
    public void findPlaces() throws IOException {
        Config.getInstance().setLanguage("pt");

        List<Place> places = Place.find("Shopping Dom Pedro Campinas");
        Assert.assertTrue(!places.isEmpty());

        Address address = Address.get(places.get(0).getFormattedAddress());
        Assert.assertNotNull(address.getFormattedAddress());
        Assert.assertTrue(!address.getFormattedAddress().isEmpty());

        List<PlaceDetail> placesDetail = address.findPlacesNearby();
        Assert.assertTrue(!placesDetail.isEmpty());

        Assert.assertEquals(address.getFormattedAddress(),
                "Av. Guilherme Campos, 500 - Santa Genebra, Campinas - SP, 13087-901, Brasil");
    }
}
