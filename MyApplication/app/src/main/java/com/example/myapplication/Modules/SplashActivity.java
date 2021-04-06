package com.example.myapplication.Modules;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Common.SharedPreferenceHelper;
import com.example.myapplication.Common.Utilities;
import com.example.myapplication.Models.BodyResponseDTO;
import com.example.myapplication.Models.Setting.UserDTO;
import com.example.myapplication.Modules.API.ApiUser;
import com.example.myapplication.Modules.API.RetrofitClient;
import com.example.myapplication.Modules.Authorization.MainActivity;
import com.example.myapplication.Modules.Home.HomeActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private ApiUser apiUser = RetrofitClient.createService(ApiUser.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        Handler handler = new Handler();
        handler.postDelayed(() -> checkToken(), 3000);
        // close splash activity
    }

    public void gotoLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void gotoHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    public void navigate(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();

    }

    public void checkToken() {

        String token = SharedPreferenceHelper.getString(Utilities.SHARE_TOKEN);
        Observable<Response<BodyResponseDTO<UserDTO>>> observable = apiUser.getCurrentUser();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    navigate(String.valueOf(response.code()));

                    if (response.code() == 200) {
                        gotoHome();
                        Utilities.token = token;

                    } else gotoLogin();
                });
    }
}
