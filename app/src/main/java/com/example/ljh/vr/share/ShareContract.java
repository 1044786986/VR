package com.example.ljh.vr.share;

import android.support.v7.widget.RecyclerView;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class ShareContract {
    interface ShareView extends BaseView {

    }

    interface SharePresenter{
        void initRvAdapter(RecyclerView recyclerView);
        void getData();
        void loadData();
    }

    interface ShareModel{
        void getData(MyRetrofitCallback callback);
        void loadData(String id,MyRetrofitCallback callback);
    }
}
