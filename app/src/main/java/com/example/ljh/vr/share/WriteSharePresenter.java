package com.example.ljh.vr.share;

import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;

public class WriteSharePresenter extends BasePresenter implements WriteShareContract.WritePresenter {
    private WriteShareContract.WriteShareView mWriteShareView;
    private WriteShareContract.WriteModel mWriteModel;

    public WriteSharePresenter(BaseView baseView) {
        super(baseView);
        mWriteShareView = (WriteShareContract.WriteShareView) baseView;
        mWriteModel = new WriteShareModel();
    }


}
