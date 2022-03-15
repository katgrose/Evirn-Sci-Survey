package com.example.evirn_sci_survey.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    // from https://amit-bhandari.medium.com/storing-java-objects-other-than-primitive-types-in-room-database-11e45f4f6d22
    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static ArrayList<Integer> fromString(String value){
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
}
