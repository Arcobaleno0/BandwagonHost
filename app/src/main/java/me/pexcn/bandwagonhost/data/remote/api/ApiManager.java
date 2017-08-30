package me.pexcn.bandwagonhost.data.remote.api;

/**
 * Created by pexcn on 2017-08-30.
 */
public class ApiManager {
    private static volatile ApiManager INSTANCE;

    private ApiManager() {
    }

    public static ApiManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ApiManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiManager();
                }
            }
        }
        return INSTANCE;
    }
}
