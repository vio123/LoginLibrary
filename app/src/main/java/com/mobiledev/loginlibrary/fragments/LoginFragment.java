package com.mobiledev.loginlibrary.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobiledev.loginlibrary.R;
import com.mobiledev.loginlibrary.callbacks.LoginCallBack;

public class LoginFragment extends Fragment {
    private LoginCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof LoginCallBack){
            callBack = (LoginCallBack) context;
        }
    }
    private Button loginBtn;
    private EditText email,password;
    private TextView title,title1,forgot,signup;
    private ConstraintLayout login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn = view.findViewById(R.id.loginBtn);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        title = view.findViewById(R.id.title);
        title1 = view.findViewById(R.id.title1);
        login = view.findViewById(R.id.login);
        forgot = view.findViewById(R.id.forgot);
        signup = view.findViewById(R.id.signup);
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
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nr = 0;
                if(email.getText().equals("")){
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
                if(password.getText().equals("")){
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
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack != null){
                    callBack.onForgotClicked();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callBack != null){
                    callBack.onSignUpClicked();
                }
            }
        });
        return view;
    }
}