package com.example.ljh.vr.share;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseFragment;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.ui.LoadMoreWrapper;
import com.example.ljh.vr.utils.ShowTipUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class SharePresenter extends BasePresenter implements ShareContract.SharePresenter {
    private ShareContract.ShareView mShareView;
    private ShareContract.ShareModel mShareModel;

    private List<ShareBean> mDataList = new ArrayList<>();
    private LoadMoreWrapper mAdapter;
    private RvAdapterShare rvAdapterShare;

    public SharePresenter(BaseView baseView) {
        super(baseView);
        mShareView = (ShareContract.ShareView) baseView;
        mShareModel = new ShareModel();
    }

    @Override
    public void initRvAdapter(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mShareView.getMyContext()));
        rvAdapterShare = new RvAdapterShare(mShareView.getMyContext(),mDataList);
        mAdapter = new LoadMoreWrapper(rvAdapterShare);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getData() {
        mShareModel.getData(new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(checkViewNull()){
                    return;
                }
                mShareView.hideProgressBar();
                List<ShareBean> list = (List<ShareBean>) o;
                mDataList.clear();
                if(list == null){
                    return;
                }
                for (int i=0;i<list.size();i++){
                    mDataList.add(list.get(i));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(checkViewNull()){
                    return;
                }
                mShareView.hideProgressBar();
                ShowTipUtils.toastShort(mShareView.getMyContext(),error);
                mShareView.showNullTip();
            }
        });
    }

    @Override
    public void loadData() {

    }
}
