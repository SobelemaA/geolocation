package org.alancesar.geolocation.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.place.Place;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.response.Response;
import org.alancesar.geolocation.response.ResponseReader;
import org.alancesar.geolocation.util.UrlBuilder;

import java.util.List;

public class PlaceService implements Service<List<Place>> {
    private static final String MAIN_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
    private static final String KEY = Config.getInstance().getKey();
    private Response<Place> response;

    public List<Place> findPlaces(String query) throws RequestException {
    	String url = new UrlBuilder(MAIN_URL)
    			.addParam("query", query)
    			.addParam("key", KEY)
    			.get();
    	return get(url);
    }
    
    public List<Place> get(String url) throws RequestException {
    	response = new ResponseReader<Place>()
    			.getResponseFromUrl(url, Place.class, (json, type)-> new GsonBuilder()
    					.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    					.create()
    					.fromJson(json, type));
    	
    	return response.getResults();
    }
    
    public List<Place> getMore() throws RequestException {
    	if (!response.hasMore())
    		return null;
    	
    	String nextPageToken = response.getNextPageToken();
    	String url = new UrlBuilder(MAIN_URL)
    			.addParam("pageToken", nextPageToken)
    			.addParam("key", KEY)
    			.get();
    	
    	return get(url);
    }
    
    public boolean hasMore() {
    	return response.hasMore();
    }
}
