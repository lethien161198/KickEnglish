package com.example.myapplication.Modules.Artist;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Models.Dictionary.ResponeDictionary;
import com.example.myapplication.Modules.API.ApiDictionary;
import com.example.myapplication.Modules.API.ApiDownload;
import com.example.myapplication.Modules.API.RetrofitClient;
import com.example.myapplication.Common.basemvp.Interactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayVideoInteractor extends Interactor<PlayVideoContract.Presenter> implements PlayVideoContract.Interactor {
    ApiDownload apiDownload = createService(ApiDownload.class);

    @Override
    public void getTranscript(String link) {
        String host = BuildConfig.BASE_URL + link;


        apiDownload.downloadFile(host).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    readFile(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("downloadfile", "onFailure: " + t.getMessage());
            }
        });
    }

    private void readFile(ResponseBody body) throws IOException {
        StringBuilder text = new StringBuilder();
        InputStream inputStream = null;
        try {


            inputStream = body.byteStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str = in.readLine()) != null) {
                text.append(str + "\n");
            }

            new Handler(Looper.getMainLooper()).post(() -> getPresenter().onFinishGetTrans(text));
            Log.d("downloadfile", text.toString());
        } catch (IOException e) {
            Log.d("downloadfile", e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Override
    public void getApiDictionary(String word) {
        ApiDictionary apiDictionary = RetrofitClient.getClientDictionary().create(ApiDictionary.class);
        Call<List<ResponeDictionary>> call = apiDictionary.tranlslate(word);
        call.enqueue(new Callback<List<ResponeDictionary>>() {
            @Override
            public void onResponse(Call<List<ResponeDictionary>> call, Response<List<ResponeDictionary>> response) {
                if (response.body() != null) {
                    List<ResponeDictionary> responeDictionary = response.body();
                    getPresenter().onFinishDictionary(responeDictionary);
                } else getPresenter().onFailedDictionary();
            }

            @Override
            public void onFailure(Call<List<ResponeDictionary>> call, Throwable t) {

            }
        });
    }

}
