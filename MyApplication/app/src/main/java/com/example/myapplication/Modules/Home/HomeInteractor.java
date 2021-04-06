package com.example.myapplication.Modules.Home;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.MemberLessonDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.API.ApiHome;
import com.example.myapplication.Modules.API.ApiUser;
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

public class HomeInteractor extends Interactor<HomeFragmentContract.Presenter> implements HomeFragmentContract.Interactor {

    private final ApiHome apiHome = createService(ApiHome.class);
    private final ApiUser apiUser = createService(ApiUser.class);
    private Observable<Response<BodyResponseDTO<UserDTO>>> observableUser;
    private Observable<Response<BodyResponseDTO<List<CategoryDTO>>>> observableCategories;
    private Observable<Response<BodyResponseDTO<List<Artist>>>> observableArtists;
    private Observable<Response<BodyResponseDTO<List<MemberLessonDTO>>>> observableMemberLessons;

    public void merge() {
        Observable.mergeDelayError( observableArtists, observableCategories, observableMemberLessons)
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void getUser() {
        observableUser = apiUser.getCurrentUser();
        observableUser.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<UserDTO>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<UserDTO>> response) {
                        if (response.body() != null) {
                            getPresenter().onFinishLoadUser(response.body().getData());
                            getPresenter().onSuccess(response.body().getMessage());
                        } else if (response.code() == 401)
                            getPresenter().tokenFailed(response.code() + "");
                        else getPresenter().onError(response.message());
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
    public void getCategories() {
        observableCategories = apiHome.listCategory("");

        observableCategories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<CategoryDTO>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<CategoryDTO>>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinishLoadCategories(bodyResponseDTOResponse.body().getData());
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

    @Override
    public void getArtists() {
        observableArtists = apiHome.listArtist();
        observableArtists.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<Artist>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<Artist>>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinishLoadArtist(bodyResponseDTOResponse.body().getData());
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

    @Override
    public void getLessonMember() {
        observableMemberLessons = apiHome.listMemberLesson();
        observableMemberLessons.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BodyResponseDTO<List<MemberLessonDTO>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BodyResponseDTO<List<MemberLessonDTO>>> bodyResponseDTOResponse) {
                        if (bodyResponseDTOResponse.body() != null) {
                            getPresenter().onFinishLoadLessonMember(bodyResponseDTOResponse.body().getData());
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
