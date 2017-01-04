package org.alancesar.geolocation.service;

import java.util.List;

import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.Location;
import org.alancesar.geolocation.LocationType;
import org.alancesar.geolocation.place.PlaceDetail;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.response.RequestStatus;
import org.alancesar.geolocation.response.Response;
import org.alancesar.geolocation.response.ResponseReader;
import org.alancesar.geolocation.util.UrlBuilder;

public class PlaceDetailService implements Service<List<PlaceDetail>> {

    private static final String MAIN_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final int DEFAULT_RANGE = 250;
    private Response<PlaceDetail> response;

    public List<PlaceDetail> findPlacesNearby(Location location, int range, LocationType... filter)
            throws RequestException {
        String key = Config.getInstance().getKey();

        if (key == null || key.trim().isEmpty()) {
            throw new RequestException(RequestStatus.INVALID_KEY);
        }

        String url = new UrlBuilder(MAIN_URL).addParam("location", location).addParam("types", createFilter(filter))
                .addParam("radius", range).addParam("key", key).addParam("language", Config.getInstance().getLanguage())
                .get();

        return get(url);
    }

    public List<PlaceDetail> findPlacesNearby(Location location, LocationType... filter) throws RequestException {
        return findPlacesNearby(location, DEFAULT_RANGE, filter);
    }

    public List<PlaceDetail> get(String url) throws RequestException {
        response = new ResponseReader<PlaceDetail>().getResponseFromUrl(url, PlaceDetail.class,
                (json, type) -> Config.getInstance().getGson().fromJson(json, type));

        return response.getResults();
    }

    public List<PlaceDetail> getMore() throws RequestException {
        String key = Config.getInstance().getKey();

        if (key == null || key.trim().isEmpty()) {
            throw new RequestException(RequestStatus.INVALID_KEY);
        }

        if (!response.hasMore()) {
            return null;
        }

        String nextPageToken = response.getNextPageToken();
        String url = new UrlBuilder(MAIN_URL).addParam("pagetoken", nextPageToken).addParam("key", key)
                .addParam("language", Config.getInstance().getLanguage()).get();

        return get(url);
    }

    public boolean hasMore() {
        return response.hasMore();
    }

    private String createFilter(LocationType... types) {
        StringBuilder filter = new StringBuilder();

        for (LocationType type : types) {
            filter.append(type + "|");
        }

        if (!filter.toString().isEmpty()) {
            filter.delete(filter.length() - 1, filter.length());
        }

        return filter.toString();
    }
}
