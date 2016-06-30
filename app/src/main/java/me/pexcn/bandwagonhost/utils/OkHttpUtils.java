package me.pexcn.bandwagonhost.utils;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by pexcn on 2016-06-29.
 */
public class OkHttpUtils {
    private OkHttpUtils() {
    }

    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        String result;
        try {
            result = new OkHttpClient().newCall(request).execute().body().string();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        new OkHttpClient().newCall(request).enqueue(callback);
    }
}
