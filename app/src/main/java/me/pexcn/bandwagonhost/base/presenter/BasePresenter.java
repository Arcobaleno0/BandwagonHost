package me.pexcn.bandwagonhost.base.presenter;

/**
 * Created by pexcn on 2016-06-30.
 */
public abstract class BasePresenter<V, M> implements IBasePresenter {
    // TODO: WeakReference
    protected V mView;
    protected M mModel;

    public BasePresenter(V v) {
        this.mView = v;
        this.mModel = getModel();
    }

    abstract protected M getModel();

    @Override
    public void destroy() {
        mView = null;
    }
}
