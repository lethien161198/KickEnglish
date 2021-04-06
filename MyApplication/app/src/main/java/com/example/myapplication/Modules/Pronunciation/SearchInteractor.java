package com.example.myapplication.Modules.Pronunciation;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.API.ApiMedia;
import com.example.myapplication.Common.basemvp.Interactor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchInteractor extends Interactor<SearchContract.Presenter> implements SearchContract.Interactor {
    private ApiMedia apiMedia = createService(ApiMedia.class);

    @Override
    public void getResult(String s) {

        Observable<Response<BodyResponseDTO<List<MediaDTO>>>> observable = apiMedia.listMedia(2,  "AUDIO", s);
        observable.delay(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<MediaDTO>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<MediaDTO>>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinishSearch(bodyResponseDTOResponse.body().getData());
                            getPresenter().onSuccess(bodyResponseDTOResponse.body().getMessage());
                        } else if (bodyResponseDTOResponse.code() == 401)
                            getPresenter().tokenFailed(bodyResponseDTOResponse.code() + "");
                        else getPresenter().onError(bodyResponseDTOResponse.message());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getPresenter().onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
