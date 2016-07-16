package me.pexcn.bandwagonhost.api;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Locations;
import me.pexcn.bandwagonhost.utils.common.LogUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by pexcn on 2016-07-16.
 */
public class LocationsServiceImpl implements LocationsService {
    @Override
    public Call<Locations> getLocations(@Query(Api.VEID) int veid, @Query(Api.KEY) String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LocationsService service = retrofit.create(LocationsService.class);
        Call<Locations> call = service.getLocations(veid, key);
        call.enqueue(new Callback<Locations>() {
            @Override
            public void onResponse(Call<Locations> call, Response<Locations> response) {
                List<String> locationses = response.body().locations;
                for (String locations : locationses) {
                    LogUtils.d(locations);
                }
            }

            @Override
            public void onFailure(Call<Locations> call, Throwable t) {

            }
        });
        return call;
    }
}
