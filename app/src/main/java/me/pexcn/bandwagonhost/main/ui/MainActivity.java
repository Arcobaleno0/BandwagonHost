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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.ui.BaseActivity;
import me.pexcn.bandwagonhost.bean.Host;
import me.pexcn.bandwagonhost.database.HostDatabase;
import me.pexcn.bandwagonhost.main.presenter.IMainPresenter;
import me.pexcn.bandwagonhost.main.presenter.MainPresenter;
import me.pexcn.bandwagonhost.utils.TextFilter;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView,
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private FloatingActionButton mFab;

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
    public void switchToFragment(Fragment fragment, String title, int item) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, fragment).commit();
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
        mNavigationView.setCheckedItem(item);
    }

    @SuppressLint("InflateParams")
    @Override
    public void showAddHostDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_addhost, null);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitle.setFilters(new InputFilter[]{new TextFilter(mTitle)});
        mVeid.setFilters(new InputFilter[]{new TextFilter(mVeid)});
        mKey.setFilters(new InputFilter[]{new TextFilter(mKey)});
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .setTitle("添加主机")
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
                    dialog.cancel();
                    return true;
                }
                return false;
            }
        });
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Host host = new Host();
                host.title = mTitle.getText().toString();
                host.veid = mVeid.getText().toString();
                host.key = mKey.getText().toString();
                if ("".equals(host.title) || "".equals(host.veid) || "".equals(host.key)) {
                    if ("".equals(host.title)) {
                        mTitle.setError("标题不能为空");
                    }
                    if ("".equals(host.veid)) {
                        mVeid.setError("VEID 不能为空");
                    }
                    if ("".equals(host.key)) {
                        mKey.setError("KEY 不能为空");
                    }
                } else {
                    mPresenter.addHost(host);
                    dialog.dismiss();
                }
            }
        });
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
        switch (id) {
            case R.id.nav_settings:
                // TODO
                break;
            case R.id.nav_about:
                showAboutDialog();
                break;
            case R.id.nav_hostmanager:
            case R.id.nav_migrate:
            case R.id.nav_extra:
                if (!item.isChecked()) {
                    mPresenter.switchToFragment(id);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showAddHostDialog();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (HostDatabase.getInstance(this).isOpen()) {
            HostDatabase.getInstance(this).close();
        }
        super.onDestroy();
    }
}
