package com.example.ljh.vr.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;

public class ShareCommentContract {
    interface ShareCommentView extends BaseView{
        void showTopInfo(ShareBean shareBean);
        void showHeaderImage(Bitmap bitmap);
        void addTopImage(View view);
        void showImageAndDelete();
        void hideImageAndDelete();
        void showCameraAndAlbum();
        void hideCameraAndAlbum();
        void hideInputMethod(View view);
        void showProgressBar();
        void clearEditTextComment();
        int getHeaderWidth();
        int getHeaderHeight();
    }

    interface ShareCommentPresenter {
        void initRvAdapter(RecyclerView recyclerView, View headerView);
        void getIntent(Intent intent);
        void getData(String id);
        void sendComment(String text);
    }

    interface ShareCommentModel {
        void getData(String id, MyRetrofitCallback callback);
        void loadData(String id,String commentId,MyRetrofitCallback callback);
        void sendComment(String share_id,String text,MyRetrofitCallback callback);
    }
}
