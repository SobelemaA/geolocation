package org.alancesar.geolocation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class HtmlReader {

    public String read(String url) {
        try (InputStream inputStream = new URL(url).openStream()) {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}