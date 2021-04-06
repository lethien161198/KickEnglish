package com.example.myapplication.Modules.Setting;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.basemvp.AppNavigator;
import com.example.myapplication.Common.basemvp.FragmentView;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.Home.HomeActivity;
import com.example.myapplication.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class SettingFragment extends FragmentView<SettingContract.Presenter> implements SettingContract.View {
    private EditText first, last, des, phone;
    private Button change;
    private String lang = "en";
    private TextView username, email, changeLang, save, back, gender, nation, dob;
    private CircleImageView avt;
    private ImageView imageView;
    private TextView free, name;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private static final int GALLER_ACTION_PICK_CODE = 100;
    private Uri uri;
    private int gen = 0;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void init(View view) {
        changeLang = view.findViewById(R.id.changeLang);
        first = view.findViewById(R.id.firstName);
        last = view.findViewById(R.id.lastName);
        gender = view.findViewById(R.id.gender);
        dob = view.findViewById(R.id.dob);
        des = view.findViewById(R.id.des);
        phone = view.findViewById(R.id.phone);
        nation = view.findViewById(R.id.nation);
        username = view.findViewById(R.id.userName);
        email = view.findViewById(R.id.email);
        avt = view.findViewById(R.id.avtUser);
        back = view.findViewById(R.id.txtback);
        name = view.findViewById(R.id.txtName);
        free = view.findViewById(R.id.txtFree);
        save = view.findViewById(R.id.save);
        save.setText(R.string.txtSave);
        change = view.findViewById(R.id.buttonChange);

        imageView = view.findViewById(R.id.imageBack);
        imageView.setBackgroundResource(R.drawable.ic_baseline_arrow_back_ios_24);

        showBottomBar(0, R.color.black);

        dob.setOnClickListener(v -> chooseDob());
        gender.setOnClickListener(v -> chooseGender());
        changeLang.setOnClickListener(v -> chooseLanguage());

        change.setOnClickListener(v -> navigateChangePass());
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();

            showBottomBar(1, R.drawable.backg);
        });
        save.setOnClickListener(v -> saveSuccess());

        avt.setOnClickListener(v -> runtimePermission());
        getPresenter().getUserDTO();
    }

    public void runtimePermission() {
        Dexter.withContext(getContext())
                .withPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                galleryIntent();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLER_ACTION_PICK_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLER_ACTION_PICK_CODE) {
                uri = data.getData();
                avt.setImageURI(uri);
            }
        }
    }

    @Override
    public void loadInfoUser(UserDTO userDTO) {
        first.setText(userDTO.getFirstName());
        last.setText(userDTO.getLastName());
        Integer gen = userDTO.getGender();
        switch (gen) {
            case 0:
                gender.setText(R.string.unknown);
                break;
            case 1:
                gender.setText(R.string.male);
                break;
            case 2:
                gender.setText(R.string.female);
                break;
            case 9:
                gender.setText(R.string.other);
                break;
        }
        dob.setText(userDTO.getDateOfBirth());
        des.setText(userDTO.getSelfIntroduce());
        phone.setText(userDTO.getPhone());
        nation.setText(userDTO.getNation());
        username.setText(userDTO.getUsername());
        email.setText(userDTO.getEmail());
        name.setText(userDTO.getLastName());
        free.setText(userDTO.getSubscriptionPlan().getName());
        changeLang.setText(userDTO.getLanguage());
        LoadImage.Load(getContext(), BuildConfig.BASE_URL + userDTO.getImgUrl(), avt);
    }


    @Override
    public void saveSuccess() {

        //Save Preference
        SharedPreferenceHelper.setString(Utilities.SHARE_LANG, changeLang.getText().toString());
        MultipartBody.Part filePart = null;

        //Save Image
        if (uri != null) {
            File file = new File(getRealPathFromURI(uri));
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse(getActivity().getContentResolver().getType(uri)),
                    file);
            filePart =
                    MultipartBody.Part.createFormData("imgFile", file.getName(), requestBody);
        }
        getPresenter().saveUserDTO(first.getText().toString(), last.getText().toString(), phone.getText().toString(),
                dob.getText().toString(), String.valueOf(gen), nation.getText().toString(), des.getText().toString(), filePart, changeLang.getText().toString());


    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public void chooseLanguage() {
        String[] listItem = {"English", "Vietnamese"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(listItem, -1, (dialog, which) -> {
            if (which == 0) {
                lang = "en";
                changeLang.setText("en");
            } else if (which == 1) {
                lang = "vi";
                changeLang.setText("vi");
            }
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void chooseDob() {
        int mYear, mMonth, mDay;
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, mYear, mMonth, mDay);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
        dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String y = String.valueOf(year);
            String m = String.valueOf(month);
            String d = String.valueOf(dayOfMonth);
            if (month < 10) {

                m = "0" + month;
            }
            if (dayOfMonth < 10) {

                d = "0" + dayOfMonth;
            }
            //String date=y+"-"+m+"-"+d;
            dob.setText(y + "-" + m + "-" + d);
        };
    }

    public void chooseGender() {
        String[] listItem = {"Female", "Male", "Other"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Gender");
        builder.setSingleChoiceItems(listItem, -1, (dialog, which) -> {
            switch (which) {
                case 0:
                    gender.setText(listItem[which]);
                    gen = 2;
                    break;
                case 1:
                    gender.setText(listItem[which]);
                    gen = 1;
                    break;
                case 2:
                    gender.setText(listItem[which]);
                    gen = 9;
                    break;

            }

            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void navigateChangePass() {
        navigate(AppNavigator.ROUTE_CHANGE_PASS);
    }

    @Override
    public void showSuccess(String message) {
        showToast(message);
        Utilities.setLocale(lang, getResources());
        getActivity().finish();
        startActivity(new Intent(getActivity(), HomeActivity.class));
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