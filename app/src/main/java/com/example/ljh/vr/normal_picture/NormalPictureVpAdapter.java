package com.example.ljh.vr.normal_picture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ljh.vr.R;
import com.example.ljh.vr.info.AlbumUrlBean;
import com.example.ljh.vr.utils.glide.DefaultGlideOptionsFactory;

import java.util.List;

public class NormalPictureVpAdapter extends PagerAdapter{
    private List<AlbumUrlBean> mUrlList;
    private View[] mViewList;
    private Context mContext;

    NormalPictureVpAdapter(Context context,ViewPager viewPager,List<AlbumUrlBean> list,int pos){
        mContext = context;
        mUrlList = list;
        mViewList = new View[mUrlList.size()];
        viewPager.setCurrentItem(pos,false);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {

        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_normal_picture,container,false);
        final ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(mContext)
                .load(mUrlList.get(position).getNormalImg())
                .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                .into(imageView);
        container.addView(view);
        mViewList[position] = view;
        return view;
    }

    @Override
    public int getCount() {
        return mUrlList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList[position]);
    }

    public void recycler(){
        mViewList = null;
    }

}
