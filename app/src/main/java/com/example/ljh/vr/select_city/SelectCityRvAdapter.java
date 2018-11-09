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
import com.example.ljh.vr.utils.ThreadPoolUtils;
import com.socks.library.KLog;

import java.util.regex.Pattern;

public class SelectCityRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private final int TYPE_LETTER = 0;
    private final int TYPE_CHINESE = 1;
    private final int TYPE_HEADER = 2;
    private SelectCityContract.OnSelectCityListener mListener;
    private String mLetters[] = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mLetterPos[] = new int[27];

    SelectCityRvAdapter(Context context){
        mContext = context;
        mListener = (SelectCityContract.OnSelectCityListener) context;
        getAllLetterPos();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == TYPE_LETTER){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_city_letter,parent,
                    false);
            return new ViewHolderLetter(view);
        }else if(viewType == TYPE_CHINESE){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_city_chinese,parent,
                    false);
            return new ViewHolderCity(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolderLetter){
            ((ViewHolderLetter)holder).textView.setText(City.citys[position]);
            for(int i=0;i<mLetterPos.length;i++){
                if(mLetterPos[i] == position){
                    mListener.onScrollLetter(i);
                }
            }
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
//        return 50;
        return City.citys.length;
    }

    @Override
    public int getItemViewType(int position) {
        Pattern pattern = Pattern.compile("^[A-Z]");
        if (pattern.matcher(City.citys[position]).matches()){
            return TYPE_LETTER;
        }else{
            return TYPE_CHINESE;
        }
    }

    public int[] getLetterPos(){
        return mLetterPos;
    }

    private void getAllLetterPos(){
        ThreadPoolUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                for(int n=0;n<mLetters.length;n++){
                    boolean exits = false;
                    for(int i=0;i<City.citys.length;i++){
                        if(City.citys[i].equals(mLetters[n])){
                            mLetterPos[n] = i;
                            exits = true;
                            break;
                        }
                    }
                    if(!exits){
                        if(n == 0){
                            mLetterPos[0] = 0;
                        }else{
                            mLetterPos[n] = mLetterPos[n - 1];
                        }
                    }
                }

//                for(int i=0;i<mLetterPos.length;i++){
//                    KLog.i("aaa","pos = "+mLetterPos[i]);
//                }
            }
        });

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
