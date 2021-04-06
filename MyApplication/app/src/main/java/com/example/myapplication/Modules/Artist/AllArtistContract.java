package com.example.myapplication.Modules.Artist;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Home.Artist;

import java.util.List;

public interface AllArtistContract {
    interface View extends MVP.View<Presenter> {
        void loadAllArtist(List<Artist> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getAllArtist();

        void onFinishGetAll(List<Artist> result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getAllArtistAPI();
    }

}
