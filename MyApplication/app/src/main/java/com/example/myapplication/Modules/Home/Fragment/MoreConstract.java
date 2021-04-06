package com.example.myapplication.Modules.Home.Fragment;

import com.example.myapplication.Common.basemvp.MVP;

public class MoreConstract {
    interface Presenter extends MVP.Presenter<View, Interactor> {
    }

    interface Interactor extends MVP.Interactor<Presenter> {
    }

    interface View extends MVP.View<Presenter> {
    }
}
