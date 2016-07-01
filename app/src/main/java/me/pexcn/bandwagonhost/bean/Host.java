package me.pexcn.bandwagonhost.bean;

/**
 * Created by pexcn on 2016-06-30.
 */
public class Host {
    private int _id;
    private String title;
    private String veid;
    private String key;

    public int getId() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getVeid() {
        return veid;
    }

    public String getKey() {
        return key;
    }

    public void setId(int id) {
        this._id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVeid(String veid) {
        this.veid = veid;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
