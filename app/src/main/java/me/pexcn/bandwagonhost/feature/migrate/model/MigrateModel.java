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

    private String IS_SELECTED_PROFILE = Constants.PREFERENCE.KEY_IS_SELECTED_PROFILE;
    private String CURRENT_PROFILE_KEY = Constants.PREFERENCE.KEY_CURRENT_PROFILE;

    public MigrateModel(Context context) {
        this.mContext = context;
        this.mDatabaseManager = DatabaseManager.getInstance(mContext);
    }

    @Override
    public boolean isEmpty() {
        return mDatabaseManager.isEmpty();
    }

    @Override
    public void fetchLocations(Profile profile, final OnFetchLocationsListener listener) {

    }

    @Override
    public String[] getProfileTitle() {
        List<Profile> profiles = mDatabaseManager.queryAll();
        String[] titles = new String[profiles.size()];
        for (int i = 0; i < profiles.size(); i++) {
            titles[i] = profiles.get(i).title;
        }
        return titles;
    }

    @Override
    public boolean isSelectedProfile() {
        return PreferencesUtils.getBoolean(IS_SELECTED_PROFILE, false);
    }

    @Override
    public void selectProfile(Profile profile) {
        PreferencesUtils.setInt(CURRENT_PROFILE_KEY, profile._id);
        PreferencesUtils.setBoolean(IS_SELECTED_PROFILE, true);
    }
}
