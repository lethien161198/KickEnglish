package com.example.myapplication.Common.basemvp;

import android.os.Bundle;

import com.example.myapplication.Modules.Artist.AllArtistFragment;
import com.example.myapplication.Modules.Artist.AllArtistInteractor;
import com.example.myapplication.Modules.Artist.AllArtistPresenter;
import com.example.myapplication.Modules.Artist.ArtistDetailFragment;
import com.example.myapplication.Modules.Artist.ArtistDetailInteractor;
import com.example.myapplication.Modules.Artist.ArtistDetailPresenter;
import com.example.myapplication.Modules.Artist.PlayVideoFragment;
import com.example.myapplication.Modules.Artist.PlayVideoInteractor;
import com.example.myapplication.Modules.Artist.PlayVideoPresenter;
import com.example.myapplication.Modules.Authorization.LoginPresenter;
import com.example.myapplication.Modules.Authorization.SignInFragment;
import com.example.myapplication.Modules.Authorization.SignInInteractor;
import com.example.myapplication.Modules.Authorization.SignUpFragment;
import com.example.myapplication.Modules.Authorization.SignUpInteractor;
import com.example.myapplication.Modules.Authorization.SignUpPresenter;
import com.example.myapplication.Modules.Home.Fragment.AccountFragment;
import com.example.myapplication.Modules.Home.Fragment.AccountInteractor;
import com.example.myapplication.Modules.Home.Fragment.AccountPresenter;
import com.example.myapplication.Modules.Home.HomeFragment;
import com.example.myapplication.Modules.Home.HomeFragmentPresenter;
import com.example.myapplication.Modules.Home.HomeInteractor;
import com.example.myapplication.Modules.Pronunciation.PlayAudio.LyricFragment;
import com.example.myapplication.Modules.Pronunciation.PlayAudio.LyricInteractor;
import com.example.myapplication.Modules.Pronunciation.PlayAudio.LyricPresenter;
import com.example.myapplication.Modules.Pronunciation.PronunInteractor;
import com.example.myapplication.Modules.Pronunciation.PronunPresenter;
import com.example.myapplication.Modules.Pronunciation.PronunciationFragment;
import com.example.myapplication.Modules.Pronunciation.SearchFragment;
import com.example.myapplication.Modules.Pronunciation.SearchInteractor;
import com.example.myapplication.Modules.Pronunciation.SearchPresenter;
import com.example.myapplication.Modules.Setting.ChangePasswordFragment;
import com.example.myapplication.Modules.Setting.ChangePasswordInteractor;
import com.example.myapplication.Modules.Setting.ChangePasswordPresenter;
import com.example.myapplication.Modules.Setting.SettingFragment;
import com.example.myapplication.Modules.Setting.SettingInteractor;
import com.example.myapplication.Modules.Setting.SettingPresenter;

public class AppNavigator {
    public static final String ROUTE_SETTINGS = "route_settings";
    public static final String ROUTE_SEARCH = "route_search";
    public static final String ROUTE_ALL_ARTISTS = "route_all_artists";
    public static final String ROUTE_ARTIST_DETAIL = "route_artist_detail";
    public static final String ROUTE_PLAY_VIDEO = "route_play_video";
    public static final String ROUTE_SIGN_IN = "route_sign_in";
    public static final String ROUTE_SIGN_UP = "route_sign_up";
    public static final String ROUTE_HOME = "route_home";
    public static final String ROUTE_ACCOUNT = "route_account";
    public static final String ROUTE_PRONUN = "route_pronun";
    public static final String ROUTE_CHANGE_PASS = "route_change_pass";
    public static final String ROUTE_LYRICS = "route_lyrics";

    public static MVP.View viewWithRoute(String route) {
        return viewWithRoute(route, null);
    }

    public static MVP.View viewWithRoute(String route, Bundle parameters) {
        switch (route) {
            case ROUTE_LYRICS:
                return new LyricPresenter(new LyricFragment(), new LyricInteractor(), parameters).getView();
            case ROUTE_SETTINGS:
                return new SettingPresenter(new SettingFragment(), new SettingInteractor(), parameters).getView();
            case ROUTE_SEARCH:
                return new SearchPresenter(new SearchFragment(), new SearchInteractor(), parameters).getView();
            case ROUTE_ALL_ARTISTS:
                return new AllArtistPresenter(new AllArtistFragment(), new AllArtistInteractor(), parameters).getView();
            case ROUTE_PLAY_VIDEO:
                return new PlayVideoPresenter(new PlayVideoFragment(), new PlayVideoInteractor(), parameters).getView();
            case ROUTE_SIGN_UP:
                return new SignUpPresenter(new SignUpFragment(), new SignUpInteractor(), parameters).getView();
            case ROUTE_ACCOUNT:
                return new AccountPresenter(new AccountFragment(), new AccountInteractor(), parameters).getView();
            case ROUTE_PRONUN:
                return new PronunPresenter(new PronunciationFragment(), new PronunInteractor(), parameters).getView();
            case ROUTE_CHANGE_PASS:
                return new ChangePasswordPresenter(new ChangePasswordFragment(), new ChangePasswordInteractor(), parameters).getView();
            case ROUTE_HOME:
                return new HomeFragmentPresenter(new HomeFragment(), new HomeInteractor(), parameters).getView();
            case ROUTE_ARTIST_DETAIL:
                return new ArtistDetailPresenter(new ArtistDetailFragment(), new ArtistDetailInteractor(), parameters).getView();
            case ROUTE_SIGN_IN:
                return new LoginPresenter(new SignInFragment(), new SignInInteractor(), parameters).getView();
            default:
                break;
        }

        return null;
    }
}
