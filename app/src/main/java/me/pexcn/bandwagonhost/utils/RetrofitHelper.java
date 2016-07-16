package me.pexcn.bandwagonhost.utils;

import me.pexcn.bandwagonhost.api.Api;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pexcn on 2016-07-16.
 */
public class RetrofitHelper {
    private static Retrofit mInstance;

    public static Retrofit getInstance() {
        if (mInstance == null) {
            mInstance = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mInstance;
    }
}
