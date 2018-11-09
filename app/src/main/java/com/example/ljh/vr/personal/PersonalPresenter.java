package com.example.ljh.vr.personal;

import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr.login.LoginBean;
import com.example.ljh.vr.utils.CheckLoginUtils;

public class PersonalPresenter extends BasePresenter implements PersonalContract.PersonalPresenter{
    private PersonalContract.PersonalView personalView;
    private PersonalContract.PersonalModel personalModel;

    PersonalPresenter(BaseView baseView){
        super(baseView);
        this.personalView = (PersonalContract.PersonalView) baseView;
        personalModel = new PersonalModel();
    }

    @Override
    public void recycle() {
        personalView = null;
    }

    @Override
    public boolean checkViewNull() {
        return personalView == null;
    }

    @Override
    public void checkLoginStatus() {
            CheckLoginUtils.getInstance().getCurUserInfo(personalView.getMyContext(), new CheckLoginUtils.GetCurUserInfoCallback() {
                @Override
                public void onSuccess(LoginBean.LoginResponse loginResponse) {
                    String username = loginResponse.getUsername();
                    personalView.hideTvLogin();
                    personalView.showUsername(username);
                    loginResponse = null;
                }

                @Override
                public void onFailed() {
//                    ShowTipUtils.toastShort(personalView.getMyContext(),"获取用户信息失败");
                    personalView.hideUsername();
                    personalView.showTvLogin();
                }
            });
    }
}
