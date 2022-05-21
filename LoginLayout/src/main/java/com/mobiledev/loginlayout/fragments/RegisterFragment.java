package com.mobiledev.loginlayout.fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.mobiledev.loginlayout.R;
import com.mobiledev.loginlayout.callbacks.RegisterCallBack;

public class RegisterFragment extends Fragment {
    private RegisterCallBack callBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RegisterCallBack){
            callBack = (RegisterCallBack) context;
        }
    }

    private EditText email,password,cpassword,memberCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        cpassword = view.findViewById(R.id.cpassword);
        memberCode = view.findViewById(R.id.memberCode);
        Button registerBtn = view.findViewById(R.id.registerBtn);
        ImageView backBtn = view.findViewById(R.id.back);
        ConstraintLayout register = view.findViewById(R.id.register);
        TextView title = view.findViewById(R.id.title);
        TextView title1 = view.findViewById(R.id.title1);
        if(callBack.changeRegisterTitle()!=null){
            title.setText(callBack.changeRegisterTitle());
        }
        if(callBack.changeRegisterSubTitle()!=null){
            title1.setText(callBack.changeRegisterSubTitle());
        }
        if(callBack.changeButtonRegisterBackground()!=0)
            registerBtn.setBackgroundResource(callBack.changeButtonRegisterBackground());
        if(callBack.changeRegisterBackground()!=0)
            register.setBackgroundResource(callBack.changeRegisterBackground());
        backBtn.setOnClickListener(view1 -> {
            if(callBack != null){
                callBack.backButtonClicked();
            }
        });
        registerBtn.setOnClickListener(view12 -> {
            int nr = 0;
            if(!password.getText().equals(cpassword.getText())){
                cpassword.setError("It is not same");
                ++nr;
            }
            int minLengthPass = 6;
            if(callBack.minLengthPassword()!=0){
                minLengthPass = callBack.minLengthPassword();
            }
            if(email.getText().length()==0){
                email.setError("Is empty");
                ++nr;
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                email.setError("Invalid email");
                        ++nr;
            }
            if(password.getText().length() == 0){
                password.setError("Is empty");
                        ++nr;
            }
            else if( password.getText().length()<minLengthPass){
                password.setError("Required min " + minLengthPass+ " Length");
                        ++nr;
            }
            if(cpassword.getText().length() == 0){
                cpassword.setError("Is empty");
                        ++nr;
            }
            else if(cpassword.getText().length()< minLengthPass){
                cpassword.setError("Required min "  +  minLengthPass + " Length");
                        ++nr;
            }
            if(nr == 0){
                if(callBack != null){
                    callBack.registerButtonClicked(email.getText().toString(),password.getText().toString(),memberCode.getText().toString());
                }
            }
        });
        return view;
    }
}