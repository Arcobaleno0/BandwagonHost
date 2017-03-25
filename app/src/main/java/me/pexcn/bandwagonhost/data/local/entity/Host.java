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

package me.pexcn.bandwagonhost.data.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pexcn on 2016-06-30.
 */
@DatabaseTable(tableName = "hosts")
public class Host implements Parcelable {
    @DatabaseField(columnName = "id", generatedId = true)
    public int id;
    @DatabaseField(columnName = "title", canBeNull = false)
    public String title;
    @DatabaseField(columnName = "veid", canBeNull = false)
    public String veid;
    @DatabaseField(columnName = "key", canBeNull = false)
    public String key;

    public Host() {
    }

    protected Host(Parcel in) {
        id = in.readInt();
        title = in.readString();
        veid = in.readString();
        key = in.readString();
    }

    public static final Creator<Host> CREATOR = new Creator<Host>() {
        @Override
        public Host createFromParcel(Parcel in) {
            return new Host(in);
        }

        @Override
        public Host[] newArray(int size) {
            return new Host[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(veid);
        dest.writeString(key);
    }

    @Override
    public String toString() {
        return "id => " + id + ", title => " + title + ", veid => " + veid + ", key => " + key;
    }
}
