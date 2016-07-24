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

package me.pexcn.bandwagonhost.feature.migrate.model;

import android.content.Context;

import java.util.List;

import me.pexcn.bandwagonhost.Constants;
import me.pexcn.bandwagonhost.database.DatabaseManager;
import me.pexcn.bandwagonhost.bean.Profile;
import me.pexcn.bandwagonhost.utils.common.PreferencesUtils;

/**
 * Created by pexcn on 2016-07-03.
 */
public class MigrateModel implements IMigrateModel {
    private Context mContext;
    private DatabaseManager mDatabaseManager;

    private String IS_PROFILE_SELECTED = Constants.Preferences.KEY_IS_PROFILE_SELECTED;
    private String CURRENT_PROFILE = Constants.Preferences.KEY_CURRENT_PROFILE;

    public MigrateModel(Context context) {
        this.mContext = context;
        this.mDatabaseManager = DatabaseManager.getInstance(mContext);
    }

    @Override
    public boolean isProfileEmpty() {
        return mDatabaseManager.isEmpty();
    }

    @Override
    public String[] getProfilesTitle() {
        List<Profile> profiles = mDatabaseManager.queryAll();
        int size = profiles.size();
        String[] titles = new String[size];
        for (int i = 0; i < size; i++) {
            titles[i] = profiles.get(i).title;
        }
        return titles;
    }

    @Override
    public boolean isProfileSelected() {
        return PreferencesUtils.getBoolean(IS_PROFILE_SELECTED, false);
    }

    @Override
    public void selectProfile(Profile profile) {
        PreferencesUtils.setInt(CURRENT_PROFILE, profile._id);
        PreferencesUtils.setBoolean(IS_PROFILE_SELECTED, true);
    }
}
