package org.alancesar.geolocation.service;

import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.Location;
import org.alancesar.geolocation.address.Address;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.response.Response;
import org.alancesar.geolocation.response.ResponseReader;
import org.alancesar.geolocation.util.UrlBuilder;

public class GeolocationService implements Service<Address> {

    private static final String MAIN_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private Response<Address> response;

    public Address getByAddress(String address) throws RequestException {
        String url = new UrlBuilder(MAIN_URL).addParam("address", address)
                .addParam("language", Config.getInstance().getLanguage()).get();
        return get(url);
    }

    public Address getByLocation(Location location) throws RequestException {
        String url = new UrlBuilder(MAIN_URL).addParam("latlng", location)
                .addParam("language", Config.getInstance().getLanguage()).get();
        return get(url);
    }

    public Address get(String url) throws RequestException {
        response = new ResponseReader<Address>().getResponseFromUrl(url, Address.class,
                (json, type) -> Config.getInstance().getGson().fromJson(json, type));

        if (response.isEmpty()) {
            return null;
        }

        return response.getResults().get(0);
    }

    public Address getMore() throws RequestException {
        if (!response.hasMore()) {
            return null;
        }

        String nextPageToken = response.getNextPageToken();
        String url = new UrlBuilder(MAIN_URL).addParam("pageToken", nextPageToken)
                .addParam("language", Config.getInstance().getLanguage()).get();

        return get(url);
    }

    public boolean hasMore() {
        return response.hasMore();
    }
}
