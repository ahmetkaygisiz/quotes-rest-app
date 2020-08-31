package com.akua.utils;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Collection;

public class Jsons {

    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .create();

    public static Gson provider(){
        return gson;
    }

    public static String objectToJson(Object obj){
        return provider().toJson(obj);
    }

    public static JsonArray readJsonFile(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        return provider().fromJson(bufferedReader, JsonArray.class);
    }

    public static <T> Collection<T> asCollection(JsonArray json, Type typeOf) {
        return gson.fromJson(json, typeOf);
    }
}
