package com.example.myapplication.Modules.Artist;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Dictionary.ResponeDictionary;
import com.example.myapplication.Models.Subtitles;
import com.example.myapplication.Common.basemvp.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class PlayVideoPresenter extends Presenter<PlayVideoContract.View, PlayVideoContract.Interactor> implements PlayVideoContract.Presenter {

    public PlayVideoPresenter(PlayVideoContract.View view, PlayVideoContract.Interactor interactor, Bundle parameters) {
        super(view, interactor, parameters);
    }

    @Override
    public void getVideo(String url, Context context) {
        onFinishGetUrl(url, context);

    }

    @Override
    public void getTrans(String url) {
        getInteractor().getTranscript(url);
    }

    @Override
    public void getDictionary(String word) {
        getInteractor().getApiDictionary(word);
    }

    @Override
    public void onFinishGetUrl(String url, Context context) {
        if (url.contains("youtube.com")) {

            new YouTubeExtractor(context) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {
                        int itag = 22;
                        String downloadUrl = ytFiles.get(itag).getUrl();
                        getView().loadVideo(downloadUrl);
                    }
                }
            }.extract(url, true, true);
        } else {
            String fullurl = BuildConfig.BASE_URL + url;
            getView().loadVideo(fullurl);
        }
    }

    @Override
    public void onFinishGetTrans(StringBuilder text) {
        List<Subtitles> list = new ArrayList<>();

        String lineNumberPattern = "(\\d+\\s)";
        String timeStampPattern = "([\\d:,]+)";
        String contentPattern = "(.*)";
        Matcher matcher = Pattern.compile(lineNumberPattern + timeStampPattern + "( --> )" + timeStampPattern + "(\\s)" + contentPattern).matcher(text.toString());
        while (matcher.find()) {
            Subtitles subtitle = new Subtitles("", 0, 0);
            String start = matcher.group(2);
            String end = matcher.group(4);
            String content = matcher.group(6);

            subtitle.setStart(Utilities.strToLong(start));
            subtitle.setEnd(Utilities.strToLong(end));
            subtitle.setContent(content);
            list.add(subtitle);
        }
        getView().loadTrans(list);
    }

    @Override
    public void onFinishDictionary(List<ResponeDictionary> list) {
        if (list != null) {
            getView().loadDictionary(list);
        } else onError("Load Failed");
    }

    @Override
    public void onFailedDictionary() {
        getView().loadFailed();
    }
}
