package org.alancesar.geolocation.address;

import java.util.List;

import org.alancesar.geolocation.LocationType;

public class AddressComponents {

    private String longName;
    private String shortName;
    private List<LocationType> types;

    private AddressComponents() {
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }

    public List<LocationType> getTypes() {
        return types;
    }
}
