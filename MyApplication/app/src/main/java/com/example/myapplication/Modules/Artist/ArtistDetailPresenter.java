package com.example.myapplication.Modules.Artist;

import android.os.Bundle;

import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.List;

public class ArtistDetailPresenter extends Presenter<ArtistDetailContract.View, ArtistDetailContract.Interactor> implements ArtistDetailContract.Presenter {
    public String message;

    public ArtistDetailPresenter(ArtistDetailContract.View view, ArtistDetailContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    public void onGetArtist(int id) {
        getInteractor().getArtist(id);
    }

    @Override
    public void onGetMediaArtist(int id) {
        getInteractor().getMediaArtist(id);
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
    public void onFinishArtist(Artist result) {
        if (result != null) {

            getView().loadDetail(result);

        }
    }

    @Override
    public void onFinishMediaArtist(List<MediaDTO> result) {
        if (result != null) {
            getView().loadMedia(result);
        }
    }
}
