package com.mobiledev.loginlibrary.callbacks;

public interface LoginCallBack {
    void onLoginClicked(String email,String password);
    void onForgotClicked();
    void onSignUpClicked();
    int changeButtonLoginBackground();
    int changeLoginBackground();
    int minLengthPassword();
    String changeLoginTitle();
    String changeLoginSubTitle();
}
