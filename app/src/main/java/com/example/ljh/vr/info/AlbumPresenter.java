package com.example.ljh.vr.info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.ShowTipUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumPresenter extends BasePresenter implements AlbumContract.AlbumPresenter {
    private AlbumContract.AlbumView mAlbumView;
    private AlbumContract.AlbumModel mAlbumModel;

    private AlbumVpAdapter mVpAdapter;
//    private AlbumRvAdapter mRvAdapter;
    private SparseArray<View> mRvMap;
    private String mTabs[] = {"普通图","VR图","视频"};
    private String mId = "0";

    public static final int RV_NORMAL = 0;
    public static final int RV_VR = 1;
    public static final int RV_VIDEO = 2;

//    private List<String> mRvNormalList;
//    private List<String> mRvVrList;

    public AlbumPresenter(BaseView baseView) {
        super(baseView);
        mAlbumView = (AlbumContract.AlbumView) baseView;
        mAlbumModel = new AlbumModel();
    }

    @Override
    public void initVpAdapter(ViewPager viewPager, TabLayout tabLayout) {
        mVpAdapter = new AlbumVpAdapter(mAlbumView.getMyContext(),mRvMap,mTabs,this);
        viewPager.setAdapter(mVpAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initRvAdapter(RecyclerView rvNormal, RecyclerView rvVr, RecyclerView rvVideo) {
//        int color = mAlbumView.getMyContext().getResources()
        mRvMap = new SparseArray<>();
        mRvMap.put(RV_NORMAL,rvNormal);
        mRvMap.put(RV_VR,rvVr);
        mRvMap.put(RV_VIDEO,rvVideo);
    }

    @Override
    public void getNormalData(RecyclerView recyclerView) {
        final List<AlbumUrlBean> urlList = new ArrayList<>();
        final AlbumRvAdapter adapter = new AlbumRvAdapter(mAlbumView.getMyContext(),urlList,RV_NORMAL);
        recyclerView.setAdapter(adapter);
        mAlbumView.showProgressBar();
        mAlbumModel.getNormalData(mId, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(mAlbumView == null){
                    return;
                }
                mAlbumView.hideProgressBar();
                List<AlbumUrlBean> list = (List<AlbumUrlBean>) o;
                for(int i=0;i<list.size();i++){
                    urlList.add(list.get(i));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(mAlbumView == null){
                    return;
                }
                mAlbumView.hideProgressBar();
                ShowTipUtils.toastLong(mAlbumView.getMyContext(),error);
            }
        });
    }

    @Override
    public void getVrData(RecyclerView recyclerView) {
        final List<AlbumUrlBean> urlList = new ArrayList<>();
        final AlbumRvAdapter adapter = new AlbumRvAdapter(mAlbumView.getMyContext(),urlList,RV_VR);
        recyclerView.setAdapter(adapter);
        mAlbumView.showProgressBar();
        mAlbumModel.getVrData(mId, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(mAlbumView == null){
                    return;
                }
                mAlbumView.hideProgressBar();
                List<AlbumUrlBean> list = (List<AlbumUrlBean>) o;
                for(int i=0;i<list.size();i++){
                    urlList.add(list.get(i));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(mAlbumView == null){
                    return;
                }
                mAlbumView.hideProgressBar();
                ShowTipUtils.toastLong(mAlbumView.getMyContext(),error);
            }
        });
    }

    @Override
    public void getVideoData() {

    }

    @Override
    public void getIntent(Intent intent) {
        mId = intent.getStringExtra(KeyApp.INTENT_KEY_ALBUM);
    }

//    @Override
//    public void recycleBitmapList(int type) {
//        switch (type){
//            case RV_NORMAL:
//                if(mRvNormalList != null){
//                    for(int i=0;i<mRvNormalList.size();i++){
//                        mRvNormalList.get(i).recycle();
//                    }
//                    mRvNormalList.clear();
//                }
//                break;
//            case RV_VR:
//                if(mRvVrList != null){
//                    for(int i=0;i<mRvVrList.size();i++){
//                        mRvVrList.get(i).recycle();
//                    }
//                    mRvVrList.clear();
//                }
//                break;
//            case RV_VIDEO:
//                break;
//        }
//    }

    @Override
    protected void recycle() {
        super.recycle();
        mRvMap.clear();
    }
}
