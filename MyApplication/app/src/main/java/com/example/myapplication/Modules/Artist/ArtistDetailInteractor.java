package com.example.myapplication.Modules.Artist;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.API.ApiArtistDetail;
import com.example.myapplication.Common.basemvp.Interactor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ArtistDetailInteractor extends Interactor<ArtistDetailContract.Presenter> implements ArtistDetailContract.Interactor {
    private ApiArtistDetail apiArtistDetail = createService(ApiArtistDetail.class);

    @Override
    public void getArtist(int id) {
        Observable<Response<BodyResponseDTO<Artist>>> observable = apiArtistDetail.getArtistDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<Artist>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<Artist>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinishArtist(bodyResponseDTOResponse.body().getData());
                            getPresenter().onSuccess(bodyResponseDTOResponse.body().getMessage());
                        } else if (bodyResponseDTOResponse.code() == 401)
                            getPresenter().tokenFailed(bodyResponseDTOResponse.message());
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

    @Override
    public void getMediaArtist(int id) {
        Observable<Response<BodyResponseDTO<List<MediaDTO>>>> observable = apiArtistDetail.getMedia(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<MediaDTO>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<MediaDTO>>> listBodyResponseDTO) {
                        if (listBodyResponseDTO.body() != null) {
                            getPresenter().onFinishMediaArtist(listBodyResponseDTO.body().getData());
                            getPresenter().onSuccess(listBodyResponseDTO.body().getMessage());
                        } else if (listBodyResponseDTO.code() == 401)
                            getPresenter().tokenFailed(listBodyResponseDTO.code() + "");
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
