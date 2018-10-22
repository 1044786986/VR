package com.example.ljh.vr.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr.ui.SlideBack;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView,View.OnClickListener{
    @BindView(R.id.etUsername)
    protected EditText etUsername;
    @BindView(R.id.etPassword)
    protected EditText etPassword;
    @BindView(R.id.etEmail)
    protected EditText etEmail;
    @BindView(R.id.btRegister)
    protected Button btRegister;
    @BindView(R.id.pbRegister)
    protected ProgressBar progressBar;

    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    @Override
    @OnClick(R.id.btRegister)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btRegister:
                registerPresenter.register();
                break;
        }
    }

    private void init(){
        registerPresenter = new RegisterPresenter(this);
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String string) {

    }

    @Override
    public void finishThis() {
        synchronized(this){
            finish();
        }
    }

    @Override
    public RegisterRequestBean getRegisterInfo() {
        RegisterRequestBean registerRequestBean = new RegisterRequestBean();
        registerRequestBean.setUsername(etUsername.getText()+"");
        registerRequestBean.setPassword(etPassword.getText()+"");
        registerRequestBean.setEmail(etEmail.getText()+"");
        return registerRequestBean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.recycle();
        registerPresenter = null;
    }
}
