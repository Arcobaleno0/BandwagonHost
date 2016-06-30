package me.pexcn.bandwagonhost;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by pexcn on 2016-06-29.
 */
public class App extends Application {
    private static App sInstance;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        sInstance = this;
        sHandler = new Handler();
    }

    public static Context getContext() {
        return sInstance.getApplicationContext();
    }

    public static Handler getHandler() {
        return sHandler;
    }
}
