package org.alancesar.geolocation.service;

import org.alancesar.geolocation.response.RequestException;

public interface Service<T> {
	T get(String url) throws RequestException;
	T getMore() throws RequestException;
	boolean hasMore();
}
