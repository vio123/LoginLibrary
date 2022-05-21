package com.mobiledev.loginlayout.callbacks;

public interface RegisterCallBack {
    void registerButtonClicked(String email,String password,String memberCode);
    void backButtonClicked();
    int changeButtonRegisterBackground();
    int changeRegisterBackground();
    String changeRegisterTitle();
    String changeRegisterSubTitle();
    int minLengthPassword();
}
