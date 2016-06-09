package org.alancesar.geolocation;

public class Geometry {
    private Location location;
    private LocationType locationType;
    private Viewport viewport;

    public Location getLocation() {
        return location;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public enum LocationType {
        ROOFTOP(1, "Rooftop"),
        RANGE_INTERPOLATED(2, "Range Interpolated"),
        GEOMETRIC_CENTER(3, "Geometric Center"),
        APPROXIMATE(4, "Approximate");

        public int id;
        public String name;

        LocationType(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class Viewport {
        private Location northeast;
        private Location southwest;

        public Location getNortheast() {
            return northeast;
        }

        public Location getSouthwest() {
            return southwest;
        }
    }
}
