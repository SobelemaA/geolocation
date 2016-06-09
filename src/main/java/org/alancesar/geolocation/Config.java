package org.alancesar.geolocation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config instance = new Config();

    private String key;

    private Config() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

            if (inputStream != null) {
                properties.load(inputStream);
                key = properties.getProperty("key");
            } else {
                throw new FileNotFoundException("Properties file 'config.properties' not found in the classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Config getInstance() {
        return instance;
    }

    public String getKey() {
        return key;
    }
}
