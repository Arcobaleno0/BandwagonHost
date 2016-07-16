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

package me.pexcn.bandwagonhost.feature.manager.model;

import java.util.List;

import me.pexcn.bandwagonhost.bean.Profile;

/**
 * Created by pexcn on 2016-07-01.
 */
public interface IManagerModel {
    boolean isEmptyProfile();

    void insertProfile(Profile profile);

    void removeProfile(int id);

    List<Profile> getProfileList();

    List<Integer> getProfileIds();
}
