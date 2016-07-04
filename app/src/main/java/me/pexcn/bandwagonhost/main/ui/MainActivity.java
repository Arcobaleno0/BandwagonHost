package me.pexcn.bandwagonhost.main.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseActivity;
import me.pexcn.bandwagonhost.database.HostDatabase;
import me.pexcn.bandwagonhost.main.presenter.IMainPresenter;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    @Override
    protected void init() {
        // drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // nav
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        assert mNavigationView != null;
        mNavigationView.setNavigationItemSelectedListener(this);

        // prepare
        mPresenter.prepare();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected boolean hasParentActivity() {
        return false;
    }

    @Override
    public void setToolbarTitle(String title) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setNavCheckedItem(int id) {
        mNavigationView.setCheckedItem(id);
    }

    @Override
    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
    }

    @Override
    public void showAboutDialog() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_settings:

                break;
            case R.id.nav_about:
                showAboutDialog();
                break;
            case R.id.nav_hostmanager:
            case R.id.nav_migrate:
            case R.id.nav_extra:
                if (!item.isChecked()) {
                    mPresenter.switchModule(id);
                }
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO: remove it?
        if (HostDatabase.getInstance(this).isOpen()) {
            HostDatabase.getInstance(this).close();
        }
        super.onDestroy();
    }
}
