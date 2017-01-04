package org.alancesar.geolocation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {

    private static Config instance = new Config();
    private String language = "en";
    private Gson gson;
    private String key;

    private Config() {
        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        File keyFile = new File(getClass().getClassLoader().getResource("google_api_key.txt").getFile());
        
        if (keyFile != null && keyFile.exists()) {
            try {
                key = Files.readAllLines(keyFile.toPath()).get(0);
            } catch (IOException e) {
                key = null;
            }
        }
    }

    public static Config getInstance() {
        return instance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
