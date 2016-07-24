package me.pexcn.bandwagonhost.bean.api;

import java.util.List;

/**
 * Created by pexcn on 2016-07-16.
 */
public class MigrateResult {
    public int error;
    public String notificationEmail;
    public List<String> newIps;

    // extra
    public String message;
}
