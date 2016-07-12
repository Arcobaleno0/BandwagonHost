/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen (pexcn) <pexcn97@gmail.com>
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

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Api {
    String BASE_URL = "https://api.64clouds.com/v1";

    interface PROFILE {
        String GET_INFO = BASE_URL + "/API_getServiceInfo";

        String RESTART_VPS = BASE_URL + "/restart";
        String SET_PTR_RECORD = BASE_URL + "/setPTR";
    }

    interface MIGRATE {

    }

    interface EXTRA {
        String CREATE_SNAPSHOT = BASE_URL + "/snapshot/create";
    }
}
