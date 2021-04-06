package com.example.myapplication.Modules.Artist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Modules.Adapter.ArtistAdapter;
import com.example.myapplication.R;

import java.util.List;

public class AllArtistFragment extends FragmentView<AllArtistContract.Presenter> implements AllArtistContract.View {
    private TextView back, title, save;
    private ImageView imageView;
    private RecyclerView allArtistRecycler;
    private ArtistAdapter artistAdapter;

    public AllArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all_artist;
    }

    @Override
    public void init(android.view.View view) {
        back = view.findViewById(R.id.txtback);
        title = view.findViewById(R.id.txtTitle);
        save = view.findViewById(R.id.save);
        imageView = view.findViewById(R.id.imageBack);
        imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);

        title.setText(R.string.artist);

        save.setText("");
        allArtistRecycler = view.findViewById(R.id.recyclerAllartist);
        getPresenter().getAllArtist();
        showBottomBar(0, R.color.pronun);
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
            showBottomBar(1, R.drawable.backg);
        });
    }

    @Override
    public void loadAllArtist(List<Artist> list) {
        allArtistRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        artistAdapter = new ArtistAdapter(this.getContext(), list, id -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utilities.ID_ARTIST, id);
            navigate(AppNavigator.ROUTE_ARTIST_DETAIL, bundle);
        });
        allArtistRecycler.setAdapter(artistAdapter);
        artistAdapter.notifyDataSetChanged();
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