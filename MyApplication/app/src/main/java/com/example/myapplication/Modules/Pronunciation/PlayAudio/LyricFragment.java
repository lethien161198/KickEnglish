package com.example.myapplication.Modules.Pronunciation.PlayAudio;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Lyric;
import com.example.myapplication.Modules.Adapter.LyricAdapter;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class LyricFragment extends FragmentView<LyricContract.Presenter> implements LyricContract.View {
    private TextView textView;
    private Handler handler;
    private long a = 0;
    private RecyclerView lyricRecycler;
    private String urllyric = "";
    private LyricAdapter lyricAdapter;
    private List<Lyric> lyric = new ArrayList<>();
    private Boolean checker = false;
    private LinearLayoutManager layoutManager;

    public LyricFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_lyric;
    }

    public void init(View view) {
        lyricRecycler = view.findViewById(R.id.listLyric);
        textView = view.findViewById(R.id.textView);
        handler = new Handler();
        playMedia();
        handler.postDelayed(updateProgressAction, 0);
        getPresenter().getLyric(urllyric);
    }

    private Runnable updateProgressAction;

    public void playMedia() {
        updateProgressAction = () -> {
            if (checker) {
                for (Lyric l : lyric) {
                    int i = lyric.indexOf(l);

                    if (a >= l.getTime() && a < lyric.get(i + 1).getTime()) {
                        lyricRecycler.smoothScrollToPosition(lyric.indexOf(l));
//                        if (lyric.indexOf(l) >= 4){
//                            lyricRecycler.smoothScrollToPosition(lyric.indexOf(l) - 4);
//                        }
//                        int totalVisibleItems = layoutManager.findLastVisibleItemPosition() - layoutManager.findFirstVisibleItemPosition();
//                        int centeredItemPosition = totalVisibleItems / 2;
//                        lyricRecycler.smoothScrollToPosition(i);
//                        lyricRecycler.setScrollY(centeredItemPosition );
                        lyricRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                if (dy > 0) {
                                    //Scrolling down
                                    Log.d("recycler", "onScrolled: down");
                                } else if (dy < 0 && lyric.indexOf(l) >= 4) {
                                    //Scrolling up
                                    Log.d("recycler", "onScrolled: up");
//                                    lyricRecycler.smoothScrollToPosition(lyric.indexOf(l) + 5);

                                }
                            }
                        });
                        if (lyricAdapter.index != i) {
                            int b = lyricAdapter.index;
                            lyricAdapter.index = i;
                            lyricAdapter.notifyItemChanged(b);
                            lyricAdapter.notifyItemChanged(i);
                        }
                    }
                }
            } else {
                int b = lyricAdapter.index;
                lyricAdapter.index = -1;
                lyricAdapter.notifyItemChanged(b);
            }
            handler.postDelayed(updateProgressAction, 200);
        };
    }

    public void loadPosition(long position) {
        a = position;
    }

    public void getUrlLyric(String url) {
        urllyric = url;

    }

    public void listenMedia(boolean check) {
        checker = check;
    }

    @Override
    public void loadLyric(List<Lyric> lyricList) {
        lyric = lyricList;

        lyricRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lyricAdapter = new LyricAdapter(this.getContext(), lyric, new LyricAdapter.onClickItem() {
            @Override
            public void sendTime(long time) {
                EventBus.getDefault().postSticky(new Long(time));
            }
        });
        lyricRecycler.setAdapter(lyricAdapter);
        lyricAdapter.notifyDataSetChanged();
        layoutManager = (LinearLayoutManager) lyricRecycler.getLayoutManager();
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