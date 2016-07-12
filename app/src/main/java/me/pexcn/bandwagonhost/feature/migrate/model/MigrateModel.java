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

package me.pexcn.bandwagonhost.feature.migrate.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Profile;
import me.pexcn.bandwagonhost.database.ProfileDatabase;

/**
 * Created by pexcn on 2016-07-03.
 */
public class MigrateModel implements IMigrateModel {
    private Context mContext;
    private ProfileDatabase mDatabase;

    public MigrateModel(Context context) {
        this.mContext = context;
        this.mDatabase = ProfileDatabase.getInstance(mContext);
    }

    @Override
    public String[] getProfileTitle() {
        List<Profile> profiles = mDatabase.queryAll();
        String[] titles = new String[profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            titles[i] = profiles.get(i).title;
        }
        return titles;
    }
}
