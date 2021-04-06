package com.example.myapplication.Modules.Home;

import com.example.myapplication.Common.basemvp.MVP;
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.Models.Home.CategoryDTO;
import com.example.myapplication.Models.Home.MemberLessonDTO;
import com.example.myapplication.Models.Setting.UserDTO;

import java.util.List;

public interface HomeFragmentContract {
    interface View extends MVP.View<Presenter> {
        void loadArtists(List<Artist> list);

        void loadCategories(List<CategoryDTO> list);

        void loadLessonMembers(List<MemberLessonDTO> list);

        void loadUser(UserDTO userDTO);

        void navigatePronunciation();

        void navigateSetting();

        void navigateAllArtist();

    }

    interface Presenter extends MVP.Presenter<View, Interactor> {
        void Categories();

        void Artists();

        void MemberLessons();

        void User();

        void onFinishLoadUser(UserDTO result);

        void onFinishLoadCategories(List<CategoryDTO> result);

        void onFinishLoadArtist(List<Artist> result);

        void onFinishLoadLessonMember(List<MemberLessonDTO> result);
    }

    interface Interactor extends MVP.Interactor<Presenter> {
        void getUser();

        void getCategories();

        void getArtists();

        void getLessonMember();
    }

}
