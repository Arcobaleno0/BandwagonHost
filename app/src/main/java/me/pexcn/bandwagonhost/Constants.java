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

package me.pexcn.bandwagonhost;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Constants {
    interface Database {
        String DB_NAME = "bandwagonhost.db";
        int DB_VERSION = 1;
        String TABLE_NAME_HOST = "host";
        String TABLE_COLUMN_HOST_ID = "id";
        String TABLE_COLUMN_HOST_TITLE = "title";
        String TABLE_COLUMN_HOST_VEID = "veid";
        String TABLE_COLUMN_HOST_KEY = "key";
    }

    interface Color {
        // TODO: 颜色小修改
        int WHITE = 0xFFFFFFFF;
        int BLUE = 0xFF0386F7;
        int GREEN = 0xFF0F9D58;
        int ORANGE = 0xFFFEB500;
        int RED = 0xFFFE4621;
    }
}
