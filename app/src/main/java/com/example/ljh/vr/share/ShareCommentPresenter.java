package com.example.ljh.vr.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._application.MyApplication;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.info.AlbumUrlBean;
import com.example.ljh.vr.main.MainPresenter;
import com.example.ljh.vr.normal_picture.NormalPictureActivity;
import com.example.ljh.vr.ui.HeaderAndFooter;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.DateTimeUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.example.ljh.vr.utils.ImageLoade;
import com.example.ljh.vr.utils.ShowTipUtils;
import com.example.ljh.vr.vr_picture.VrPictureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class ShareCommentPresenter extends BasePresenter implements ShareCommentContract.ShareCommentPresenter {
    private ShareCommentContract.ShareCommentView mShareCommentView;
    private ShareCommentContract.ShareCommentModel mShareCommentModel;

    private ShareBean mShareBean;
//    private LoadMoreWrapper mAdapter;
    private RvAdapterShareComment rvAdapterShareComment;
    HeaderAndFooter headerAndFooter;
    private List<ShareCommentBean> mDataList = new ArrayList<>();

    private List<Bitmap> mSendImageList = new ArrayList<>();//评论要发送的图片集合
    private Bitmap mTopHeaderBitmap;

    private static Handler handler = new Handler(Looper.getMainLooper());

    public ShareCommentPresenter(BaseView baseView) {
        super(baseView);
        mShareCommentView = (ShareCommentContract.ShareCommentView) baseView;
        mShareCommentModel = new ShareCommentModel();
    }

    @Override
    public void initRvAdapter(RecyclerView recyclerView, View headerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mShareCommentView.getMyContext()));
        rvAdapterShareComment = new RvAdapterShareComment(mShareCommentView.getMyContext(),mDataList);
        headerAndFooter = new HeaderAndFooter(rvAdapterShareComment);
        headerAndFooter.addHeaderView(headerView);
