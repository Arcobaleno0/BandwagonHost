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

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by pexcn on 2016-06-29.
 */
public class App extends Application {
    private static App sInstance;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sHandler = new Handler();
    }

    public static Context getContext() {
        return sInstance.getApplicationContext();
    }

    public static Handler getHandler() {
        return sHandler;
    }
}
