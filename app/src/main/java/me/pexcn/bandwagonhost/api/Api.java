package me.pexcn.bandwagonhost.api;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Api {
    String BASE_URL = "https://api.64clouds.com/v1";

    // Get info
    String GET_INFO = BASE_URL + "/API_getServiceInfo";

    // Create snapshot
    String CREATE_SNAPSHOT = BASE_URL + "/snapshot/create";

    // Restart VPS
    String RESTART_VPS = BASE_URL + "/restart";

    // Set PTR record
    String SET_PTR_RECORD = BASE_URL + "/setPTR";
}
