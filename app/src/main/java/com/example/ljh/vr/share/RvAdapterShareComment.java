package com.example.ljh.vr.share;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.info.AlbumUrlBean;
import com.example.ljh.vr.normal_picture.NormalPictureActivity;
import com.example.ljh.vr.utils.LruCacheUtils;
import com.example.ljh.vr.utils.ScreenUtils;
import com.example.ljh.vr.utils.glide.DefaultGlideOptionsFactory;
import com.example.ljh.vr.vr_picture.VrPictureActivity;
import com.socks.library.KLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RvAdapterShareComment extends RecyclerView.Adapter<RvAdapterShareComment.ViewHolder> {
    private Context mContext;
    private List<ShareCommentBean> mDataList;

    RvAdapterShareComment(Context context, List<ShareCommentBean> list){
        mContext = context;
        mDataList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_share_comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ShareCommentBean bean = mDataList.get(position);
        /**
         * 显示基本信息
         */
        holder.tvContent.setText(bean.getContent());
        holder.tvDate.setText(bean.getDate());
        holder.tvFloor.setText("#"+(position+2));

        if(bean.getName() != null && bean.getName() != ""){
            holder.tvUserName.setText(bean.getName());
        }else{
            holder.tvUserName.setText(bean.getUsername());
        }

        /**
         * 显示头像
         */
        Glide.with(mContext)
                .load(bean.getHeaderUrl())
                .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                .into(holder.ivHeader);
        /**
         * 显示图片组
         */
        final int childCount = holder.linearLayout_image.getChildCount();
        if(bean.getImgUrls() != null){
            for(int i=0;i<bean.getImgUrls().size();i++){
                final int pos = i;
                final String url = (bean.getImgUrls().get(i).getSmallUrl()+"").equals("")
                        ? bean.getImgUrls().get(i).getNormalUrl() : bean.getImgUrls().get(i).getSmallUrl();
                final Bitmap bitmap = (Bitmap) LruCacheUtils.getInstance().get(url);

                if(childCount > 0){
                    ImageView iv = (ImageView) holder.linearLayout_image.getChildAt(i);
                    if(bitmap != null){
                        iv.setImageBitmap(bitmap);
                        setImageOnClick(iv,bean,pos);
                    }
                }else {
                    final ImageView imageView = LayoutInflater.from(mContext).
                            inflate(R.layout.iv_share_comment,null,false).findViewById(R.id.imageView);
                    KLog.i("aaa","imageView.width = " + imageView.getWidth() + "    " + imageView.getHeight());
                    int width = ScreenUtils.getInstance().getScreenWidth();
                    int height = (int) (width * (2.0/3.0));
//                KLog.i("aaa","height = " + height);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
                    imageView.setLayoutParams(layoutParams);
                    holder.linearLayout_image.addView(imageView);

                    Glide.with(mContext)
                            .load(url)
                            .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                            .into(imageView);
                    setImageOnClick(imageView,bean,pos);
//                    GetUrlImageUtils.getUrlImage(url, new GetUrlImageUtils.UrlImageCallback() {
//                        @Override
//                        public void onSuccess(byte[] bytes) {
//                            Bitmap bitmap1 = CompressUtils.getInstance(mContext).compress(bytes,imageView.getWidth(),imageView.getHeight(),60);
//                            imageView.setImageBitmap(bitmap1);
//                            LruCacheUtils.getInstance().put(url,bitmap1);
//                            setImageOnClick(imageView,bean,pos);
//                        }
//
//                        @Override
//                        public void onFailed(String error) {}
//                    });
                }
                }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private void getNormalImgUrl(){

    }

    private void getVrImgUrl(){

    }

    private void setImageOnClick(ImageView imageView, final ShareCommentBean bean, final int pos){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = bean.getImgUrls().get(pos).getType();
                Intent intent;
                if(type.equals("normal")){
                    intent = new Intent(mContext,NormalPictureActivity.class);
                    List<AlbumUrlBean> list = new ArrayList<>();
                    Bundle bundle = new Bundle();
                    AlbumUrlBean albumUrlBean = new AlbumUrlBean();
                    albumUrlBean.setSmallImg(bean.getImgUrls().get(pos).getSmallUrl());
                    albumUrlBean.setNormalImg(bean.getImgUrls().get(pos).getNormalUrl());
                    list.add(albumUrlBean);
                    bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST, (Serializable) list);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }else if(type.equals("vr")){
                    intent = new Intent(mContext,VrPictureActivity.class);
                    intent.putExtra(KeyApp.INTENT_KEY_VR_PICTURE,bean.getImgUrls().get(pos).getNormalUrl());
//                                    AlbumUrlBean albumUrlBean = new AlbumUrlBean();
//                                    albumUrlBean.setSmallImg(bean.getImgUrls().get(pos).getSmallUrl());
//                                    albumUrlBean.setNormalImg(bean.getImgUrls().get(pos).getNormalUrl());
//                                    list.add(albumUrlBean);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivHeader;
        private TextView tvUserName;
        private TextView tvContent;
        private TextView tvDate;
        private TextView tvFloor;
        private LinearLayout linearLayout_image;

        public ViewHolder(View view) {
            super(view);
            ivHeader = view.findViewById(R.id.ivHeader);
            tvUserName = view.findViewById(R.id.tvUserName);
            tvContent = view.findViewById(R.id.tvContent);
            tvDate = view.findViewById(R.id.tvDate);
            tvFloor = view.findViewById(R.id.tvFloor);
            linearLayout_image = view.findViewById(R.id.linearLayout_image);
        }
    }
}
