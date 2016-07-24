package me.pexcn.bandwagonhost.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pexcn on 2016-07-24.
 */
public class RetrofitHelper {
    private static Retrofit sInstance;

    public static <T> T createService(Class<T> cls) {
        if (sInstance == null) {
            sInstance = new Retrofit.Builder()
                    .baseUrl(me.pexcn.bandwagonhost.api.Api.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sInstance.create(cls);
    }
}
