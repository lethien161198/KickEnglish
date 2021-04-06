package com.example.myapplication.Modules.Home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.R;

public class MoreFragment extends FragmentView<MoreConstract.Presenter> implements MoreConstract.View {
    private TextView back, title, save;
    private ImageView pronun, share, account;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        init(view);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public void init(View view) {

        pronun = view.findViewById(R.id.Pronunciation);
        share = view.findViewById(R.id.Share);
        account = view.findViewById(R.id.Account);
        back = view.findViewById(R.id.txtback);
        title = view.findViewById(R.id.txtTitle);
        save = view.findViewById(R.id.save);
        save.setText("");
        back.setText("");
        title.setText(R.string.titlemore);
        pronun.setOnClickListener(v -> navigatePronun());
        account.setOnClickListener(v -> navigateAccount());
        share.setOnClickListener(v -> {
            String s = "";
            share(s);
        });
        showBottomBar(1, R.color.black);
    }

    public void share(String s) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void navigateAccount() {
        navigate(AppNavigator.ROUTE_ACCOUNT);
    }

    public void navigatePronun() {
        navigate(AppNavigator.ROUTE_PRONUN);
    }
}