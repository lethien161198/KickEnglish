package com.example.myapplication.Modules.Pronunciation;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Pronunciation.MediaDTO;

import java.util.List;

public interface SearchContract {
    interface View extends MVP.View<Presenter> {

        void loadResultSearch(List<MediaDTO> listMedia);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void onSearch(String s);

        void onFinishSearch(List<MediaDTO> result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getResult(String s);
    }

}
