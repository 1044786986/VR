package com.example.ljh.vr.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ljh.vr.main.MainActivity;
import com.example.ljh.vr.R;
import com.example.ljh.vr.ui.SlideBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView,View.OnClickListener{
    @BindView(R.id.tvRegister)
    protected TextView tvRegister;
    @BindView(R.id.cbRemember)
    protected CheckBox cbRemember;
    @BindView(R.id.cbAuto)
    protected CheckBox cbAuto;
    @BindView(R.id.etUsername)
    protected EditText etUsername;
    @BindView(R.id.etPassword)
    protected EditText etPassword;
    @BindView(R.id.btLogin)
    protected Button btLogin;
    @BindView(R.id.pbLogin)
    protected ProgressBar progressBar;
    @BindView(R.id.linearLayout_timer)
    protected LinearLayout linearLayout_timer;
    @BindView(R.id.tvTimer)
    protected TextView tvTimer;
    @BindView(R.id.linearLayout_wechat)
    protected LinearLayout linearLayout_wechat;
    @BindView(R.id.linearLayout_msg)
    protected LinearLayout linearLayout_msg;
    @BindView(R.id.ivBack)
    protected ImageView ivBack;

    protected LoginContract.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        loginPresenter.init();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.logo_300);
    }

    @Override
    @OnClick({R.id.btLogin,R.id.tvRegister,R.id.linearLayout_msg,R.id.linearLayout_wechat,R.id.ivBack})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btLogin:
                loginPresenter.login();
                break;
//            case R.id.tvForget:
//                toForgetPassWord();
//                break;
            case R.id.tvRegister:
                toRegister();
                break;
            case R.id.linearLayout_wechat:
                break;
            case R.id.linearLayout_msg:
                loginPresenter.msgLogin(etUsername.getText()+"");
                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public LoginBean getInfo() {
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(etUsername.getText()+"");
        loginBean.setPassword(etPassword.getText()+"");
        return loginBean;
    }

    @Override
    public LoginBean.LoginCheckBox getCheckBox() {
        LoginBean.LoginCheckBox loginCheckBox = new LoginBean.LoginCheckBox();
        loginCheckBox.setRemember(cbRemember.isChecked());
        loginCheckBox.setAuto(cbAuto.isChecked());
        return loginCheckBox;
    }

    @Override
    public void toRegister() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void toForgetPassWord() {
        Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
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
    public void setTimerText(String s) {
        tvTimer.setText(s);
    }

    @Override
    public void timerFinish() {
        linearLayout_timer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTimer() {
        linearLayout_timer.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCbRemember() {
        cbRemember.setChecked(true);
    }

    @Override
    public void setCbAuto() {
        cbAuto.setChecked(true);
    }

    @Override
    public void setUsername(String username) {
        etUsername.setText(username);
    }

    @Override
    public void setPassword(String password) {
        etPassword.setText(password);
    }

    @Override
    public boolean isRemember() {
        return cbRemember.isChecked();
    }

    @Override
    public boolean isAutoLogin() {
        return cbAuto.isChecked();
    }

    private void init(){
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.recycle();
        loginPresenter = null;
    }
}
