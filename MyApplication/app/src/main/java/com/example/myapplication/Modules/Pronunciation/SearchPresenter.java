package com.example.myapplication.Modules.Pronunciation;

import android.os.Bundle;

import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.List;

public class SearchPresenter extends Presenter<SearchContract.View, SearchContract.Interactor> implements SearchContract.Presenter {

    public SearchPresenter(SearchContract.View view, SearchContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void onSearch(String s) {
        getInteractor().getResult(s);
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
    public void onFinishSearch(List<MediaDTO> result) {
        if (result != null) {
            getView().loadResultSearch(result);
        }
    }
}
