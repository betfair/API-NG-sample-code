package com.betfair.aping.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Useful class to convert to and from Json
 * In this example we use Google gson
 */
public class JsonConverter {

    /**
     * We needed to override the adapter for the Date class as Betfair's API-NG requires all dates to be serialized in ISO8601 UTC
     * Just formatting the string to the ISO format does not adjust by the timezone on the Date instance during serialization.
     */
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new ISO8601DateTypeAdapter()).create();

    /** This method deserializes the specified Json into an object of the specified class.
     *
     */
    public static  <T> T convertFromJson(String toConvert,  Class<T>  clazz){
        return gson.fromJson(toConvert, clazz);
    }

    /** This method deserializes the specified Json into an object of the specified Type.
     *
     */
    public static  <T> T convertFromJson(String toConvert,  Type  typeOfT){
        return gson.fromJson(toConvert, typeOfT);
    }

    /**
     * This method serializes the specified object into its equivalent Json representation.
     */
    public static String convertToJson(Object toConvert){
        return gson.toJson(toConvert);

    }
}
