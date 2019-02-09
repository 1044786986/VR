package com.example.ljh.vr.personal;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ljh.vr.R;
import com.example.ljh.vr._application.MyApplication;
import com.example.ljh.vr._base.BaseFragment;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.login.LoginActivity;
import com.example.ljh.vr.setting.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalFragment extends BaseFragment implements PersonalContract.PersonalView,View.OnClickListener{
    @BindView(R.id.tvLogin)
    protected TextView tvLogin;
    @BindView(R.id.tvUsername)
    protected TextView tvUsername;
    @BindView(R.id.linearLayout_sendRecord)
    protected LinearLayout sendRecord;
    @BindView(R.id.linearLayout_senderAddress)
    protected LinearLayout senderAddress;
    @BindView(R.id.linearLayout_addresseeAddress)
    protected LinearLayout addresseeAddress;
    @BindView(R.id.linearLayout_collection)
    protected LinearLayout collection;
    @BindView(R.id.ivSetting)
    protected ImageView ivSetting;
    @BindView(R.id.linearLayout_print)
    protected LinearLayout print;
    @BindView(R.id.linearLayout_myInfo)
    protected LinearLayout myInfo;

    private PersonalPresenter personalPresenter;


    @Override
    @OnClick({R.id.tvLogin,R.id.ivSetting,R.id.linearLayout_sendRecord,R.id.linearLayout_senderAddress,
    R.id.linearLayout_addresseeAddress,R.id.linearLayout_collection,R.id.linearLayout_print,R.id.linearLayout_myInfo})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.tvLogin:
                intent = new Intent(getMyContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ivSetting:
                intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.linearLayout_sendRecord:
//                intent = new Intent(getMyContext(),SendRecordActivity.class);
//                startActivity(intent);
                break;
            case R.id.linearLayout_addresseeAddress:
//                intent = new Intent(getContext(),AddressActivity.class);
//                intent.putExtra(KeyApp.SA_ADDRESS_TYPE,AddressContract.TYPE_ADDRESSEE);
//                intent.putExtra(KeyApp.SA_ADDRESS_FROM,AddressContract.FROM_PERSONAL);
//                startActivity(intent);
                break;
            case R.id.linearLayout_senderAddress:
//                intent = new Intent(getContext(),AddressActivity.class);
//                intent.putExtra(KeyApp.SA_ADDRESS_TYPE,AddressContract.TYPE_SENDER);
//                intent.putExtra(KeyApp.SA_ADDRESS_FROM,AddressContract.FROM_PERSONAL);
//                startActivity(intent);
                break;
            case R.id.linearLayout_collection:
//                intent = new Intent(getContext(), MyCollectionActivity.class);
//                startActivity(intent);
                break;
            case R.id.linearLayout_print:
//                intent = new Intent(getContext(),PrintManagerActivity.class);
//                startActivity(intent);
                break;
            case R.id.linearLayout_myInfo:
//                intent = new Intent(getContext(),MyInfoActivity.class);
//                startActivity(intent);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_personal;
    }

    @Override
    public BasePresenter bindPresenter() {
        personalPresenter = new PersonalPresenter(this);
        return personalPresenter;
    }

    @Override
    public void initView(View view) {
//        ButterKnife.bind(this,view);
    }

    @Override
    public void initData() {
        personalPresenter.checkLoginStatus();
    }

    @Override
    public Context getMyContext() {
        if(getContext() == null){
            return MyApplication.getInstance();
        }
        return getContext();
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void myFinish() {

    }

    @Override
    public void showUsername(String username) {
        tvUsername.setText(username);
        tvUsername.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUsername() {
        tvUsername.setText("");
        tvUsername.setVisibility(View.GONE);
    }

    @Override
    public void showTvLogin() {
        tvLogin.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideTvLogin() {
        tvLogin.setVisibility(View.GONE);
    }

    @Override
    public void checkLoginStatus() {
        if(personalPresenter != null){
            personalPresenter.checkLoginStatus();
        }
    }
}
