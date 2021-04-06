package com.example.myapplication.Modules.Artist;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Pronunciation.MediaDTO;

import java.util.List;

public interface ArtistDetailContract {
    interface View extends MVP.View<Presenter> {
        void loadDetail(Artist artist);

        void loadMedia(List<MediaDTO> list);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void onGetArtist(int id);

        void onGetMediaArtist(int id);

        void onFinishArtist(Artist result);

        void onFinishMediaArtist(List<MediaDTO> result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getArtist(int id);

        void getMediaArtist(int id);
    }
}
