package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import com.example.myapplication.Common.basemvp.MVP;

public interface AudioContract {
    interface View extends MVP.View<Presenter> {

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {

    }

    interface Interactor extends MVP.Interactor<Presenter> {

    }
}

