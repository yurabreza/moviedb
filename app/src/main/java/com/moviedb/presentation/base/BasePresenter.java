package com.moviedb.presentation.base;

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    public void onAttach(T view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }
}
