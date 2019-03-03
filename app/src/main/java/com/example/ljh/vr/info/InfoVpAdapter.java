package com.example.ljh.vr.info;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ljh.vr.R;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.example.ljh.vr.utils.GlideOptionsUtils;
import com.example.ljh.vr.utils.glide.CenterCropFactory;
import com.example.ljh.vr.utils.glide.CenterCropOptions;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class InfoVpAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mUrlStringList;
    private List<ImageView> mViewList;
    private InfoPresenter mInfoPresenter;

    InfoVpAdapter(Context context, List<String> list,InfoPresenter infoPresenter){
        mContext = context;
        mUrlStringList = list;
        mViewList = new ArrayList<>();
        mInfoPresenter = infoPresenter;
    }

    @Override
    public int getCount() {
        return mUrlStringList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        if(position < mViewList.size()){
            ImageView imageView = mViewList.get(position);
            container.addView(imageView);
            return imageView;
        }
        final ImageView imageView = (ImageView) LayoutInflater.from(mContext).inflate(R.layout.imageview_info_viewpager,null,false);
        Glide.with(mContext)
                .load(mUrlStringList.get(position))
                .apply(new CenterCropFactory().getGlideOptions().getOptions())
                .into(imageView);
        container.addView(imageView);
        mViewList.add(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoPresenter.toAlbum();
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        if(position < mViewList.size()){
            container.removeView(mViewList.get(position));
//        }
    }

}
