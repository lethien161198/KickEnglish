package com.example.myapplication.Common.basemvp;

import com.example.myapplication.Modules.API.RetrofitClient;

public abstract class Interactor<P extends MVP.Presenter> implements MVP.Interactor<P> {
    private P presenter;
    public <S> S createService(Class<S> serviceClass){
        return RetrofitClient.createService(serviceClass);
    }
    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }/**/
}
