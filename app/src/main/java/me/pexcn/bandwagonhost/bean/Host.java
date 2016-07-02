package me.pexcn.bandwagonhost.bean;

/**
 * Created by pexcn on 2016-06-30.
 */
public class Host {
    public int _id;
    public String title;
    public String veid;
    public String key;

    public Host() {
    }

    public Host(String title, String veid, String key) {
        this.title = title;
        this.veid = veid;
        this.key = key;
    }
}
