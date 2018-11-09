package com.example.ljh.vr.normal_picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ljh.vr.R;
import com.example.ljh.vr.info.AlbumUrlBean;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.example.ljh.vr.utils.TransformationUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NormalPictureVpAdapter extends PagerAdapter {
    private List<AlbumUrlBean> mUrlList;
    private List<Bitmap> mBitmapList = new ArrayList<>();
    private List<byte[]> mBytesList;
    private List<View> mViewList;
    private int mCurPos;
    private Context mContext;
    private NormalPictureContract.OnProgressBarListener mListener;

    NormalPictureVpAdapter(Context context,List<AlbumUrlBean> list,List<byte[]> bytesList,int pos){
        mContext = context;
        mUrlList = list;
        mBitmapList = new ArrayList<>();
        mViewList = new ArrayList<>();
        mBytesList = bytesList;
        mCurPos = pos;
        mListener = (NormalPictureContract.OnProgressBarListener) context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
//        position += mCurPos;
        if(position < mViewList.size() && mViewList.get(position) != null){
            View view = mViewList.get(position);
            container.addView(view);
            return view;
        }
        final View view = LayoutInflater.from(mContext).inflate(R.layout.view_normal_picture,container,false);
        final ImageView imageView = view.findViewById(R.id.imageView);
        final ProgressBar progressBar = view.findViewById(R.id.progressBar);
        container.addView(view);
        mViewList.add(view);
//        if(mBytesList != null && position < mBytesList.size() && mBytesList.get(position) != null){
//            imageView.setImageBitmap(TransformationUtils.getInstance().bytes2bitmap(mBytesList.get(position)));
//        }
        mListener.onShow();
        GetUrlImageUtils.getUrlImage(mUrlList.get(position).getSmallImg(), new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                if(mContext == null){
                    return;
                }
//                progressBar.setVisibility(View.GONE);
                mListener.onHide();
                Bitmap bitmap = CompressUtils.getInstance(mContext).compress(bytes,0,0,0);

                if(bitmap != null){
                    mBitmapList.add(bitmap);
                    imageView.setImageBitmap(bitmap);
                }
            }

            @Override
            public void onFailed(String error) {
                if(mContext == null){
                    return;
                }
                mListener.onHide();
                imageView.setImageResource(R.drawable.load_img_failed_gray_300);
//                progressBar.setVisibility(View.GONE);
            }
        });
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
        container.removeView(mViewList.get(position));
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);

    }

    public void recycler(){
        mViewList.clear();
        mViewList = null;
        for(int i=0;i<mBitmapList.size();i++){
            mBitmapList.get(i).recycle();
        }
        mBitmapList.clear();
        mBitmapList = null;
    }
}
