package me.pexcn.bandwagonhost.base.presenter;

/**
 * Created by pexcn on 2016-06-30.
 */
public abstract class BasePresenter<V> implements IBasePresenter {
    // TODO: WeakReference
    protected V mView;

    public BasePresenter(V v) {
        this.mView = v;
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
