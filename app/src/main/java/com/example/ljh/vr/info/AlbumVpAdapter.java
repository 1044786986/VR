package com.example.ljh.vr.info;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class AlbumVpAdapter extends PagerAdapter {
    private Context mContext;
//    private List<View> mDataList;
    private SparseArray<View> mDataMap;
    private String[] mTabs;
    private AlbumPresenter mAlbumPresenter;

    AlbumVpAdapter(Context context, SparseArray<View> list,String[] tabs,AlbumPresenter albumPresenter){
        mContext = context;
        mDataMap = list;
        mAlbumPresenter = albumPresenter;
        mTabs = tabs;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        RecyclerView recyclerView = (RecyclerView) mDataMap.valueAt(position);
       if(position == AlbumPresenter.RV_NORMAL){
           GridLayoutManager gl = new GridLayoutManager(mContext,2);
           recyclerView.setLayoutManager(gl);
           mAlbumPresenter.getNormalData(recyclerView);
       }else if(position == AlbumPresenter.RV_VR){
           LinearLayoutManager ll = new LinearLayoutManager(mContext);
           recyclerView.setLayoutManager(ll);
           mAlbumPresenter.getVrData(recyclerView);
       }else if(position == AlbumPresenter.RV_VIDEO){
           GridLayoutManager gl = new GridLayoutManager(mContext,2);
           recyclerView.setLayoutManager(gl);
       }
       container.addView(recyclerView);
        return recyclerView;
    }

    @Override
    public int getCount() {
        return mDataMap.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mDataMap.valueAt(position));
//        mAlbumPresenter.recycleBitmapList(mDataMap.keyAt(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

    public void recycle(){
        mDataMap.clear();
        mDataMap = null;

    }
}
