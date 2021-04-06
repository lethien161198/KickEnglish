package com.example.myapplication.Modules.Pronunciation;


import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.Adapter.PronunAdapter;
import com.example.myapplication.Modules.Adapter.RecentAdapter;
import com.example.myapplication.Modules.Home.HomeActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends FragmentView<SearchContract.Presenter> implements SearchContract.View {
    private EditText search;
    private ImageView imgback;
    private TextView clear;
    private RecyclerView listRecent, listPro;
    private RecentAdapter recentAdapter;
    private HomeActivity homeActivity;
    private PronunAdapter pronunAdapter;
    String string = "";

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void init(View view) {

        homeActivity = (HomeActivity) getActivity();
        List<String> recent = new ArrayList<>();
        List<MediaDTO> newList = new ArrayList<>();
        clear = view.findViewById(R.id.clear);
        imgback = view.findViewById(R.id.imageBack);
        listRecent = view.findViewById(R.id.listRecent);
        listPro = view.findViewById(R.id.listPronunciation);

        imgback.setOnClickListener(v -> getActivity().onBackPressed());
        clear.setOnClickListener(v -> {
            recent.clear();
            setAdapter(recent);
        });
        search = view.findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                new Handler().postDelayed(() -> {
                    Log.d("TAG", "afterTextChanged: " + s.toString());
                    if (!s.toString().equals("")) {
                        getPresenter().onSearch(s.toString());
                    }
                }, 1000);
                if (s.toString().equals("")) {
                    newList.clear();
                    loadResultSearch(newList);
                }
            }
        });
    }

    public void setAdapter(List<String> list) {
        recentAdapter = new RecentAdapter(getContext(), list);
        listRecent.setLayoutManager(new GridLayoutManager(getContext(), 1, LinearLayoutManager.VERTICAL, false));
        listRecent.setAdapter(recentAdapter);
        recentAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadResultSearch(List<MediaDTO> listMedia) {
        listPro.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        pronunAdapter = new PronunAdapter(this.getContext(), listMedia,
                mediaDTO -> {
                    homeActivity.goToPlayAudioActivity(mediaDTO);
                });
        listPro.setAdapter(pronunAdapter);
        pronunAdapter.notifyDataSetChanged();
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