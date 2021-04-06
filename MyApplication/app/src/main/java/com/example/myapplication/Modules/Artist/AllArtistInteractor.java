package com.example.myapplication.Modules.Artist;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Modules.API.ApiHome;
import com.example.myapplication.Common.basemvp.Interactor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AllArtistInteractor extends Interactor<AllArtistContract.Presenter> implements AllArtistContract.Interactor {

    private ApiHome apiHome = createService(ApiHome.class);

    @Override
    public void getAllArtistAPI() {
        Observable<Response<BodyResponseDTO<List<Artist>>>> observable = apiHome.listArtist();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<Artist>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<Artist>>> bodyResponseDTOResponse) {
                        if(bodyResponseDTOResponse.body()!=null){
                            getPresenter().onFinishGetAll(bodyResponseDTOResponse.body().getData());
                            getPresenter().onSuccess(bodyResponseDTOResponse.body().getMessage());
                        }
                        else if(bodyResponseDTOResponse.code()==401) getPresenter().tokenFailed(bodyResponseDTOResponse.code()+"");
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
