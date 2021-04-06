package com.example.myapplication.Modules.Pronunciation;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Pronunciation.MediaDTO;

import java.util.List;

public interface PronunContract {
    interface View extends MVP.View<Presenter> {
        void loadMedia(List<MediaDTO> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void listMedia();

        void onFinishGetListMedia(List<MediaDTO> list);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getListMedia();
    }
}
