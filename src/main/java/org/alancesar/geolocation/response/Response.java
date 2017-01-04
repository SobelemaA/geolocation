package org.alancesar.geolocation.response;

import java.util.List;

public class Response<T> {

    private List<T> results;
    private String nextPageToken;
    private RequestStatus status;

    public List<T> getResults() throws RequestException {
        return results;
    }

    public boolean hasMore() {
        return nextPageToken != null;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public boolean isEmpty() {
        return results == null || results.isEmpty();
    }

    public boolean ok() {
        return status != null && status == RequestStatus.OK;
    }
}
