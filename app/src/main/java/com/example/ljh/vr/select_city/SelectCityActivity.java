package com.example.ljh.vr.select_city;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.AZSideBarView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class SelectCityActivity extends BaseActivity implements SelectCityContract.SelectCityView,
        SelectCityContract.OnSelectCityListener {
    @BindView(R.id.azSideBarView)
    protected AZSideBarView mAZSizeBarView;
    @BindView(R.id.tvLetter)
    protected TextView mTvLetter;
    @BindView(R.id.rvSelectCity)
    protected RecyclerView mRvSelectCity;
//    @BindView(R.id.linearLayout_recently)
//    protected LinearLayout mLinearLayout_recently;
//    @BindView(R.id.linearLayout_hotCity)
//    protected LinearLayout mLinearLayout_hotCity;
//    @BindView(R.id.gdHotCity)
//    protected GridView mGdHotCity;
//    @BindView(R.id.gdRecently)
//    protected GridView mGdRecently;
    @BindView(R.id.ivBack)
    protected ImageView mIvBack;

//    private List<Map<String,Object>> mHotCityList = new ArrayList<>();
//    private List<Map<String,Object>> mRecentlyList = new ArrayList<>();
//
//    private SimpleAdapter mHotCityAdapter;
//    private SimpleAdapter mRecentlyAdapter;
    private SelectCityPresenter mSelectCityPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_select_city;
    }

    @Override
    public void initView() {
//        mRvSelectCity.setNestedScrollingEnabled(false);
        View recentlyView = LayoutInflater.from(this).inflate(R.layout.view_select_city_recently,null,false);
        GridView gdRecently = recentlyView.findViewById(R.id.gdRecently);
//        mLinearLayout_recently = recentlyView.findViewById(R.id.linearLayout_recently);
        View hotCityView = LayoutInflater.from(this).inflate(R.layout.view_select_city_hot,null,false);
        GridView gdHotCity = hotCityView.findViewById(R.id.gdHotCity);
//        mLinearLayout_hotCity = hotCityView.findViewById(R.id.linearLayout_hotCity);
        mSelectCityPresenter.initRvAdapter(mRvSelectCity,recentlyView,hotCityView);
        mSelectCityPresenter.initGdAdapter(gdHotCity,gdRecently);
//        /**
//         * 初始化最近访问与热门城市
//         */
//        mHotCityAdapter = new SimpleAdapter(this,mHotCityList,R.layout.tv_city_name,new String[]{"text"},new int[]{R.id.tvCity});
//        mGdHotCity.setAdapter(mHotCityAdapter);
//        mRecentlyAdapter = new SimpleAdapter(this,mRecentlyList,R.layout.tv_city_name,new String[]{"text"},new int[]{R.id.tvCity});
//        mGdRecently.setAdapter(mRecentlyAdapter);

        mAZSizeBarView.setOnTouchLetterListener(new AZSideBarView.OnTouchLetterListener() {
            @Override
            public void onLetterChange(int pos, String s) {
                if(mTvLetter.getVisibility() != View.VISIBLE){
                    mTvLetter.setVisibility(View.VISIBLE);
                }
                mTvLetter.setText(s);
                mSelectCityPresenter.moveToPos(pos,mRvSelectCity);
            }

            @Override
            public void onLetterSelect(int pos, String s) {
                mTvLetter.setVisibility(View.GONE);
//                mSelectCityPresenter.moveToPos(pos,mRvSelectCity);
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        mSelectCityPresenter.getRecentlyCity();
        mSelectCityPresenter.getHotCity();
    }

    @Override
    public BasePresenter bindPresenter() {
        mSelectCityPresenter = new SelectCityPresenter(this);
        return mSelectCityPresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void hideProgressBar() {

    }


    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void onSelect(String city) {
        Intent intent = new Intent();
        intent.putExtra(KeyApp.RESULT_KEY_SELECT_CITY,city);
        setResult(KeyApp.RESULT_CODE_SELECT_CITY,intent);
        finish();
    }

    @Override
    public void onScrollLetter(int pos) {
        mAZSizeBarView.setCurPos(pos);
    }

    @Override
    public void addHotCityView(List<String> list) {
        int n = 1;
        List<String> tem_list = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            tem_list.add(list.get(i));
            if(i == list.size()-1){
//                addView(tem_list,mLinearLayout_hotCity);
                return;
            }
            if(n == 3){
//                addView(tem_list,mLinearLayout_hotCity);
                tem_list.clear();
                n = 0;
            }else{
//                tem_list.add(list.get(i));
//                n++;
//                if(i == list.size()-1){
//                    addView(tem_list,mLinearLayout_hotCity);
//                }
            }
            n++;
        }
    }

    @Override
    public void addRecentlyCityView(List<String> list) {
//        addView(list,mLinearLayout_recently);
    }

    @Override
    public void addView(List<String> list,LinearLayout ll) {
        int maxWidth = ll.getWidth() / 3;
        KLog.i("aaa","maxWidth = " + maxWidth);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        linearLayout.setBackgroundColor(getColor(Color.BLACK));
//        linearLayout.setBackgroundColor(getResources().getColor(R.color.appTheme));
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        linearLayout.setLayoutParams(layoutParams);
        int tvMargin = (int) getResources().getDimension(R.dimen.dp_5);
        for(int i=0;i<list.size();i++){
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_city_name,null);
            textView.setText(list.get(i));
//            textView.setWidth(maxWidth);
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams
                    (maxWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvLp.setMargins(tvMargin,tvMargin,tvMargin,tvMargin);
            tvLp.weight = 1;
//            tvLp.width = maxWidth;
            textView.setLayoutParams(tvLp);

            linearLayout.addView(textView);
//            textView.requestLayout();
            KLog.i("aaa","textView.weight = " + textView.getWidth() + " " + textView.getHeight());
            KLog.i("aaa","linearLayout.weight = " + linearLayout.getWidth() + " " + linearLayout.getHeight());
            addCitySelectListener(textView,list.get(i));
        }
        ll.addView(linearLayout);
//        linearLayout.requestLayout();
        KLog.i("aaa","ll.weight = " + ll.getWidth() + " " + ll.getHeight());
    }

    @Override
    public void addCitySelectListener(TextView tv, final String city) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelect(city);
            }
        });
    }
}
