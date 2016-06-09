package org.alancesar.geolocation.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import org.alancesar.geolocation.Config;
import org.alancesar.geolocation.place.PlaceDetail;
import org.alancesar.geolocation.Location;
import org.alancesar.geolocation.LocationType;
import org.alancesar.geolocation.response.RequestException;
import org.alancesar.geolocation.response.Response;
import org.alancesar.geolocation.response.ResponseReader;
import org.alancesar.geolocation.util.UrlBuilder;

import java.util.List;

public class PlaceDetailService implements Service<List<PlaceDetail>> {
    private static final String MAIN_URL ="https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    private static final String KEY = Config.getInstance().getKey();
    private static final int DEFAULT_RANGE = 250;

    private Response<PlaceDetail> response;
    
    public List<PlaceDetail> findPlacesNearby(Location location, int range, LocationType... filter)
    		throws RequestException {
        String url = new UrlBuilder(MAIN_URL)
        		.addParam("location", location)
        		.addParam("types", createFilter(filter))
                .addParam("radius", range)
                .addParam("key", KEY).get();
        
        return get(url);
    }
    
    public List<PlaceDetail> findPlacesNearby(Location location, LocationType... filter)
    		throws RequestException {
    	return findPlacesNearby(location, DEFAULT_RANGE, filter);
    }
    
    public List<PlaceDetail> get(String url) throws RequestException {
    	response = new ResponseReader<PlaceDetail>()
    			.getResponseFromUrl(url, PlaceDetail.class, (json, type)-> new GsonBuilder()
    					.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    					.create()
    					.fromJson(json, type));
    	
    	return response.getResults();
    }
    
    public List<PlaceDetail> getMore() throws RequestException {
    	if (!response.hasMore())
    		return null;
    	
    	String nextPageToken = response.getNextPageToken();
    	String url = new UrlBuilder(MAIN_URL)
    			.addParam("pagetoken", nextPageToken)
    			.addParam("key", KEY)
    			.get();
    	
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
