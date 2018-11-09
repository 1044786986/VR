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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView,View.OnClickListener{
    @BindView(R.id.etUsername)
    protected EditText etUsername;
    @BindView(R.id.etPassword)
    protected EditText etPassword;
    @BindView(R.id.etMsg)
    protected EditText etMsg;
    @BindView(R.id.btMsg)
    protected Button btMsg;
    @BindView(R.id.btRegister)
    protected Button btRegister;
    @BindView(R.id.progressBar)
    protected ProgressBar progressBar;

    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    @Override
    @OnClick({R.id.btRegister,R.id.btMsg})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btRegister:
                registerPresenter.register();
                break;
            case R.id.btMsg:
                registerPresenter.getMsgCode((etUsername.getText()+"").trim());
                break;
        }
    }

    private void init(){
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();
    }

    @Override
    public void setTimerText(String s) {
        btMsg.setText(s+"秒可后重发");
    }

    @Override
    public void timerFinish() {
        btMsg.setText("获取验证码");
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
    public void finishThis() {
        synchronized(this){
            finish();
        }
    }

    @Override
    public RegisterRequestBean getRegisterInfo() {
        RegisterRequestBean registerRequestBean = new RegisterRequestBean();
        registerRequestBean.setUsername((etUsername.getText()+"").trim());
        registerRequestBean.setPassword((etPassword.getText()+"").trim());
        registerRequestBean.setCode((etMsg.getText()+"").trim());
        return registerRequestBean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(registerPresenter != null){
            registerPresenter.recycle();
            registerPresenter = null;
        }
    }
}
