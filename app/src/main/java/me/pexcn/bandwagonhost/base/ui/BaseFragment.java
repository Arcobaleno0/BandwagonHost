package me.pexcn.bandwagonhost.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.base.presenter.IBasePresenter;

/**
 * Created by pexcn on 2016-06-29.
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment {
    protected Activity mActivity;
    protected View mRootView;
    protected P mPresenter;

    // TODO: 抽取getArgs
    // TODO: initData位置

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mPresenter = getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        initView(mRootView, savedInstanceState);
        initData();
        return mRootView;
    }

    // TODO: WeakReference
    @Override
    public void onDestroyView() {
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroyView();
    }

    abstract protected int getLayoutId();

    abstract protected P getPresenter();

    abstract protected void initView(View view, @Nullable Bundle savedInstanceState);

    abstract protected void initData();

    // TODO: 移动到接口
    abstract protected void showTips(String msg, int duration);
}
