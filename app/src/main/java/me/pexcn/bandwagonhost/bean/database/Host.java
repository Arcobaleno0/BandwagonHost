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

package me.pexcn.bandwagonhost.bean.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import me.pexcn.bandwagonhost.Constants;

/**
 * Created by pexcn on 2016-06-30.
 */
@DatabaseTable(tableName = Constants.Database.TABLE_NAME_HOST)
public class Host {
    @DatabaseField(columnName = Constants.Database.TABLE_COLUMN_HOST_ID, generatedId = true)
    public int id;
    @DatabaseField(columnName = Constants.Database.TABLE_COLUMN_HOST_TITLE, canBeNull = false)
    public String title;
    @DatabaseField(columnName = Constants.Database.TABLE_COLUMN_HOST_VEID, canBeNull = false)
    public String veid;
    @DatabaseField(columnName = Constants.Database.TABLE_COLUMN_HOST_KEY, canBeNull = false)
    public String key;
}
