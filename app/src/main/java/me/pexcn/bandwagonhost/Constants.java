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

package me.pexcn.bandwagonhost;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Constants {
    String UNKNOWN = "unknown";

    interface PROFILE {
        interface DATABASE {
            String DATABASE_NAME = "profile.db";
            int DATABASE_VERSION = 1;
            String TABLE_NAME = "profile";
            String TABLE_COLUMN_ID = "_id";
            String TABLE_COLUMN_TITLE = "title";
            String TABLE_COLUMN_VEID = "veid";
            String TABLE_COLUMN_KEY = "key";
        }
    }

    interface MIGRATE {
        interface PREFERENCES {
            String KEY_IS_SELECTED_PROFILE = "IsSelectedProfile";
            String KEY_CURRENT_PROFILE = "CurrentProfile";
        }
    }
}
