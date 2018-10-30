package com.example.ljh.vr.select_city;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr.data.City;

import java.util.regex.Pattern;

public class SelectCityRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private final int TYPE_LETTER = 0;
    private final int TYPE_CHINESE = 1;
    private SelectCityContract.OnSelectCityListener mListener;

    SelectCityRvAdapter(Context context){
        mContext = context;
        mListener = (SelectCityContract.OnSelectCityListener) context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == TYPE_LETTER){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_city_letter,parent,
                    false);
            return new ViewHolderLetter(view);
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_city_chinese,parent,
                    false);
            return new ViewHolderCity(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolderLetter){
            ((ViewHolderLetter)holder).textView.setText(City.citys[position]);
        }else{
            ((ViewHolderCity)holder).textView.setText(City.citys[position]);
            ((ViewHolderCity)holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onSelect(City.citys[position]);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return City.citys.length;
    }

    @Override
    public int getItemViewType(int position) {
        Pattern pattern = Pattern.compile("[A-Z]^");
        if (pattern.matcher(City.citys[position]).matches()){
            return TYPE_LETTER;
        }else{
            return TYPE_CHINESE;
        }
    }

    public class ViewHolderCity extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolderCity(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    public class ViewHolderLetter extends RecyclerView.ViewHolder{
        TextView textView;

        public ViewHolderLetter(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
