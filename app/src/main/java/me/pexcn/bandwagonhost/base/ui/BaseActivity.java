package me.pexcn.bandwagonhost.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (hasParentActivity()) {
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mPresenter = getPresenter();

        init();
    }

    abstract protected int getLayoutId();

    abstract protected P getPresenter();

    abstract protected boolean hasParentActivity();

    abstract protected void init();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroy();
    }
}
