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
import com.mobiledev.loginlayout.callbacks.ForgotCallBack;

public class ForgotFragment extends Fragment {
    private EditText email;
    private ForgotCallBack callBack;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ForgotCallBack){
            callBack = (ForgotCallBack) context;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot, container, false);
        ImageView backBtn = view.findViewById(R.id.back);
        email = view.findViewById(R.id.email);
        Button resetBtn = view.findViewById(R.id.resetBtn);
        ConstraintLayout forgot = view.findViewById(R.id.forgot);
        TextView title = view.findViewById(R.id.title);
        TextView title1 = view.findViewById(R.id.title1);
        if(callBack.changeForgotTitle()!=null){
            title.setText(callBack.changeForgotTitle());
        }
        if(callBack.changeForgotSubTitle()!=null){
            title1.setText(callBack.changeForgotSubTitle());
        }
        if(callBack.changeButtonResetBackground()!=0)
            resetBtn.setBackgroundResource(callBack.changeButtonResetBackground());
        if(callBack.changeForgotBackground()!=0)
            forgot.setBackgroundResource(callBack.changeForgotBackground());
        backBtn.setOnClickListener(view1 -> {
            if(callBack != null){
                callBack.backButtonClicked();
            }
        });
        resetBtn.setOnClickListener(view1 ->{
            if(email.getText().length() == 0){
                email.setError("Is empty");
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                email.setError("Invalid email");
            }else{
                if(callBack != null){
                    callBack.resetButtonClicked(email.getText().toString());
                }
            }
        });
        return view;
    }
}