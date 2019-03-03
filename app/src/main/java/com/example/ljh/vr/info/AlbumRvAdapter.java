package com.example.ljh.vr.info;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.normal_picture.NormalPictureActivity;
import com.example.ljh.vr.utils.glide.DefaultGlideOptionsFactory;
import com.example.ljh.vr.vr_picture.VrPictureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<AlbumUrlBean> mUrlList;
    /**
     * 改为缓存存储
     */
    private List<Bitmap> mBitmapList;
    private int mType;
    private int mMaxSize = 40;

    AlbumRvAdapter(Context context, List<AlbumUrlBean> list,int type){
        mContext = context;
        mUrlList = list;
        mType = type;
        mBitmapList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (mType) {
            case AlbumPresenter.RV_NORMAL:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_album_normal,parent,false);
                return new VHNormal(view);
            case AlbumPresenter.RV_VR:
                View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_album_vr,parent,false);
                return new VHVr(view1);
            case AlbumPresenter.RV_VIDEO:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (mType) {
            case AlbumPresenter.RV_NORMAL:
                Glide.with(mContext)
                        .load(mUrlList.get(position).getSmallImg())
                        .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                        .into(((VHNormal)holder).imageView);
                setNormalPictureListener(((VHNormal) holder).imageView,position);

//                if(position < mBitmapList.size() && mBitmapList.get(position) != null){
//                    ((VHNormal)holder).imageView.setImageBitmap(mBitmapList.get(position));
//                    return;
//                }
//                GetUrlImageUtils.getUrlImage(mUrlList.get(position).getSmallImg(), new GetUrlImageUtils.UrlImageCallback() {
//                    @Override
//                    public void onSuccess(final byte[] bytes) {
//                        final Bitmap bitmap = CompressUtils.getInstance(mContext).compress
//                                (bytes,((VHNormal)holder).imageView.getWidth(),((VHNormal)holder).imageView.getHeight(),mMaxSize);
//                        mBitmapList.add(bitmap);
//                        ((VHNormal)holder).imageView.setImageBitmap(bitmap);
//                        ((VHNormal)holder).imageView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                AlbumResUrlBean bean = new AlbumResUrlBean();
//                                bean.setData(mUrlList);
//                                Intent intent = new Intent(mContext,NormalPictureActivity.class);
//                                Bundle bundle = new Bundle();
//                                bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST,(Serializable) mUrlList);
////                                bundle.putByteArray(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST,TransformationUtils.getInstance().bitmap2bytes(bitmap));
//                                List<byte[]> list = new ArrayList<>();
//                                for(int i=0;i<mBitmapList.size();i++){
////                                    list.add(TransformationUtils.getInstance().bitmap2bytes(mBitmapList.get(i)));
//                                }
////                                bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE2,(Serializable) list);
//                                bundle.putInt(KeyApp.INTENT_KEY_NORMAL_PICTURE_CUR_POS,position);
//                                intent.putExtras(bundle);
//                                mContext.startActivity(intent);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailed(String error) {
//                        ((VHNormal)holder).imageView.setImageResource(R.mipmap.load_img_failed_gray_300);
//                    }
//                });

//                ((VHNormal)holder).imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        AlbumResUrlBean bean = new AlbumResUrlBean();
//                        bean.setData(mUrlList);
//                        Intent intent = new Intent(mContext,NormalPictureActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST,bean);
//                        bundle.putByteArray(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST,TransformationUtils.getInstance().bitmap2bytes(bitmap));
//                        intent.putExtras(bundle);
//                        mContext.startActivity(intent);
//                    }
//                });
                break;
            case AlbumPresenter.RV_VR:
//                if(position < mBitmapList.size() && mBitmapList.get(position) != null){
//                    ((VHVr)holder).imageView.setImageBitmap(mBitmapList.get(position));
//                    return;
//                }
                Glide.with(mContext)
                        .load(mUrlList.get(position).getSmallImg())
                        .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                        .into(((VHVr)holder).imageView);
                setVrPictureListener(((VHVr)holder).imageView,position);
//                GetUrlImageUtils.getUrlImage(mUrlList.get(position).getSmallImg(), new GetUrlImageUtils.UrlImageCallback() {
//                    @Override
//                    public void onSuccess(byte[] bytes) {
//                        Bitmap bitmap = CompressUtils.getInstance(mContext).compress
//                                (bytes,((VHVr)holder).imageView.getWidth(),((VHVr)holder).imageView.getHeight(),mMaxSize);
//                        mBitmapList.add(bitmap);
//                        ((VHVr)holder).imageView.setImageBitmap(bitmap);
//
//                        ((VHVr)holder).imageView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent intent = new Intent(mContext,VrPictureActivity.class);
//                                intent.putExtra(KeyApp.INTENT_KEY_VR_PICTURE,mUrlList.get(position).getNormalImg());
//                                mContext.startActivity(intent);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailed(String error) {
//                        ((VHVr)holder).imageView.setImageResource(R.mipmap.load_img_failed_gray_300);
//                    }
//                });
                break;
            case AlbumPresenter.RV_VIDEO:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mUrlList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 设置普通图片点击监听
     * @param view
     * @param position
     */
    private void setNormalPictureListener(View view,final int position){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlbumResUrlBean bean = new AlbumResUrlBean();
//                bean.setData(mUrlList);
                Intent intent = new Intent(mContext,NormalPictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST,(Serializable) mUrlList);
                bundle.putInt(KeyApp.INTENT_KEY_NORMAL_PICTURE_CUR_POS,position);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    private void setVrPictureListener(View view,final int position){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,VrPictureActivity.class);
                intent.putExtra(KeyApp.INTENT_KEY_VR_PICTURE,mUrlList.get(position).getNormalImg());
                mContext.startActivity(intent);
            }
        });
    }

    public void recycle(){
        for(int i=0;i<mBitmapList.size();i++){
            mBitmapList.get(i).recycle();
        }
        mBitmapList.clear();
    }

    class VHNormal extends RecyclerView.ViewHolder{
        ImageView imageView;

        public VHNormal(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    class VHVr extends RecyclerView.ViewHolder{
        ImageView imageView;
        public VHVr(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    class VHVideo extends RecyclerView.ViewHolder{

        public VHVideo(View itemView) {
            super(itemView);
        }
    }

}
