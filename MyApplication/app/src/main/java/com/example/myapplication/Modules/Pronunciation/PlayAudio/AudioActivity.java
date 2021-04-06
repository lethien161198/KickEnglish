package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.PositionEvent;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.Adapter.ViewPagerAdapter;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class AudioActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView duration, position;
    private ImageView next, pre, pause, play, shuffle, repeat;
    private SeekBar progress;
    private static MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private PlayerFragment playerFragment;
    private LyricFragment lyricFragment;
    private TextView title, back;

    public PlayerFragment getPlayerFragment() {
        return playerFragment;
    }

    public LyricFragment getLyricFragment() {
        return lyricFragment;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(Long time) {
        Log.d("bus", time + "");
        mediaPlayer.seekTo(time.intValue());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        lyricFragment = (LyricFragment) AppNavigator.viewWithRoute(AppNavigator.ROUTE_LYRICS);
        playerFragment = new PlayerFragment();
        init();
    }

    public void init() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        back = findViewById(R.id.txtback);
        title = findViewById(R.id.txtTitle);
        tabLayout.setupWithViewPager(viewPager);
        duration = findViewById(R.id.exo_duration);
        position = findViewById(R.id.exo_position);
        next = findViewById(R.id.exo_next);
        pre = findViewById(R.id.exo_prev);
        play = findViewById(R.id.exo_play);
        shuffle = findViewById(R.id.exo_shuffle);
        repeat = findViewById(R.id.exo_repeat_toggle);
        progress = findViewById(R.id.exo_progress);
        back.setOnClickListener(v -> {
            onBackPressed();
        });
        title.setText(R.string.player);
        String url = "";
        String transcript = "";
        Intent intent = getIntent();
        MediaDTO mediaDTO = (MediaDTO) intent.getSerializableExtra(Utilities.EXTRA_PLAYAUDIO);

        url = BuildConfig.BASE_URL + mediaDTO.getUrl();
        transcript = mediaDTO.getTranscriptUrl();


        Bundle bundle = new Bundle();
        bundle.putSerializable(Utilities.EXTRA_MEDIA, mediaDTO);
        playerFragment.setParameters(bundle);
        lyricFragment.getUrlLyric(transcript);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    title.setText(R.string.player);
                } else title.setText(R.string.transcript);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                mediaPlayer.start();
            }
        });


        try {
            Uri myUri = Uri.parse(url);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            mediaPlayer.setDataSource(getApplicationContext(), myUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            duration.setText(longtoString(mediaPlayer.getDuration()));
            progress.setMax(mediaPlayer.getDuration());
            progress.setProgress(mediaPlayer.getCurrentPosition());
            position.setText(longtoString(mediaPlayer.getCurrentPosition()));
            playMedia();
            handler.postDelayed(updateProgressAction, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable updateProgressAction;

    public void playMedia() {
        updateProgressAction = () -> {

            play.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    play.setImageDrawable(getDrawable(R.drawable.ic_play));
                } else {
                    mediaPlayer.start();
                    play.setImageDrawable(getDrawable(R.drawable.ic_pause));
                }
            });
            if (mediaPlayer.isPlaying()) {
                play.setImageDrawable(getDrawable(R.drawable.ic_pause));
            } else {
                play.setImageDrawable(getDrawable(R.drawable.ic_play));
            }
            lyricFragment.loadPosition(mediaPlayer.getCurrentPosition());
            lyricFragment.listenMedia(mediaPlayer.isPlaying());
            playerFragment.listenMedia(mediaPlayer.isPlaying());
            EventBus.getDefault()
                    .post(new PositionEvent(mediaPlayer.getCurrentPosition()));


            progress.setProgress(mediaPlayer.getCurrentPosition());

            position.setText(longtoString(mediaPlayer.getCurrentPosition()));

            handler.postDelayed(updateProgressAction, 500);
        };

    }

    public String longtoString(long a) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String s = simpleDateFormat.format(a);
        return s;
    }
}