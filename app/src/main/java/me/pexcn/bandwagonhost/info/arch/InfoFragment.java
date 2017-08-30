package me.pexcn.bandwagonhost.info.arch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import me.pexcn.android.base.arch.mvp.BaseFragment;
import me.pexcn.android.utils.io.LogUtils;
import me.pexcn.bandwagonhost.R;

/**
 * Created by pexcn on 2017-03-24.
 */
public class InfoFragment extends BaseFragment<InfoContract.Presenter> implements InfoContract.View {
    public static Fragment newInstance() {
        return new InfoFragment();
    }

    @Override
    protected InfoContract.Presenter createPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
//        public HashMap<String, String> data = new HashMap();
        Gson gson = new Gson();
        Type typeHashMap = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> data = gson.fromJson("{ vm_type: \"ovz\", vz_status: { status: \"running\", hostname: \"alpha\", load_average: \"0.00/0.00/0.00\", nproc: \"10\", nproc_b: \"80\", kmemsize: \"6304851\", kmemsize_b: \"37748736\", privvmpages: \"5313\", privvmpages_b: \"73728\", oomguarpages: \"1420\", oomguarpages_b: \"18432\", numtcpsock: \"7\", numtcpsock_b: \"570\", numfile: \"136\", numfile_b: \"2880\", swappages: \"792\", swappages_b: \"4096\", physpages: \"3823\", physpages_l: \"18432\" }, vz_quota: { occupied_kb: \"247240\", softlimit_kb: \"1887436\", hardlimit_kb: \"1981440\", occupied_inodes: \"12041\", softlimit_inodes: \"1843200\", hardlimit_inodes: \"1935360\" }, is_cpu_throttled: \"\", ssh_port: 2222, hostname: \"Alpha\", node_ip: \"98.142.136.15\", node_alias: \"v1105\", node_location: \"US, California\", location_ipv6_ready: true, plan: \"micro64\", plan_monthly_data: 107374182400, plan_disk: 1932735283, plan_ram: 75497472, plan_swap: 16777216, plan_max_ipv6s: 3, os: \"debian-7.0-x86_64-minimal\", email: \"pexcn@live.cn\", data_counter: 10161031, data_next_reset: 1495425600, ip_addresses: [ \"98.142.143.141\", \"2607:8700:112:ec46::\" ], rdns_api_available: true, suspended: false, error: 0 }", typeHashMap);

        Set<Map.Entry<String, String>> entrySet = data.entrySet();

        Iterator iterator = entrySet.iterator();

        for (int j = 0; j < entrySet.size(); j++) {
            try {
                Map.Entry entry = (Map.Entry) iterator.next();
                String key = entry.getKey().toString();
                String value = entry.getValue().toString();
                LogUtils.d("key = " + key + ", value = " + value);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
            break;
        }
    }
}
