package com.example.ljh.vr.select_city;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.ui.HeaderAndFooter;
import com.example.ljh.vr.ui.LoadMoreWrapper;
import com.example.ljh.vr.utils.SQLiteHelper;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCityPresenter extends BasePresenter implements SelectCityContract.SelectCityPresenter {
    private SelectCityContract.SelectCityView mSelectCityView;
    private SelectCityContract.SelectCityModel mSelectCityModel;

    private SelectCityRvAdapter mSelectCityRvAdapter;//城市列表适配器
    private HeaderAndFooter mHeaderAndFooterAdapter;//

    private List<Map<String, String>> mHotCityList = new ArrayList<>(); //热门城市
    private List<Map<String, String>> mRecentlyList = new ArrayList<>();//最近访问城市
    private SimpleAdapter mHotCityAdapter;//热门城市gridView适配器
    private SimpleAdapter mRecentlyAdapter;//最近访问城市gridView适配器


    public SelectCityPresenter(BaseView baseView) {
        super(baseView);
        this.mSelectCityView = (SelectCityContract.SelectCityView) baseView;
        mSelectCityModel = new SelectCityModel();
//        mSelectCityListener = (SelectCityContract.OnSelectCityListener) mSelectCityView.getMyContext();
    }


    @Override
    public void initRvAdapter(RecyclerView recyclerView, View recently, View hotCity) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mSelectCityView.getMyContext()));
        mSelectCityRvAdapter = new SelectCityRvAdapter(mSelectCityView.getMyContext());
        mHeaderAndFooterAdapter = new HeaderAndFooter(mSelectCityRvAdapter);
        mHeaderAndFooterAdapter.addHeaderView(recently);
        mHeaderAndFooterAdapter.addHeaderView(hotCity);
//        mSelectCityRvAdapter = new SelectCityRvAdapter(mSelectCityView.getMyContext());
        mHeaderAndFooterAdapter.setChildAdapter(mSelectCityRvAdapter);
        recyclerView.setAdapter(mHeaderAndFooterAdapter);
    }

    /**
     * 初始化最近访问与热门城市
     */
    @Override
    public void initGdAdapter(GridView gdHotCity, GridView gdRecently) {
        mHotCityAdapter = new SimpleAdapter(mSelectCityView.getMyContext(), mHotCityList, R.layout.tv_city_name,
                new String[]{"text"}, new int[]{R.id.tvCity});
        gdHotCity.setAdapter(mHotCityAdapter);
        mRecentlyAdapter = new SimpleAdapter(mSelectCityView.getMyContext(), mRecentlyList, R.layout.tv_city_name,
                new String[]{"text"}, new int[]{R.id.tvCity});
        gdRecently.setAdapter(mRecentlyAdapter);

        gdHotCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KLog.i("aaa","hotcity + " + position);
                String city = mHotCityList.get(position).get("text");
                mSelectCityView.onSelectCity(city);
            }
        });

        gdRecently.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = mRecentlyList.get(position).get("text");
                mSelectCityView.onSelectCity(city);
            }
        });
    }

    /**
     * 列表移动到指定位置
     *
     * @param pos
     * @param recyclerView
     */
    @Override
    public void moveToPos(int pos, RecyclerView recyclerView) {
        int target = mSelectCityRvAdapter.getLetterPos()[pos];
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();
        int offest = lastItem - firstItem - 1;
        if (target > lastItem) {
            target += offest;
        }
        recyclerView.scrollToPosition(target);
    }

    /**
     * 获取热门城市数据
     */
    @Override
    public void getHotCity() {
        mSelectCityModel.getHotCity(new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if (checkViewNull()) {
                    return;
                }
                List<String> list = (List<String>) o;
                mHotCityList.clear();
                for (int i = 0; i < list.size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    map.put("text", list.get(i));
                    mHotCityList.add(map);
                }
                mHotCityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if (checkViewNull()) {
                    return;
                }
            }
        });
    }

    /**
     * 获取最近访问城市
     */
    @Override
    public void getRecentlyCity() {
        mRecentlyList.clear();
        List<String> list = SQLiteHelper.getInstance().getRecentlyCity();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("text", list.get(i));
            mRecentlyList.add(map);
        }
        mRecentlyAdapter.notifyDataSetChanged();
    }

    @Override
    public void addRecentlyCity(String city) {
        mSelectCityModel.addRecentlyCity(city);
    }
}
