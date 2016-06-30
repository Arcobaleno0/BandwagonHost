package me.pexcn.bandwagonhost.main.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseActivity;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements IMainView,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private MainPresenter mPresenter;

    @Override
    protected void init() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        assert mNavigationView != null;
        mNavigationView.setNavigationItemSelectedListener(this);

        mPresenter = new MainPresenter(this);
        mPresenter.switchToFragment(R.id.nav_host);
        mNavigationView.setCheckedItem(R.id.nav_host);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean hasParentActivity() {
        return false;
    }

    @Override
    public void switchToFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void showSnackbarMessage(String msg, int duration) {
        CoordinatorLayout coordinator = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        assert coordinator != null;
        Snackbar.make(coordinator, msg, duration).setAction("确定", this).show();
    }

    @Override
    public void showAboutDialog() {
        // TODO
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings) {
            // TODO
        } else if (id == R.id.nav_about) {
            showAboutDialog();
        } else {
            mPresenter.switchToFragment(id);
            item.setChecked(true);
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
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        // ignore
    }
}
