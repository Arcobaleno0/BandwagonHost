/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package me.pexcn.bandwagonhost.api;

import me.pexcn.bandwagonhost.bean.Locations;
import me.pexcn.bandwagonhost.bean.MigrateResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pexcn on 2016-07-14.
 */
public interface RetrofitApi {
    interface MIGRATE {
        interface LOCATIONS {
            @GET(Api.MIGRATE.GET_LOCATIONS)
            Call<Locations> getLocations(@Query(Api.VEID) int veid, @Query(Api.KEY) String key);
        }

        interface START {
            @GET(Api.MIGRATE.START)
            Call<MigrateResult> startMigrate(@Query(Api.VEID) int veid, @Query(Api.KEY) String key, @Query(Api.PARAMS.LOCATION) String location);
        }
    }
}
