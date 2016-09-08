package org.alancesar.geolocation.response;

import java.lang.reflect.Type;

@FunctionalInterface
public interface Deserializer<T, R> {
	R deserialize(T input, Type type);
}
