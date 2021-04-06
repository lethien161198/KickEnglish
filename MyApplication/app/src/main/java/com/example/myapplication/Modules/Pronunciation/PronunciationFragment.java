package com.example.myapplication.Modules.Pronunciation;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.Modules.Adapter.PronunAdapter;
import com.example.myapplication.Modules.Home.HomeActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PronunciationFragment extends FragmentView<PronunContract.Presenter> implements PronunContract.View {
    private RecyclerView listPro;
    private PronunAdapter pronunAdapter;
    private ImageView imageView, imageViewBack;
    private Spinner spinner;

    private HomeActivity homeActivity;
    private List<MediaDTO> dtoList1 = new ArrayList<>();
    private List<MediaDTO> dtoList2 = new ArrayList<>();
    private List<MediaDTO> dtoList3 = new ArrayList<>();
    private List<MediaDTO> mediaDTOList = new ArrayList<>();

    private TextView back, title, save;

    public PronunciationFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pronunciation;
    }

    @Override
    public void loadMedia(List<MediaDTO> list) {
        mediaDTOList = list;
        for (MediaDTO m : list) {
            if (m.getSkillLevel().equals("Beginner")) {
                dtoList1.add(m);
            }
            if (m.getSkillLevel().equals("Intermediate")) {
                dtoList2.add(m);
            }
            if (m.getSkillLevel().equals("Advanced")) {
                dtoList3.add(m);
            }
        }
        hienthi(list);
    }

    public void hienthi(List<MediaDTO> list) {
        listPro.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        pronunAdapter = new PronunAdapter(this.getContext(), list, mediaDTO -> homeActivity.goToPlayAudioActivity(mediaDTO));
        listPro.setAdapter(pronunAdapter);
        pronunAdapter.notifyDataSetChanged();
    }

    @Override
    public void init(View view) {
        showBottomBar(0, R.drawable.pronun);
        homeActivity = (HomeActivity) getActivity();
        listPro = view.findViewById(R.id.listPronunciation);
        imageView = view.findViewById(R.id.imageSearch);
        imageViewBack = view.findViewById(R.id.imageBack);
        back = view.findViewById(R.id.txtback);
        title = view.findViewById(R.id.txtTitle);
        save = view.findViewById(R.id.save);
        title.setText(R.string.txtPronunciation);
        save.setText("");
        imageView.setBackgroundResource(R.drawable.ic_baseline_search_24);
        imageView.setOnClickListener(view1 -> {
            navigate(AppNavigator.ROUTE_SEARCH);
        });
        imageViewBack.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
            showBottomBar(1, R.drawable.backg);
        });


        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.spinnerName, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:

                        hienthi(dtoList1);
                        break;
                    case 2:

                        hienthi(dtoList2);
                        break;
                    case 3:

                        hienthi(dtoList3);
                        break;
                    case 0:
                        hienthi(mediaDTOList);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getPresenter().listMedia();

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