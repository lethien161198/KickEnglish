package com.example.myapplication.Modules.Setting;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Models.Setting.ChangePasswordDTO;
import com.example.myapplication.R;

public class ChangePasswordFragment extends FragmentView<ChangePasswordContract.Presenter> implements ChangePasswordContract.View {
    private Toolbar toolbar;
    private EditText current, newpass, confirm;
    private Button save;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_change_password;
    }


    @Override
    public void init(View view) {
        save = view.findViewById(R.id.buttonSave);
        current = view.findViewById(R.id.currentpass);
        newpass = view.findViewById(R.id.newpass);
        confirm = view.findViewById(R.id.confirmpass);

        save.setOnClickListener(v -> {
            if (confirm.getText().toString().equals(newpass.getText().toString())) {
                ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(current.getText().toString(), newpass.getText().toString());
                getPresenter().changePass(changePasswordDTO);
            } else Toast.makeText(getContext(), R.string.message, Toast.LENGTH_LONG).show();
        });

        toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        showBottomBar(0, R.color.black);
    }

    public void navigateBack() {
        getActivity().onBackPressed();
    }

    @Override
    public void showSuccess(String message) {
        showToast(message);
        if (message.equals("Success")) {
            navigateBack();
        }
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