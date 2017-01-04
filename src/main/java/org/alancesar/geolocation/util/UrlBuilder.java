package org.alancesar.geolocation.util;

import java.io.IOException;
import java.net.URLEncoder;

import org.alancesar.geolocation.Location;

public class UrlBuilder {

    private StringBuilder url;

    public UrlBuilder(String mainUrl) {
        url = new StringBuilder(mainUrl + "?");
    }

    public UrlBuilder addParam(String name, String value) {
        url.append(name + "=" + encode(value) + "&");
        return this;
    }

    public UrlBuilder addParam(String name, Number number) {
        return addParam(name, String.valueOf(number));
    }

    public UrlBuilder addParam(String name, Location location) {
        url.append(name + "=" + location.getLat() + "," + location.getLng() + "&");
        return this;
    }

    public String get() {
        return url.substring(0, url.length() - 1);
    }

    private String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (IOException e) {
            return "";
        }
    }
}
