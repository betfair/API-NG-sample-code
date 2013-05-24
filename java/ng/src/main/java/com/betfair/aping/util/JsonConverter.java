package com.betfair.aping.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;

/**
 * Useful class to convert to and from Json
 * In this example we use Google gson
 */
public class JsonConverter {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    /** This method deserializes the specified Json into an object of the specified class.
     *
     */
    public static  <T> T convertFromJson(String toConvert,  Class<T>  clazz){
        return gson.fromJson(toConvert, clazz);
    }

    /** This method deserializes the specified Json into an object of the specified class.
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
