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
 */

package me.pexcn.bandwagonhost.data.remote;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Api {
    // Base
    String BASE_URL = "https://api.64clouds.com/v1/";
    String VEID = "veid";
    String KEY = "api_key";

    // APIs
    String START = BASE_URL + "start";
    String STOP = BASE_URL + "stop";
    String RESTART = BASE_URL + "restart";
    String KILL = BASE_URL + "kill";

    String GET_INFO = BASE_URL + "getServiceInfo";
    String GET_ALL_INFO = BASE_URL + "getLiveServiceInfo";

    String GET_AVAILABLE_OS = BASE_URL + "getAvailableOS";
    String REINSTALL_OS = BASE_URL + "reinstallOS";
    String RESET_ROOT_PASSWORD = BASE_URL + "resetRootPassword";

    String GET_USAGE_STATS = BASE_URL + "getRawUsageStats";
    String SET_HOSTNAME = BASE_URL + "setHostname";
    String SET_PTR = BASE_URL + "setPTR";

    String ADD_IPV6 = BASE_URL + "ipv6/add";
    String DELETE_IPV6 = BASE_URL + "ipv6/delete";

    String START_MIGRATE = BASE_URL + "migrate/start";
    String GET_LOCATIONS = BASE_URL + "migrate/getLocations";

    // Actions
    // TODO
}
