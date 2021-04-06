package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.Modules.API.ApiDownload;
import com.example.myapplication.Common.basemvp.Interactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricInteractor extends Interactor<LyricContract.Presenter> implements LyricContract.Interactor {

    @Override
    public void downloadLyric(String url1) {

        ApiDownload apiDownload = createService(ApiDownload.class);
        apiDownload.downloadFile(url1).enqueue(new Callback<ResponseBody>() {
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
        List<String> result = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        InputStream inputStream = null;
        try {

            inputStream = body.byteStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str = in.readLine()) != null) {
                text.append(str + "\n");
                result.add(str);
            }
            new Handler(Looper.getMainLooper()).post(() -> getPresenter().onFinishDownloadLyric(result));
            Log.d("downloadfile", text.toString());

        } catch (IOException e) {
            Log.d("downloadfile", e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }
}
