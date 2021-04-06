package com.example.myapplication.Modules.Setting;

import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.UpdateUserDTO;
import com.example.myapplication.Modules.API.ApiUser;
import com.example.myapplication.Common.basemvp.Interactor;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingInteractor extends Interactor<SettingContract.Presenter> implements SettingContract.Interactor {
    private ApiUser apiUser = createService(ApiUser.class);

    @Override
    public void updateUserDTO(String fn, String ln, String p, String dob, String gen, String national, String des, MultipartBody.Part filePart, String lang) {
        RequestBody firstName =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(fn));
        RequestBody lastName =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(ln));
        RequestBody phone =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(p));
        RequestBody dateOfBirth =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(dob));
        RequestBody gender =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(gen));
        RequestBody nation =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(national));
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(des));
        RequestBody language =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), String.valueOf(lang));


        Call<BodyResponseDTO<UpdateUserDTO>> call = apiUser.updateUser(firstName,
                lastName, gender, phone, nation, dateOfBirth, description, language, filePart);
        call.enqueue(new Callback<BodyResponseDTO<UpdateUserDTO>>() {
            @Override
            public void onResponse(Call<BodyResponseDTO<UpdateUserDTO>> call, Response<BodyResponseDTO<UpdateUserDTO>> response) {
                if (response.body() != null) {
                    getPresenter().onFinish();
                    getPresenter().onSuccess(response.body().getMessage());
                } else if (response.code() == 401) getPresenter().tokenFailed(response.message());
                else getPresenter().onError(response.message());
            }

            @Override
            public void onFailure(Call<BodyResponseDTO<UpdateUserDTO>> call, Throwable t) {
                getPresenter().onError(t.getMessage());
            }
        });
    }
}
