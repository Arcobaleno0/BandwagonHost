package me.pexcn.bandwagonhost.main.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseActivity;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;
import me.pexcn.bandwagonhost.database.IDatabase;
import me.pexcn.bandwagonhost.main.presenter.IMainPresenter;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;
import me.pexcn.bandwagonhost.tools.TextFilter;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,
        DialogInterface.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;
    private IDatabase<Host> mDatabase;

    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;

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

        // fab
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        assert mFab != null;
        mFab.setOnClickListener(this);

        // database
        mDatabase = HostDatabase.getInstance(this);

        // prepare
        mPresenter.switchToFragment(R.id.nav_host);
        mNavigationView.setCheckedItem(R.id.nav_host);
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
    public void switchToFragment(Fragment fragment, String title) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
    }

    @SuppressLint("InflateParams")
    @Override
    public void showAddHostDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_addhost, null);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setTitle("添加主机");
        builder.setPositiveButton("确定", this);
        builder.setNegativeButton("取消", this);
        builder.show();
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});
    }

    @Override
    public void showTips(String msg, int duration) {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showAddHostDialog();
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                mPresenter.addHost(mTitle.getText().toString(), mVeid.getText().toString(), mKey.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mDatabase.close();
        super.onDestroy();
    }
}
