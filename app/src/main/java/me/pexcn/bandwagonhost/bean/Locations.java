package me.pexcn.bandwagonhost.bean;

import java.util.List;

/**
 * Created by pexcn on 2016-07-12.
 */
public class Locations {
    public int error;
    public String currentLocation;
    public Descriptions descriptions;
    public List<String> locations;

    public static class Descriptions {
        public String USCA_2;
        public String USCA_FMT;
        public String USAZ_2;
        public String USFL_2;
        public String EUNL_3;
    }
}
