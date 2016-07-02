package me.pexcn.bandwagonhost.utils;

import me.pexcn.bandwagonhost.utils.common.OkHttpUtils;
import okhttp3.Callback;

/**
 * Created by pexcn on 2016-07-01.
 */
public class Network {
    private Network() {
    }

    public static String get(String url) {
        return OkHttpUtils.get(url);
    }

    public static void get(String url, Callback callback) {
        OkHttpUtils.get(url, callback);
    }
}
