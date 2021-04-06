package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.PositionEvent;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayerFragment extends FragmentView implements ListenerMedia {
    private Handler handler;
    private ImageView alpha;
    private CircleImageView circleImageView;
    private TextView txtnameAudio;
    private MediaDTO mediaDTO = null;
    private Boolean checker = false;
    private long a = 0;

    public PlayerFragment() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
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

    @Override
    public int getLayoutId() {
        return R.layout.fragment_player;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnCustomEvent(PositionEvent positionEvent) {
        a = positionEvent.getA();
//        if (txtnameAudio != null) {
//            txtnameAudio.setText(a + "");
//        }
    }

    @Override
    public void init(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mediaDTO = (MediaDTO) bundle.getSerializable(Utilities.EXTRA_MEDIA);
        }
        txtnameAudio = view.findViewById(R.id.nameAudio);
        circleImageView = view.findViewById(R.id.circleimage);
        alpha = view.findViewById(R.id.alpha);
        txtnameAudio.setText(mediaDTO.getTitle());
        LoadImage.Load(getContext(), BuildConfig.BASE_URL + mediaDTO.getMediumThumbnailUrl(), circleImageView);
        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
        alpha.startAnimation(animation);
        startRotation();
        handler = new Handler();
        playMedia();
        handler.postDelayed(updateProgressAction, 0);


    }

    private Runnable updateProgressAction;

    public void playMedia() {
        updateProgressAction = () -> {
            if (checker == true) {
                startRotation();
            } else stopRotation();
            handler.postDelayed(updateProgressAction, 200);
        };
    }

    public void listenMedia(boolean check) {
        checker = check;
    }

    private void startRotation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                circleImageView.animate().rotationBy(360).withEndAction(this).setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        circleImageView.animate().rotationBy(360).withEndAction(runnable).setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    private void stopRotation() {
        circleImageView.animate().cancel();
    }

    @Override
    public void onSendPosition(long position) {
        Log.i("po", position + "");
    }
}