package com.example.ljh.vr.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.example.ljh.vr.utils.TransformationUtils;

import java.util.List;

public class RvAdapterShare extends RecyclerView.Adapter<RvAdapterShare.ViewHolder> {
    private Context mContext;
    private List<ShareBean> mDataList;

    RvAdapterShare(Context context, List<ShareBean> list){
        mContext = context;
        mDataList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_share,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ShareBean shareBean = mDataList.get(position);
        /**
         * 显示头像和相片
         */
        GetUrlImageUtils.getUrlImage(shareBean.getHeaderUrl(), new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                if(mContext == null){
                    return;
                }
                holder.ivHeader.setImageBitmap(CompressUtils.getInstance(mContext).compress
                        (bytes,holder.ivHeader.getWidth(),holder.ivHeader.getHeight(),30));
            }

            @Override
            public void onFailed(String error) {}
        });

        /**
         * 显示第一张图片
         */
        if(shareBean.getImgUrls() != null && shareBean.getImgUrls().size() != 0 && shareBean.getImgUrls().get(0).getSmallUrl() != null){
            holder.imageView.setVisibility(View.VISIBLE);
            GetUrlImageUtils.getUrlImage(shareBean.getImgUrls().get(0).getSmallUrl(), new GetUrlImageUtils.UrlImageCallback() {
                @Override
                public void onSuccess(byte[] bytes) {
                    if(mContext == null){
                        return;
                    }
                    holder.imageView.setImageBitmap(CompressUtils.getInstance(mContext).
                            compress(bytes,holder.imageView.getWidth(),holder.imageView.getHeight(),100));
                }

                @Override
                public void onFailed(String error) {}
            });
        }


        /**
         * 显示名字,判断是否设置了呢称
         */
        if(shareBean.getName() != null && shareBean.getName() != ""){
            holder.tvName.setText(shareBean.getName());
        }else{
            holder.tvName.setText(shareBean.getUsername());
        }

        /**
         * 显示基本信息
         */
        holder.tvTitle.setText(shareBean.getTitle());
        holder.tvLocation.setText(shareBean.getProvince()+shareBean.getCity()+shareBean.getDistrict()+shareBean.getDetail());
        holder.tvDate.setText(TransformationUtils.getInstance().ymdhsi2ymd(shareBean.getDate()));

        /**
         * 跳转
         */
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ShareCommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KeyApp.INTENT_KEY_SHARE_CONTENT,shareBean);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivHeader;
        private ImageView imageView;
        private TextView tvName;
        private TextView tvTitle;
        private TextView tvDate;
        private TextView tvLocation;

        private LinearLayout item;

        public ViewHolder(View view) {
            super(view);
            ivHeader = view.findViewById(R.id.ivHeader);
            imageView = view.findViewById(R.id.imageView);
            tvName = view.findViewById(R.id.tvName);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDate = view.findViewById(R.id.tvDate);
            tvLocation = view.findViewById(R.id.tvLocation);
            item = view.findViewById(R.id.linearLayout_share);
        }
    }
}
