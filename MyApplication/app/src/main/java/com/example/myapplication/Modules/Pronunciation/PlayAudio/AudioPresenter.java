package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.os.Bundle;

import com.example.myapplication.Common.basemvp.Presenter;

public class AudioPresenter extends Presenter<AudioContract.View, AudioContract.Interactor> implements AudioContract.Presenter {
    public AudioPresenter(AudioContract.View view, AudioContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }
}
