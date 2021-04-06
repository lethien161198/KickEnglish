package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Lyric;

import java.util.List;

public interface LyricContract {
    interface View extends MVP.View<Presenter> {
        void loadLyric(List<Lyric> lyricList);
    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void getLyric(String url);

        void onFinishDownloadLyric(List<String> list);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void downloadLyric(String url);
    }

}
