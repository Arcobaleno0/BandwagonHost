package me.pexcn.bandwagonhost.data.remote.parser;

import android.icu.text.IDNA;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by pexcn on 2017-05-05.
 */
public class InfoDeserializer implements JsonDeserializer<IDNA.Info> {
    @Override
    public IDNA.Info deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return null;
    }
}
