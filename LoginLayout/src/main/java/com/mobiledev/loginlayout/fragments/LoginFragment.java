package com.mobiledev.loginlayout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.mobiledev.loginlayout.R;
import com.mobiledev.loginlayout.callbacks.LoginCallBack;


public class LoginFragment extends Fragment {
    private LoginCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginCallBack){
            callBack = (LoginCallBack) context;
        }
    }

    private EditText email,password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginBtn = view.findViewById(R.id.loginBtn);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        TextView title = view.findViewById(R.id.title);
        TextView title1 = view.findViewById(R.id.title1);
        ConstraintLayout login = view.findViewById(R.id.login);
        TextView forgot = view.findViewById(R.id.forgot);
        TextView signup = view.findViewById(R.id.signup);
        if(callBack.changeButtonLoginBackground()!=0)
            loginBtn.setBackgroundResource(callBack.changeButtonLoginBackground());
        if(callBack.changeLoginBackground()!=0)
            login.setBackgroundResource(callBack.changeLoginBackground());
        if(callBack.changeLoginTitle()!=null){
            title.setText(callBack.changeLoginTitle());
        }
        if(callBack.changeLoginSubTitle()!=null){
            title1.setText(callBack.changeLoginSubTitle());
        }
        loginBtn.setOnClickListener(view1 -> {
            int nr = 0;
            if(email.getText().length() == 0){
                email.setError("Is empty");
                ++nr;
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                email.setError("Invalid email");
                ++nr;
            }
            int minLengthPass = 6;
            if(callBack.minLengthPassword()!=0){
                minLengthPass = callBack.minLengthPassword();
            }
            if(password.getText().length() == 0){
                password.setError("Is empty");
                ++nr;
            }else if(password.getText().length() < minLengthPass){
                password.setError("Required min "+minLengthPass+" Length");
                ++nr;
            }
            if(nr == 0){
                if(callBack != null){
                    callBack.onLoginClicked(email.getText().toString(),password.getText().toString());
                }
            }
        });
        forgot.setOnClickListener(view12 -> {
            if(callBack != null){
                callBack.onForgotClicked();
            }
        });
        signup.setOnClickListener(view13 -> {
            if(callBack != null){
                callBack.onSignUpClicked();
            }
        });
        return view;
    }
}