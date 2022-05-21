package com.mobiledev.loginlayout.callbacks;

public interface ForgotCallBack {
    void resetButtonClicked(String email);
    void backButtonClicked();
    int changeButtonResetBackground();
    int changeForgotBackground();
    String changeForgotTitle();
    String changeForgotSubTitle();
}
