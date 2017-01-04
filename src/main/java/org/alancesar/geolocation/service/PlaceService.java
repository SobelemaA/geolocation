package org.alancesar.geolocation.service;

import java.util.List;

import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.place.Place;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.response.RequestStatus;
import org.alancesar.geolocation.response.Response;
import org.alancesar.geolocation.response.ResponseReader;
import org.alancesar.geolocation.util.UrlBuilder;

public class PlaceService implements Service<List<Place>> {

    private static final String MAIN_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
    private Response<Place> response;

    public List<Place> findPlaces(String query) throws RequestException {
        String key = Config.getInstance().getKey();

        if (key == null || key.trim().isEmpty()) {
            throw new RequestException(RequestStatus.INVALID_KEY);
        }

        String url = new UrlBuilder(MAIN_URL).addParam("query", query).addParam("key", key)
                .addParam("language", Config.getInstance().getLanguage()).get();
        return get(url);
    }

    public List<Place> get(String url) throws RequestException {
        response = new ResponseReader<Place>().getResponseFromUrl(url, Place.class,
                (json, type) -> Config.getInstance().getGson().fromJson(json, type));

        return response.getResults();
    }

    public List<Place> getMore() throws RequestException {
        String key = Config.getInstance().getKey();

        if (key == null || key.trim().isEmpty()) {
            throw new RequestException(RequestStatus.INVALID_KEY);
        }

        if (!response.hasMore()) {
            return null;
        }

        String nextPageToken = response.getNextPageToken();
        String url = new UrlBuilder(MAIN_URL).addParam("pageToken", nextPageToken).addParam("key", key)
                .addParam("language", Config.getInstance().getLanguage()).get();

        return get(url);
    }

    public boolean hasMore() {
        return response.hasMore();
    }
}
