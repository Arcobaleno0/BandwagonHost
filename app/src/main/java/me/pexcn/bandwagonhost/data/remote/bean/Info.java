package me.pexcn.bandwagonhost.data.remote.bean;

import java.util.List;

import me.pexcn.bandwagonhost.base.BaseBean;

/**
 * Created by pexcn on 2017-05-05.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Info extends BaseBean {
    public String vm_type;
    public VzStatus vz_status;
    public VzQuota vz_quota;
    public String is_cpu_throttled;
    public int ssh_port;
    public String hostname;
    public String node_ip;
    public String node_alias;
    public String node_location;
    public boolean location_ipv6_ready;
    public String plan;
    public long plan_monthly_data;
    public int monthly_data_multiplier;
    public int plan_disk;
    public int plan_ram;
    public int plan_swap;
    public int plan_max_ipv6s;
    public String os;
    public String email;
    public long data_counter;
    public int data_next_reset;
    public boolean rdns_api_available;
    public boolean suspended;
    public List<String> ip_addresses;

    public static class VzStatus {
        public String status;
        public String hostname;
        public String load_average;
        public String nproc;
        public String nproc_b;
        public String kmemsize;
        public String kmemsize_b;
        public String privvmpages;
        public String privvmpages_b;
        public String oomguarpages;
        public String oomguarpages_b;
        public String numtcpsock;
        public String numtcpsock_b;
        public String numfile;
        public String numfile_b;
        public String swappages;
        public String swappages_b;
        public String physpages;
        public String physpages_l;
    }

    public static class VzQuota {
        public String occupied_kb;
        public String softlimit_kb;
        public String hardlimit_kb;
        public String occupied_inodes;
        public String softlimit_inodes;
        public String hardlimit_inodes;
    }
}
