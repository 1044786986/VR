package com.example.ljh.vr.home;

import android.content.Context;
import android.content.Intent;
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
import com.example.ljh.vr.info.InfoActivity;
import com.example.ljh.vr.utils.GlideOptionsUtils;

import java.util.List;

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.ViewHolder>{
    private List<HomeRvBean> mDataList;
    private Context mContext;

    HomeRvAdapter(Context context,List<HomeRvBean> list){
        mDataList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mDataList.get(position).getImgUrl())
                .apply(GlideOptionsUtils.DisableDiskCache)
                .into(holder.imageView);

        holder.tvName.setText(mDataList.get(position).getName());
        holder.tvAddress.setText(mDataList.get(position).getProvince()+mDataList.get(position).getCity()+mDataList.get(position).getDistrict()
                +mDataList.get(position).getDetail());
        /**
         * 判断热度
         */
        int hot = mDataList.get(position).getHot();
        int hotId = mContext.getResources().getIdentifier("hot" + hot,"mipmap",mContext.getPackageName());
        holder.ivHot.setImageResource(hotId);
        /**
         * 添加标签
         */
        holder.linearLayout_label.removeAllViews();
        for(int i=0;i<mDataList.get(position).getLabels().size();i++){
            TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.view_home_label,null);
            tv.setText(mDataList.get(position).getLabels().get(i));
            if(i > 0){
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins((int) mContext.getResources().getDimension(R.dimen.dp_5),0,0,0);
                tv.setLayoutParams(lp);
            }
            holder.linearLayout_label.addView(tv);
            if(i == 3){
                break;
            }
        }
        /**
         * 显示Top排名
         */
        holder.tvNumber.setText(position+1+"");

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,InfoActivity.class);
                intent.putExtra(KeyApp.INTENT_KEY_INFO,mDataList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private ImageView ivHot;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvNumber;
        private LinearLayout linearLayout_label;
        private LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            ivHot = itemView.findViewById(R.id.ivHot);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            item = itemView.findViewById(R.id.item);
            linearLayout_label = itemView.findViewById(R.id.linearLayout_label);
        }
    }
}
