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

package me.pexcn.bandwagonhost.bean.api;

import java.util.List;

/**
 * Created by pexcn on 2016-07-12.
 */
public class Locations {
    public int error;
    public String currentLocation;
    public Descriptions descriptions;
    public List<String> locations;

    public static class Descriptions {
        public String USCA_2;
        public String USCA_FMT;
        public String USAZ_2;
        public String USFL_2;
        public String EUNL_3;
    }

    // extra
    public String message;
    public String additionalErrorInfo;
}
