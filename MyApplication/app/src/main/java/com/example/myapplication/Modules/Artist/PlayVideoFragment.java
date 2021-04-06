package com.example.myapplication.Modules.Artist;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Dictionary.ResponeDictionary;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Models.Subtitles;
import com.example.myapplication.Models.TextModel;
import com.example.myapplication.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


public class PlayVideoFragment extends FragmentView<PlayVideoContract.Presenter> implements PlayVideoContract.View {
    private PlayerView playerView;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;
    private ImageView btnFullscreen;
    private ImageView imageView;
    private boolean flag = false;
    private TextView title, save, back;
    private SimpleExoPlayer simpleExoPlayer;
    private LinearLayout ll;
    private LinearLayout.LayoutParams params;
    private Handler handler;
    private List<Subtitles> subtitlesList = new ArrayList<>();
    private String url = "";
    private int index = -1;
    private TextView define, cate;
    private ImageView sound;
    private MediaPlayer mediaPlayer;
    private android.view.View header;

    public PlayVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.getPlaybackState();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_play_video;
    }

    @Override
    public void init(android.view.View view) {
        LoadControl loadControl = new DefaultLoadControl();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);

        header = view.findViewById(R.id.header);
        sound = view.findViewById(R.id.sound);
        cate = view.findViewById(R.id.txtCate);
        title = view.findViewById(R.id.txtTitle);
        save = view.findViewById(R.id.save);
        frameLayout = view.findViewById(R.id.frame);
        playerView = view.findViewById(R.id.player_view);
        progressBar = view.findViewById(R.id.progress_bar);
        btnFullscreen = view.findViewById(R.id.bt_fullscreen);
        back = view.findViewById(R.id.txtback);
        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView = view.findViewById(R.id.imageBack);
        imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);
        define = view.findViewById(R.id.txtDefine);

        ll = view.findViewById(R.id.translate);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        back.setOnClickListener(v -> {
            if (handler != null) {
                handler.removeCallbacks(updateProgressAction);
            }

            getActivity().onBackPressed();
        });
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        title.setText(R.string.video);
        save.setText("");

        MediaDTO mediaDTO = null;
        Bundle receive = getArguments();
        if (receive != null) {
            mediaDTO = (MediaDTO) receive.get(Utilities.ID_MEDIA);
            url = mediaDTO.getUrl();
        }

        getPresenter().getTrans(mediaDTO.getTranscriptUrl());


        showBottomBar(0, R.drawable.back);
    }

    public void playVideo(PlayerView playerView, ProgressBar progressBar, SimpleExoPlayer simpleExoPlayer, String url) {
        Uri videoUrl = Uri.parse(url);

        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(videoUrl, factory, extractorsFactory, null, null);

        playerView.setPlayer(simpleExoPlayer);

        playerView.setKeepScreenOn(true);

        simpleExoPlayer.prepare(mediaSource);

        simpleExoPlayer.setPlayWhenReady(true);

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING) {
                    progressBar.setVisibility(android.view.View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
        handler = new Handler();

        if (subtitlesList.size() > 0) {
            long position = simpleExoPlayer.getCurrentPosition();
            for (Subtitles s : subtitlesList) {
                if (s.getStart() <= position && s.getEnd() >= position) {
                    if (subtitlesList.indexOf(s) != index) {
                        ll.removeAllViews();
                        loadText(String.valueOf(s.getContent()));
                        index = subtitlesList.indexOf(s);
                    }
                }
            }

        }
        handler.postDelayed(updateProgressAction, 0);

    }

    private final Runnable updateProgressAction = new Runnable() {
        @Override
        public void run() {
            if (subtitlesList.size() > 0) {
                long position = simpleExoPlayer.getCurrentPosition();
                for (Subtitles s : subtitlesList) {
                    if (s.getStart() <= position && s.getEnd() >= position) {
                        if (subtitlesList.indexOf(s) != index) {
                            ll.removeAllViews();
                            loadText(String.valueOf(s.getContent()));
                            index = subtitlesList.indexOf(s);
                            break;
                        }
                    }
                }
                handler.postDelayed(updateProgressAction, 500);
            }

        }
    };

    @Override
    public void loadVideo(String url) {
        playVideo(playerView, progressBar, simpleExoPlayer, url);
        btnFullscreen.setOnClickListener(v -> {
            if (flag) {
                btnFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                int px = Utilities.convertDpToPixels(200, getContext());
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, px));
                header.setVisibility(android.view.View.VISIBLE);
                flag = false;
            } else {
                btnFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.exo_controls_fullscreen_exit));
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                frameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                header.setVisibility(android.view.View.GONE);
                flag = true;
            }
        });

    }

    @Override
    public void loadTrans(List<Subtitles> list) {
        subtitlesList = list;
        getPresenter().getVideo(url, getContext());
    }

    @Override
    public void loadDictionary(List<ResponeDictionary> list) {
        try {
            if (list.get(0) != null && list.get(0).getMeanings() != null && list.get(0).getPhonetics() != null && list.get(0).getMeanings().get(0).getDefinitions().get(0) != null) {
                ResponeDictionary a = list.get(0);
                cate.setText(a.getPhonetics().get(0).getText());
                define.setText(a.getMeanings().get(0).getDefinitions().get(0).getDefinition());
                String soundurl = a.getPhonetics().get(0).getAudio();
                sound.setOnClickListener(v -> {
                    try {
                        Uri myUri = Uri.parse(soundurl);
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioAttributes(
                                new AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .build()
                        );
                        mediaPlayer.setDataSource(getContext(), myUri);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void loadFailed() {
        cate.setText("Not Found");
        define.setText("");
    }

    public void loadText(String content) {
        List<TextModel> textModelList = new ArrayList<>();
        String[] word = content.split("\\s");
        int i = 0;

        while (i < word.length) {
            TextView tv = new TextView(getContext());
            tv.setText(" " + word[i]);
            tv.setTextColor(Color.WHITE);
            tv.setLayoutParams(params);
            tv.setOnClickListener(v -> {
                tv.setBackgroundColor(Color.GRAY);
                getPresenter().getDictionary(tv.getText().toString());
                simpleExoPlayer.setPlayWhenReady(false);
                for (TextModel t : textModelList) {
                    TextView textView1 = t.getTextView();
                    if (!textView1.getText().equals(tv.getText())) {
                        textView1.setBackgroundColor(Color.BLACK);
                    }
                }
            });
            textModelList.add(new TextModel(tv, i));
            ll.addView(tv);
            i++;
        }
    }

    @Override
    public void showSuccess(String message) {
        showToast(message);
    }

    @Override
    public void showError(String message) {
        showToast(message);
    }

    @Override
    public void showTokenFailed(String message) {
        showToast(message);
        logOut(getActivity());
    }
}
