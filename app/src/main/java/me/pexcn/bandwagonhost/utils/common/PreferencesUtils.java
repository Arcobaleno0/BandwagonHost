package me.pexcn.bandwagonhost.utils.common;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import me.pexcn.bandwagonhost.App;

/**
 * Created by pexcn on 2016-06-29.
 */
@SuppressWarnings("unused")
public class PreferencesUtils {
    public static final SharedPreferences SHARED_PREFERENCES = PreferenceManager.getDefaultSharedPreferences(App.getContext());

    private PreferencesUtils() {
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return SHARED_PREFERENCES.getBoolean(key, defValue);
    }

    public static void setBoolean(String key, boolean value) {
        SHARED_PREFERENCES.edit().putBoolean(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        return SHARED_PREFERENCES.getString(key, defValue);
    }

    public static void setString(String key, String value) {
        SHARED_PREFERENCES.edit().putString(key, value).apply();
    }

    public static int getInt(String key, int defValue) {
        return SHARED_PREFERENCES.getInt(key, defValue);
    }

    public static void setInt(String key, int value) {
        SHARED_PREFERENCES.edit().putInt(key, value).apply();
    }

    public static long getLong(String key, long defValue) {
        return SHARED_PREFERENCES.getLong(key, defValue);
    }

    public static void setLong(String key, long value) {
        SHARED_PREFERENCES.edit().putLong(key, value).apply();
    }

    public static float getFloat(String key, float defValue) {
        return SHARED_PREFERENCES.getFloat(key, defValue);
    }

    public static void setFloat(String key, float value) {
        SHARED_PREFERENCES.edit().putFloat(key, value).apply();
    }
}
