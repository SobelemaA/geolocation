package org.alancesar.geolocation.response;

public enum RequestStatus {
    OK(1 ,"Ok"),
    ZERO_RESULTS(2, "Zero Results"),
    OVER_QUERY_LIMIT(3, "Over Query Limit"),
    REQUEST_DENIED(4, "Request Denied"),
    INVALID_REQUEST(5, "Invalid Request"),
    UNKNOWN_ERROR(6, "Unknown Error");

    public int id;
    public String description;

    RequestStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
