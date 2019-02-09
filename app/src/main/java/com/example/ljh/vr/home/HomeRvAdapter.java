package com.example.ljh.vr.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr.info.InfoActivity;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.socks.library.KLog;

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
//        KLog.i("aaa","url = " + mDataList.get(position).getImgUrl());
        GetUrlImageUtils.getUrlImage(mDataList.get(position).getImgUrl(), new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(mContext.getResources(),bitmap);
                rbd.setCornerRadius(mContext.getResources().getDimension(R.dimen.dp_5));
//                holder.imageView.setImageBitmap(bitmap);
                holder.imageView.setImageDrawable(rbd);
            }

            @Override
            public void onFailed(String error) {

            }
        });
        holder.tvName.setText(mDataList.get(position).getName());
        holder.tvAddress.setText(mDataList.get(position).getProvince()+mDataList.get(position).getCity()+mDataList.get(position).getDistrict()
                +mDataList.get(position).getDetail());
        /**
         * 判断热度
         */
        int hot = mDataList.get(position).getHot();
        if(hot < 0){
            hot = 0;
        }else if(hot > 5){
            hot = 5;
        }
        int hotId = mContext.getResources().getIdentifier("hot" + hot,"mipmap",mContext.getPackageName());
        holder.ivHot.setImageResource(hotId);
        /**
         * 添加标签
         */
        for(int i=0;i<mDataList.get(position).getLabel().size();i++){
            TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.view_home_label,null);
            tv.setText(mDataList.get(position).getLabel().get(i));
//            tv.setTextSize(mContext.getResources().getDimension(R.dimen.sp_11));
//            tv.setTextColor(Color.WHITE);
//            tv.setBackground(mContext.getResources().getDrawable(R.drawable.bg_home_item_label));
//            int x = (int) mContext.getResources().getDimension(R.dimen.dp_2);
//            tv.setPadding(x,x,x,x);
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
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
        private TextView tvLabel1;
        private TextView tvLabel2;
        private TextView tvLabel3;
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
//            tvLabel1 = itemView.findViewById(R.id.tvLabel1);
//            tvLabel2 = itemView.findViewById(R.id.tvLabel2);
//            tvLabel3 = itemView.findViewById(R.id.tvLabel3);
        }
    }
}
