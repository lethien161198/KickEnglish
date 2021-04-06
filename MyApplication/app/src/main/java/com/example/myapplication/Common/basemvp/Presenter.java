package com.example.myapplication.Common.basemvp;

import android.os.Bundle;

public abstract class Presenter<V extends MVP.View, I extends MVP.Interactor> implements MVP.Presenter<V, I> {
    private final V view;
    private final I interactor;

    public Presenter(V view, I interactor, Bundle parameters) {
        this.view = view;
        this.view.setPresenter(this);
        this.view.setParameters(parameters);
        this.interactor = interactor;
        this.interactor.setPresenter(this);
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public I getInteractor() {
        return interactor;
    }

    @Override
    public void tokenFailed(String message) {

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onError(String message) {

    }
}
