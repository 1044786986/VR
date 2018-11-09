package com.example.ljh.vr.normal_picture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr.info.AlbumUrlBean;
import com.example.ljh.vr.utils.TransformationUtils;

import java.util.List;

public class NormalPicturePresenter extends BasePresenter implements NormalPictureContract.NormalPicturePresenter {
    private NormalPictureContract.NormalPictureView mNormalPictureView;
    private NormalPictureContract.NormalPictureModel mNormalPictureModel;

    private List<AlbumUrlBean> mUrlList;
    private List<byte[]> mBytesList;

    private int mCurPos;
    private Bitmap mSmallBitmap;

    private NormalPictureVpAdapter mVpAdapter;


     public NormalPicturePresenter(BaseView baseView) {
        super(baseView);
        mNormalPictureView = (NormalPictureContract.NormalPictureView) baseView;
        mNormalPictureModel = new NormalPictureModel();
    }

    @Override
    public void getIntent(Intent intent) {
         Bundle bundle = intent.getExtras();
        mUrlList = (List<AlbumUrlBean>) bundle.getSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE);
//        mSmallBitmap = TransformationUtils.getInstance().bytes2bitmap(intent.getExtras().getByteArray(KeyApp.INTENT_KEY_NORMAL_PICTURE));
//        mBytesList = (List<byte[]>) bundle.getSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE2);
        mCurPos = bundle.getInt(KeyApp.INTENT_KEY_NORMAL_PICTURE3);
    }

    @Override
    public void initVpAdapter(ViewPager viewPager) {
        mVpAdapter = new NormalPictureVpAdapter(mNormalPictureView.getMyContext(),mUrlList,mBytesList,mCurPos);
        viewPager.setAdapter(mVpAdapter);
    }

    @Override
    protected void recycle() {
        super.recycle();
        mVpAdapter.recycler();
    }
}
