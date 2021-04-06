package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.os.Bundle;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Lyric;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LyricPresenter extends Presenter<LyricContract.View, LyricContract.Interactor> implements LyricContract.Presenter {

    public LyricPresenter(LyricContract.View view, LyricContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getLyric(String url) {

        String urlfull = BuildConfig.BASE_URL + url;
        getInteractor().downloadLyric(urlfull);
    }

    @Override
    public void tokenFailed(String message) {
        getView().showTokenFailed(message);
    }

    @Override
    public void onSuccess(String message) {
        getView().showSuccess(message);
    }

    @Override
    public void onError(String message) {
        getView().showError(message);
    }

    @Override
    public void onFinishDownloadLyric(List<String> list) {
        long currentTime = 0;
        String currentContent = "";
        List<Lyric> lyricList = new ArrayList<>();
        String reg = "\\[(\\d{2}:\\d{2}\\.\\d{2})\\]";
        // compile
        for (String str : list) {
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(str);

            while (matcher.find()) {

                // Get the number of groups in this match
                int groupCount = matcher.groupCount();
                // Get the content in each group
                for (int i = 0; i <= groupCount; i++) {
                    String timeStr = matcher.group(i);
                    if (i == 1) {
                        // Set the content in the second group to the current point in time
                        currentTime = Utilities.strToLong1(timeStr);
                    }
                }

                // Get the content after the time
                String[] content = pattern.split(str);

                for (int i = 0; i < content.length; i++) {
                    if (i == content.length - 1) {
                        // set the content to the current content
                        currentContent = content[i];
                    }
                }
                Lyric lyric = new Lyric(currentTime, currentContent);
                lyricList.add(lyric);
            }


        }

        getView().loadLyric(lyricList);
        onSuccess("Success");
    }
}
