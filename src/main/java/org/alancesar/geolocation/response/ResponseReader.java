package org.alancesar.geolocation.response;

import java.lang.reflect.Type;

import org.alancesar.geolocation.util.HtmlReader;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

public class ResponseReader<R> {

    public Response<R> getResponseFromUrl(String url, Type type, Deserializer<String, Response<R>> deserializer)
            throws RequestException {
        String json = new HtmlReader().read(url);
        Response<R> response = deserializer.deserialize(json, getTypeToken(TypeToken.of(type)).getType());

        if (response.ok())
            return response;

        throw new RequestException(response.getStatus());
    }

    static <R> TypeToken<Response<R>> getTypeToken(TypeToken<R> responseToken) {
        return new TypeToken<Response<R>>() {
            private static final long serialVersionUID = 1L;
        }.where(new TypeParameter<R>() {
        }, responseToken);
    }
}