//        mAdapter = new LoadMoreWrapper(headerAndFooter);
        recyclerView.setAdapter(headerAndFooter);
    }

    @Override
    public void getIntent(Intent intent) {
        mShareBean = (ShareBean) intent.getExtras().getSerializable(KeyApp.INTENT_KEY_SHARE_CONTENT);
        mShareCommentView.showTopInfo(mShareBean);
        getData(mShareBean.getId());
        /**
         * 获取楼主头像
         */
        GetUrlImageUtils.getUrlImage(mShareBean.getHeaderUrl(), new GetUrlImageUtils.UrlImageCallback() {
            @Override
            public void onSuccess(byte[] bytes) {
                mTopHeaderBitmap = CompressUtils.getInstance(mShareCommentView.getMyContext()).compress(bytes,mShareCommentView.getHeaderWidth()
                        ,mShareCommentView.getHeaderHeight(),MyApplication.SHARE_HEADER_SIZE);
                mShareCommentView.showHeaderImage(mTopHeaderBitmap);
            }

            @Override
            public void onFailed(String error) {}
        });

        /**
         * 获取楼主首条的图片集
         */
        if(mShareBean.getImgUrls() == null){
            return;
        }
        for(int i=0;i<mShareBean.getImgUrls().size();i++){
            final int pos = i;
            GetUrlImageUtils.getUrlImage(mShareBean.getImgUrls().get(i).getSmallUrl(), new GetUrlImageUtils.UrlImageCallback() {
                @Override
                public void onSuccess(byte[] bytes) {
                    if (checkViewNull()) {
                        return;
                    }
                    Bitmap bitmap = CompressUtils.getInstance(mShareCommentView.getMyContext()).
                            compress(bytes, 0, 0, MyApplication.NORMAL_SMALL_PICTURE_SIZE);
                    ImageView imageView = LayoutInflater.from(mShareCommentView.getMyContext()).
                            inflate(R.layout.iv_share_comment, null, false).findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);
                    mShareCommentView.addTopImage(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String type = mShareBean.getImgUrls().get(pos).getType();
                            Intent intent;
                            if (type.equals("normal")) {
                                intent = new Intent(mShareCommentView.getMyContext(), NormalPictureActivity.class);
                                List<AlbumUrlBean> list = new ArrayList<>();
                                Bundle bundle = new Bundle();
                                AlbumUrlBean albumUrlBean = new AlbumUrlBean();
                                albumUrlBean.setSmallImg(mShareBean.getImgUrls().get(pos).getSmallUrl());
                                albumUrlBean.setNormalImg(mShareBean.getImgUrls().get(pos).getNormalUrl());
                                list.add(albumUrlBean);
                                bundle.putSerializable(KeyApp.INTENT_KEY_NORMAL_PICTURE_URL_LIST, (Serializable) list);
                                intent.putExtras(bundle);
                                mShareCommentView.getMyContext().startActivity(intent);
                            } else if (type.equals("vr")) {
                                intent = new Intent(mShareCommentView.getMyContext(), VrPictureActivity.class);
                                intent.putExtra(KeyApp.INTENT_KEY_VR_PICTURE, mShareBean.getImgUrls().get(pos).getNormalUrl());
//                                    AlbumUrlBean albumUrlBean = new AlbumUrlBean();
//                                    albumUrlBean.setSmallImg(bean.getImgUrls().get(pos).getSmallUrl());
//                                    albumUrlBean.setNormalImg(bean.getImgUrls().get(pos).getNormalUrl());
//                                    list.add(albumUrlBean);
                                mShareCommentView.getMyContext().startActivity(intent);
                            }
                        }
                    });
                }

                @Override
                public void onFailed(String error) {
                    if(checkViewNull()){
                        return;
                    }
                }
            });
        }
    }

    @Override
    public void getData(String id) {
        mShareCommentModel.getData(id, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(checkViewNull()){
                    return;
                }
                mShareCommentView.hideProgressBar();
                List<ShareCommentBean> list = (List<ShareCommentBean>) o;
                mDataList.clear();
                for(int i=0;i<list.size();i++){
                    mDataList.add(list.get(i));
                }
                headerAndFooter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String error) {
                if(checkViewNull()){
                    return;
                }
                mShareCommentView.hideProgressBar();
                ShowTipUtils.toastShort(mShareCommentView.getMyContext(),error);
                mShareCommentView.showNullTip();
            }
        });
    }

    @Override
    public void sendComment(String text) {
        if(text.equals("")){
            ShowTipUtils.toastShort(mShareCommentView.getMyContext(),"回复内容不能为空");
            return;
        }
        final String string = text.trim();
        mShareCommentView.showProgressBar();
        mShareCommentModel.sendComment(mShareBean.getId(), string, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(checkViewNull()){
                    return;
                }
                mShareCommentView.hideProgressBar();
                mShareCommentView.clearEditTextComment();
                ShowTipUtils.toastShort(mShareCommentView.getMyContext(),"回复成功");
                ShareCommentBean bean = new ShareCommentBean();
                bean.setDate(DateTimeUtils.getDateTime());
                bean.setContent(string);
                bean.setUsername(MainPresenter.curUser);
                bean.setShareId(mShareBean.getId());
                bean.setId(mDataList.get(mDataList.size()-1).getId());
                mDataList.add(bean);
                headerAndFooter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(checkViewNull()){
                    return;
                }
                mShareCommentView.hideProgressBar();
                ShowTipUtils.toastShort(mShareCommentView.getMyContext(),error);
            }
        });
    }

    /**
     * 打开相册
     */
    public void openAlbum(){
        ThemeConfig themeConfig = ThemeConfig.DARK;
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnablePreview(true)
                .setMutiSelectMaxSize(5)
                .setEnableRotate(true)
                .setEnableCamera(true)
                .build();
        ImageLoader imageLoader = new ImageLoade();
        CoreConfig coreConfig = new CoreConfig.Builder(mShareCommentView.getMyContext(),imageLoader,themeConfig)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
        GalleryFinal.openGallerySingle(KeyApp.CHIOSE_PHOTO,functionConfig,onHandlerResultCallback);
    }

    GalleryFinal.OnHandlerResultCallback onHandlerResultCallback = new GalleryFinal.OnHandlerResultCallback() {
        @Override
        public void onHandlerSuccess(int requestCode, List<PhotoInfo> resultList) {
            int width = (int) mShareCommentView.getMyContext().getResources().getDimension(R.dimen.dp_50);
            Bitmap bitmap = CompressUtils.getInstance(mShareCommentView.getMyContext()).compress
                    (resultList.get(0).getPhotoPath(),width,width,40);
            mSendImageList.add(bitmap);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mShareCommentView.hideCameraAndAlbum();
                    mShareCommentView.showImageAndDelete();
                }
            });
        }

        @Override
        public void onHandlerFailure(int requestCode, String errorMsg) {

        }
    };

    public String getFileName(){
        return UUID.randomUUID().toString()+ ".jpg";
    }

    public Handler getHandler(){
        return handler;
    }

    @Override
    protected void recycle() {
        super.recycle();
        handler.removeCallbacksAndMessages(this);
    }
}
