package com.example.myapplication.Modules.Home;

import android.os.Bundle;

import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.MemberLessonDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.List;

public class HomeFragmentPresenter extends Presenter<HomeFragmentContract.View, HomeFragmentContract.Interactor> implements HomeFragmentContract.Presenter {
    public HomeFragmentPresenter(HomeFragmentContract.View view, HomeFragmentContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    public void Categories() {

        getInteractor().getCategories();

    }


    public void Artists() {
        getInteractor().getArtists();
    }

    @Override
    public void MemberLessons() {
        getInteractor().getLessonMember();
    }

    @Override
    public void User() {
        getInteractor().getUser();
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
    public void onFinishLoadUser(UserDTO result) {
        if (result != null) {

            getView().loadUser(result);

        }
    }

    @Override
    public void onFinishLoadCategories(List<CategoryDTO> result) {
        if (result != null) {

            getView().loadCategories(result);

        }
    }

    @Override
    public void onFinishLoadArtist(List<Artist> result) {
        if (result != null) {

            getView().loadArtists(result);

        }
    }

    @Override
    public void onFinishLoadLessonMember(List<MemberLessonDTO> result) {
        if (result != null) {

            getView().loadLessonMembers(result);

        }
    }
}
