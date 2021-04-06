package com.example.myapplication.Modules.Artist;

import android.os.Bundle;

import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.List;

public class AllArtistPresenter extends Presenter<AllArtistContract.View, AllArtistContract.Interactor> implements AllArtistContract.Presenter {

    public AllArtistPresenter(AllArtistContract.View view, AllArtistContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getAllArtist() {
        getInteractor().getAllArtistAPI();
    }

    @Override
    public void tokenFailed(String message) {
        getView().showTokenFailed(message);
    }

    @Override
    public void onSuccess(String message) {
        getView().showSuccess(message);
    }

    @Override
    public void onError(String message) {
        getView().showError(message);
    }

    @Override
    public void onFinishGetAll(List<Artist> result) {
        if (result != null) {
            getView().loadAllArtist(result);
        }
    }
}
