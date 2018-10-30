package com.example.ljh.vr.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ljh.vr.R;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(mDataList.get(position).getName());
        holder.tvAddress.setText(mDataList.get(position).getAddress());
        int hot = mDataList.get(position).getHot();
        if(hot < 0){
            hot = 0;
        }else if(hot > 5){
            hot = 5;
        }
        int hotId = mContext.getResources().getIdentifier("hot" + hot,"drawable",mContext.getPackageName());
        holder.ivHot.setImageResource(hotId);

        for(int i=0;i<mDataList.get(position).getLabel().length;i++){
            TextView tv = new TextView(mContext);
            tv.setText(mDataList.get(position).getLabel()[i]);
            tv.setTextSize(mContext.getResources().getDimension(R.dimen.sp_11));
            tv.setTextColor(Color.WHITE);
            tv.setBackground(mContext.getResources().getDrawable(R.drawable.bg_home_item_label));
            int x = (int) mContext.getResources().getDimension(R.dimen.dp_2);
            tv.setPadding(x,x,x,x);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv.getLayoutParams();
            lp.setMargins((int) mContext.getResources().getDimension(R.dimen.dp_5),0,0,0);
            tv.setLayoutParams(lp);
            holder.linearLayout_label.addView(tv);
            if(i == 3){
                break;
            }
        }
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
        private LinearLayout linearLayout_label;
        private TextView tvLabel1;
        private TextView tvLabel2;
        private TextView tvLabel3;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            ivHot = itemView.findViewById(R.id.ivHot);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
//            linearLayout_label = itemView.findViewById(R.id.linearLayout_label);
//            tvLabel1 = itemView.findViewById(R.id.tvLabel1);
//            tvLabel2 = itemView.findViewById(R.id.tvLabel2);
//            tvLabel3 = itemView.findViewById(R.id.tvLabel3);
        }
    }
}
