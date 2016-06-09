package org.alancesar.geolocation.response;

public class RequestException extends Exception {
	private static final long serialVersionUID = 1L;

	public RequestException(RequestStatus status) {
        super(status.description);
    }
}
