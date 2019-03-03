package com.example.ljh.vr.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.utils.GlideOptionsUtils;
import com.example.ljh.vr.utils.RoundTransformation;
import com.example.ljh.vr.utils.glide.CenterCropFactory;
import com.example.ljh.vr.utils.glide.DefaultGlideOptionsFactory;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.socks.library.KLog;

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
        KLog.i("aaa","第"+position+"项");
        final ShareBean shareBean = mDataList.get(position);
        /**
         * 显示头像和相片
         */
        Glide.with(mContext)
                .load(shareBean.getHeaderUrl())
                .apply(DefaultGlideOptionsFactory.getInstance().getGlideOptions().getOptions())
                .into(holder.ivHeader);

        /**
         * 显示第一张图片
         */
        if(shareBean.getImgUrls() != null && shareBean.getImgUrls().size() != 0 && shareBean.getImgUrls().get(0).getSmallUrl() != null){
            holder.imageView.setVisibility(View.VISIBLE);
            String url = shareBean.getImgUrls().get(0).getSmallUrl();
            Glide.with(mContext)
                    .load(url)
                    .apply(GlideOptionsUtils.getInstance().centerAndRound(RoundTransformation.TOP,(int)mContext.getResources().getDimension(R.dimen.dp_10)))
                    .listener(GlidePalette.with(url)
                    .use(GlidePalette.Profile.MUTED_LIGHT)
//                    .intoBackground(holder.linearLayoutShareLocation,GlidePalette.Swatch.RGB))
                            .intoCallBack(new BitmapPalette.CallBack() {
                                @Override
                                public void onPaletteLoaded(@Nullable Palette palette) {
                                    Palette.Swatch swatch = palette.getLightMutedSwatch();
                                    if(swatch != null){
                                        int rgb = swatch.getRgb();
//                                        holder.linearLayoutShareLocation.setBackgroundColor(rgb);
                                        GradientDrawable gradientDrawable = (GradientDrawable) holder.linearLayoutShareLocation.getBackground();
                                        gradientDrawable.setColor(rgb);
                                    }
                                }
                            }))
                    .into(holder.imageView);
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
//        holder.tvTitle.setText(shareBean.getTitle());
        holder.tvLocation.setText(shareBean.getProvince()+shareBean.getCity()+shareBean.getDistrict()+shareBean.getDetail());
//        holder.tvDate.setText(TransformationUtils.getInstance().ymdhsi2ymd(shareBean.getDate()));

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
//        private TextView tvDate;
        private TextView tvLocation;

        private RelativeLayout item;
        private LinearLayout linearLayoutShareLocation;

        public ViewHolder(View view) {
            super(view);
            ivHeader = view.findViewById(R.id.ivHeader);
            imageView = view.findViewById(R.id.imageView);
            tvName = view.findViewById(R.id.tvName);
//            tvTitle = view.findViewById(R.id.tvTitle);
//            tvDate = view.findViewById(R.id.tvDate);
            tvLocation = view.findViewById(R.id.tvLocation);
            item = view.findViewById(R.id.relativeLayout_share);
            linearLayoutShareLocation = view.findViewById(R.id.linearLayout_shareLocation);
        }
    }
}
