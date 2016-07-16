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

import java.io.IOException;
import java.util.List;

import me.pexcn.bandwagonhost.Constants;
import me.pexcn.bandwagonhost.api.Api;
import me.pexcn.bandwagonhost.feature.manager.bean.Profile;
import me.pexcn.bandwagonhost.database.ProfileDatabase;
import me.pexcn.bandwagonhost.utils.HttpUtils;
import me.pexcn.bandwagonhost.utils.common.PreferencesUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by pexcn on 2016-07-03.
 */
public class MigrateModel implements IMigrateModel {
    private Context mContext;
    private ProfileDatabase mDatabase;

    private String IS_SELECTED_PROFILE = Constants.MIGRATE.PREFERENCES.KEY_IS_SELECTED_PROFILE;
    private String CURRENT_PROFILE_KEY = Constants.MIGRATE.PREFERENCES.KEY_CURRENT_PROFILE;

    public MigrateModel(Context context) {
        this.mContext = context;
        this.mDatabase = ProfileDatabase.getInstance(mContext);
    }

    @Override
    public boolean isEmpty() {
        return mDatabase.isEmpty();
    }

    @Override
    public void fetchLocations(Profile profile, final OnFetchLocationsListener listener) {
        String url = Api.MIGRATE.GET_LOCATTIONS + "?veid=" + profile.veid + "&api_key=" + profile.key;
        HttpUtils.get(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                listener.OnSuccess(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                listener.OnFailure(e);
            }
        });
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
