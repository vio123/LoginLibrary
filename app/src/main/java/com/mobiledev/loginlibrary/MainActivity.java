package com.mobiledev.loginlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.mobiledev.loginlibrary.callbacks.LoginCallBack;
import com.mobiledev.loginlibrary.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginCallBack {
    private final LoginFragment loginFragment = new LoginFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.login,loginFragment);
        ft.commit();
    }

    @Override
    public void onLoginClicked(String email, String password) {
        Log.e("test123","Login Clicked");
    }

    @Override
    public void onForgotClicked() {

    }

    @Override
    public void onSignUpClicked() {

    }

    @Override
    public int changeButtonLoginBackground() {
        return 0;
    }

    @Override
    public int changeLoginBackground() {
        return 0;
    }

    @Override
    public int minLengthPassword() {
        return 0;
    }

    @Override
    public String changeLoginTitle() {
        return null;
    }

    @Override
    public String changeLoginSubTitle() {
        return null;
    }
}