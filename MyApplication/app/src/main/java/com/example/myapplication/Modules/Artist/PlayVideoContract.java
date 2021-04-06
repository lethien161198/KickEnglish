package com.example.myapplication.Modules.Artist;

import android.content.Context;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Dictionary.ResponeDictionary;
import com.example.myapplication.Models.Subtitles;

import java.util.List;

public interface PlayVideoContract {
    interface View extends MVP.View<Presenter> {
        void loadVideo(String url);

        void loadTrans(List<Subtitles> list);

        void loadDictionary(List<ResponeDictionary> list);

        void loadFailed();
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getVideo(String url, Context context);

        void getTrans(String url);

        void getDictionary(String word);

        void onFinishGetUrl(String url, Context context);

        void onFinishGetTrans(StringBuilder text);

        void onFinishDictionary(List<ResponeDictionary> list);

        void onFailedDictionary();
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getTranscript(String url);

        void getApiDictionary(String word);

    }
}
