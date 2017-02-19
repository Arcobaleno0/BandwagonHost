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

package me.pexcn.bandwagonhost.api;

/**
 * Created by pexcn on 2016-06-29.
 */
public interface Api {
    String BASE_URL = "https://api.64clouds.com/v1/";
    String VEID = "veid";
    String KEY = "api_key";

    String MANAGER_START = BASE_URL + "start";
    String MANAGER_STOP = BASE_URL + "stop";
    String MANAGER_RESTART = BASE_URL + "restart";
    String MANAGER_KILL = BASE_URL + "kill";
    String MANAGER_GET_INFO = BASE_URL + "getServiceInfo";
    String MANAGER_GET_ALL_INFO = BASE_URL + "getLiveServiceInfo";
    String MANAGER_GET_AVAILABLE_OS = BASE_URL + "getAvailableOS";
    String MANAGER_REINSTALL_OS = BASE_URL + "reinstallOS";
    String MANAGER_RESET_ROOT_PASSWORD = BASE_URL + "resetRootPassword";
    String MANAGER_SET_HOSTNAME = BASE_URL + "setHostname";
    String MANAGER_SET_PTR = BASE_URL + "setPTR";
    String MANAGER_IPV6_ADD = BASE_URL + "ipv6/add";
    String MANAGER_IPV6_DELETE = BASE_URL + "ipv6/delete";

    @Deprecated
    String GET_USAGE_GRAPHS = BASE_URL + "getUsageGraphs";
    String GET_RAW_USAGE_STATS = BASE_URL + "getRawUsageStats";

    String BASIC_SHELL_CD = BASE_URL + "basicShell/cd";
    String BASIC_SHELL_EXEC = BASE_URL + "basicShell/exec";
    String SHELL_SCRIPT_EXEC = BASE_URL + "shellScript/exec";

    String UNSUSPEND = BASE_URL + "unsuspend";
    String GET_SUSPENSION_DETAILS = BASE_URL + "getSuspensionDetails";
    String GET_RATE_LIMIT_STATUS = BASE_URL + "getRateLimitStatus";

    String MIGRATE_START = BASE_URL + "migrate/start";
    String MIGRATE_GET_LOCATIONS = BASE_URL + "migrate/getLocations";
    String MIGRATE_CLONE_FROM_EXTERNAL_SERVER = BASE_URL + "cloneFromExternalServer";

    String SNAPSHOT_CREATE = BASE_URL + "snapshot/create";
    String SNAPSHOT_LIST = BASE_URL + "snapshot/list";
    String SNAPSHOT_DELETE = BASE_URL + "snapshot/delete";
    String SNAPSHOT_RESTORE = BASE_URL + "snapshot/restore";
    String SNAPSHOT_TOGGLE_STICKY = BASE_URL + "snapshot/toggleSticky";
    String SNAPSHOT_EXPORT = BASE_URL + "snapshot/export";
    String SNAPSHOT_IMPORT = BASE_URL + "snapshot/import";
}
