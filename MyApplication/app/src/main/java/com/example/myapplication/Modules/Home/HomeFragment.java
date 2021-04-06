package com.example.myapplication.Modules.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Components.PlayVideo;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.CategoryType;
import com.example.myapplication.Models.Home.MemberLessonDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.Adapter.ArtistAdapter;
import com.example.myapplication.Modules.Adapter.MemberLessonAdapter;
import com.example.myapplication.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends FragmentView<HomeFragmentContract.Presenter> implements HomeFragmentContract.View {
    private CircleImageView nlp, pronunciation, conversation, training, tutorial, course;
    private RecyclerView recyclerView, recyclerViewMem;
    private ArtistAdapter artistAdapter;
    private MemberLessonAdapter memberLessonAdapter;
    private String url = "";
    private TextView username, seeall;
    private CircleImageView avt;
    private PlayerView playerView;
    private ProgressBar progressBar;
    private static SimpleExoPlayer simpleExoPlayer;



    public HomeFragment() {
    }



    @Override
    public void onPause() {
        super.onPause();

        if (PlayVideo.getSimpleExoPlayer() != null) {
            PlayVideo.getSimpleExoPlayer().setPlayWhenReady(false);
            PlayVideo.getSimpleExoPlayer().getPlaybackState();
            Log.d("abcxyz", "onPause: if");
        } else Log.d("abcxyz", "onPause: else");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void loadVideo(String url) {
        PlayVideo.LoadVideo(playerView, progressBar, getContext(), url);
    }


    @Override
    public void init(View view) {
        recyclerViewMem = view.findViewById(R.id.listLesson);
        recyclerView = view.findViewById(R.id.recyclerview);
        avt = view.findViewById(R.id.avt);
        username = view.findViewById(R.id.username);
        nlp = view.findViewById(R.id.NLP);
        training = view.findViewById(R.id.Training);
        course = view.findViewById(R.id.Course);
        conversation = view.findViewById(R.id.Conversation);
        pronunciation = view.findViewById(R.id.Pronunciation);
        tutorial = view.findViewById(R.id.Tutorial);
        seeall = view.findViewById(R.id.seeall);
        showBottomBar(1, R.drawable.backg);
        playerView = view.findViewById(R.id.player_view);
        progressBar = view.findViewById(R.id.progress_bar);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        seeall.setOnClickListener(v -> navigateAllArtist());

        pronunciation.setOnClickListener(v -> navigatePronunciation());

        avt.setOnClickListener(v -> navigateSetting());

        url = "http://14.177.235.12:8882/media/video/premium/cc822a26-9e5a-4334-aa5e-3ba1f76b2009-Natural Language Processing In 10 Minutes - NLP Tutorial For Beginners - NLP Training - Edureka.mp4";
        loadVideo(url);
        getPresenter().User();
        getPresenter().Artists();
        getPresenter().Categories();
        getPresenter().MemberLessons();
    }

    @Override
    public void loadArtists(List<Artist> list) {
        List<Artist> list6 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list6.add(list.get(i));
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        artistAdapter = new ArtistAdapter(this.getContext(), list6, id -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utilities.ID_ARTIST, id);
            navigate(AppNavigator.ROUTE_ARTIST_DETAIL, bundle);
        });
        recyclerView.setAdapter(artistAdapter);
        artistAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadCategories(List<CategoryDTO> list) {

        String Nlp = "", train = "", cour = "", conver = "", pronun = "", tuto = "";
        for (CategoryDTO c : list) {
            if (c.getName().equals("NLP Knowledge Base"))
                Nlp = BuildConfig.BASE_URL + c.getIconUrl();
            if (c.getCategoryType() == CategoryType.TRAINING_ROUTE)
                train = BuildConfig.BASE_URL + c.getIconUrl();
            if (c.getCategoryType() == CategoryType.TUTORIAL)
                tuto = BuildConfig.BASE_URL + c.getIconUrl();
            if (c.getCategoryType() == CategoryType.CONVERSATION)
                conver = BuildConfig.BASE_URL + c.getIconUrl();
            if (c.getCategoryType() == CategoryType.COURSE)
                cour = BuildConfig.BASE_URL + c.getIconUrl();
            if (c.getCategoryType() == CategoryType.PRONUNCIATION)
                pronun = BuildConfig.BASE_URL + c.getIconUrl();
        }
        LoadImage.Load(this.getContext(), Nlp, nlp);
        LoadImage.Load(this.getContext(), conver, conversation);
        LoadImage.Load(this.getContext(), train, training);
        LoadImage.Load(this.getContext(), tuto, tutorial);
        LoadImage.Load(this.getContext(), cour, course);
        LoadImage.Load(this.getContext(), pronun, pronunciation);

    }

    @Override
    public void loadLessonMembers(List<MemberLessonDTO> list) {
        recyclerViewMem.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        memberLessonAdapter = new MemberLessonAdapter(this.getContext(), list);
        recyclerViewMem.setAdapter(memberLessonAdapter);
        memberLessonAdapter.notifyDataSetChanged();

    }

    @Override
    public void loadUser(UserDTO userDTO) {
        SharedPreferenceHelper.setUser(Utilities.SHARE_USER, userDTO);
        username.setText(userDTO.getFirstName() + " " + userDTO.getLastName());
        LoadImage.Load(getContext(), BuildConfig.BASE_URL + userDTO.getImgUrl(), avt);
    }

    @Override
    public void navigatePronunciation() {
        navigate(AppNavigator.ROUTE_PRONUN);
    }

    @Override
    public void navigateSetting() {
        navigate(AppNavigator.ROUTE_SETTINGS);
    }

    @Override
    public void navigateAllArtist() {
        navigate(AppNavigator.ROUTE_ALL_ARTISTS);
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