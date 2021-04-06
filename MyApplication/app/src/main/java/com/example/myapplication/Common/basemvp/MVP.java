package com.example.myapplication.Common.basemvp;

import android.os.Bundle;

public class MVP {
    public interface Presenter<V extends View, I extends Interactor> {
        V getView();

        I getInteractor();

        void tokenFailed(String message);

        void onSuccess(String message);

        void onError(String message);
    }

    public interface View<P extends Presenter> {
        void setPresenter(P presenter);

        P getPresenter();

        void setParameters(Bundle parameters);

        void navigate(String route, Bundle parameters);

        void navigateReplaced(String route, Bundle parameters);

        void showSuccess(String message);

        void showError(String message);

        void showTokenFailed(String message);
    }

    public interface Interactor<P extends Presenter> {
        void setPresenter(P presenter);

        P getPresenter();
    }
}
