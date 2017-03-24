package me.pexcn.bandwagonhost.manager.mvp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import me.pexcn.android.base.mvp.BaseActivity;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.app.Constants;
import me.pexcn.bandwagonhost.data.local.Host;
import me.pexcn.bandwagonhost.info.mvp.InfoFragment;
import me.pexcn.bandwagonhost.manager.ui.ManagerPagerAdapter;

/**
 * Created by pexcn on 2017-03-24.
 */
public class ManagerActivity extends BaseActivity<ManagerContract.Presenter>
        implements ManagerContract.View {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ManagerPagerAdapter mAdapter;
    private Host mHost;

    @Override
    protected ManagerContract.Presenter createPresenter() {
        return new ManagerPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    protected void init() {
        super.init();

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mAdapter = new ManagerPagerAdapter(getSupportFragmentManager());
        mHost = getIntent().getParcelableExtra(Constants.EXTRA_KEY_HOST);

        mAdapter.addFragment("VPS Info", InfoFragment.newInstance());

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        setTitle(mHost.title);
    }

    @Override
    protected boolean isSubActivity() {
        return true;
    }
}
