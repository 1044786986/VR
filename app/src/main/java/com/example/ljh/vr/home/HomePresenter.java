package com.example.ljh.vr.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BasePresenter implements HomeContract.HomePresenter{
    private HomeContract.HomeView mHomeView;
    private HomeContract.HomeModel mHomeModel;

    private List<HomeRvBean> mDataList;
    private HomeRvAdapter mAdapter;

    public HomePresenter(BaseView baseView) {
        super(baseView);
        mHomeModel = new HomeModel();
        mHomeView = (HomeContract.HomeView) baseView;
    }


    @Override
    public void initRv(RecyclerView recyclerView) {
        mDataList = new ArrayList<>();
        mAdapter = new HomeRvAdapter(mHomeView.getMyContext(),mDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mHomeView.getMyContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        mHomeModel.getData("",new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(checkViewNull()){
                    return;
                }
                mHomeView.hideProgressBar();
//                mHomeView.setHeaderImage();
                HomeResBean homeResBean = (HomeResBean) o;
                List<HomeRvBean> list = homeResBean.getData();
                mDataList.clear();
                if(list != null){
                    for(int i=0;i<list.size();i++){
                        mDataList.add(list.get(i));
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(checkViewNull()){
                    return;
                }
                mHomeView.hideProgressBar();
            }
        });
    }
}
