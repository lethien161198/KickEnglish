package com.example.myapplication.Modules.Pronunciation;

import android.os.Bundle;

import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.List;

public class PronunPresenter extends Presenter<PronunContract.View, PronunContract.Interactor> implements PronunContract.Presenter {

    public PronunPresenter(PronunContract.View view, PronunContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void listMedia() {
        getInteractor().getListMedia();
    }

    @Override
    public void onFinishGetListMedia(List<MediaDTO> list) {
        getView().loadMedia(list);
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
}
