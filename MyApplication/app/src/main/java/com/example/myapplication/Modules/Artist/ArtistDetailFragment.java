package com.example.myapplication.Modules.Artist;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devs.readmoreoption.ReadMoreOption;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.Adapter.MediaAdapter;
import com.example.myapplication.R;

import java.util.List;


public class ArtistDetailFragment extends FragmentView<ArtistDetailContract.Presenter> implements ArtistDetailContract.View {
    private TextView name;
    private TextView shortDes;
    private TextView Des;
    private ImageView img, imageView;
    private TextView listStyle, back;
    private RecyclerView recyclerView;
    private MediaAdapter mediaAdapter;
    private int id;
    private TextView save, title;

    public ArtistDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_artist_detail;
    }

    public void init(android.view.View view) {

        name = view.findViewById(R.id.nameArtist);
        shortDes = view.findViewById(R.id.shortDes);
        Des = view.findViewById(R.id.Description);
        TextView nameStyle = view.findViewById(R.id.namestyle);
        listStyle = view.findViewById(R.id.liststyle);
        img = view.findViewById(R.id.imageView);
        recyclerView = view.findViewById(R.id.recyclerMedia);
        back = view.findViewById(R.id.txtback);
        save = view.findViewById(R.id.save);
        title = view.findViewById(R.id.txtTitle);
        imageView = view.findViewById(R.id.imageBack);
        imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);
        save.setText("");
        title.setText("");
        showBottomBar(0, R.drawable.bg);

        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
            showBottomBar(1, R.drawable.backg);
        });

        id = 0;
        Bundle receive = getArguments();
        if (receive != null) {
            id = (Integer) receive.get(Utilities.ID_ARTIST);
        }
        getPresenter().onGetArtist(id);
        getPresenter().onGetMediaArtist(id);

    }

    @Override
    public void loadDetail(Artist artist) {
        name.setText(artist.getName().toUpperCase());
        shortDes.setText((artist.getShortDescription()));
        LoadImage.Load(this, BuildConfig.BASE_URL+ artist.getImageDetailUrl(), img);

        listStyle.setText(artist.getStyle());

        ReadMoreOption readMoreOption = new ReadMoreOption.Builder(this.getContext())
                .textLength(3, ReadMoreOption.TYPE_LINE)
                .moreLabel(getResources().getString(R.string.txtMore))
                .lessLabel(getResources().getString(R.string.txtLess))
                .moreLabelColor(Color.YELLOW)
                .lessLabelColor(Color.YELLOW)
                .labelUnderLine(true)
                .expandAnimation(true)
                .build();
        readMoreOption.addReadMoreTo(Des, artist.getDescription());
    }

    @Override
    public void loadMedia(List<MediaDTO> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mediaAdapter = new MediaAdapter(this.getContext(), list, mediaDTO -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utilities.ID_MEDIA, mediaDTO);
            navigate(AppNavigator.ROUTE_PLAY_VIDEO, bundle);
        });
        recyclerView.setAdapter(mediaAdapter);
        mediaAdapter.notifyDataSetChanged();
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