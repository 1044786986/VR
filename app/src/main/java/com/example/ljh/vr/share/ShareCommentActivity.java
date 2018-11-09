package com.example.ljh.vr.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._base.BaseActivity;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr.ui.SlideBack;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareCommentActivity extends BaseActivity implements ShareCommentContract.ShareCommentView,View.OnClickListener {
//    private RecyclerView mRvImage;
    private LinearLayout mLinearLayout_TopImage;
    private TextView tvContent,tvUsername,tvDate,tvThumbUp,tvThumbDown,tvDelete,tvLocation;    //头部的textView
    private ImageView ivHead,ivThumbUp,ivThumbDown;                                 //头部的ImageView

    @BindView(R.id.rvCommunity)
    protected RecyclerView mRvContent;
    /**
     * ivAlbum,ivCamera,ivAddImage,ivDelete;  //相册、相机、添加图片的布局按钮
     */
    @BindView(R.id.ivBack)
    protected ImageView mIvBack;
//    @BindView(R.id.ivDelete)
//    protected ImageView mIvDelete;
    @BindView(R.id.ivAddImage)
    protected ImageView mIvAddImage;
    @BindView(R.id.ivCamera)
    protected ImageView mIvCamera;
    @BindView(R.id.ivAlbum)
    protected ImageView mIvAlbum;
//    @BindView(R.id.ivCommunity)
//    protected ImageView mIvCommunity;                   //显示要发送的图片
    @BindView(R.id.etMessage)
    protected EditText mEtMessage;                     //编辑发送的文字
    @BindView(R.id.ivSend)
    protected ImageView mIvSend;                          //发送按钮
    @BindView(R.id.layout_addImage)
    protected LinearLayout mLayout_addImage;           //相册和相机的布局
    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    private Uri uri;
    private int comment_id = 0;
    private final int TAKE_PHOTO = 0;
    private final int CHOICE_PHOTO = 1;
    private Bitmap bitmap = null;   //要发送的图片

    private ShareCommentPresenter mShareCommentPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_share_comment;
    }

    @Override
    public void initView() {
        SlideBack slideBack = new SlideBack(this);
        slideBack.attach();

        View headView = LayoutInflater.from(this).inflate(R.layout.headview_community,null,false);
        tvContent =  headView.findViewById(R.id.tvContent);
        tvUsername =  headView.findViewById(R.id.tvUserName);
        tvDate = headView.findViewById(R.id.tvDate);
        tvLocation = headView.findViewById(R.id.tvLocation);
//        tvThumbUp =  headView.findViewById(R.id.tvThumbUp);
//        tvThumbDown =  headView.findViewById(R.id.tvThumbDown);
        ivHead = headView.findViewById(R.id.ivHead);
//        ivThumbUp =  headView.findViewById(R.id.ivThumbUp);
//        ivThumbDown = headView.findViewById(R.id.ivThumbDown);
        mLinearLayout_TopImage = headView.findViewById(R.id.linearLayout_topImage);
        mShareCommentPresenter.initRvAdapter(mRvContent,headView);
    }

    @Override
    public void initData() {
        mShareCommentPresenter.getIntent(getIntent());
    }

    @Override
    public BasePresenter bindPresenter() {
        mShareCommentPresenter = new ShareCommentPresenter(this);
        return mShareCommentPresenter;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

//    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearEditTextComment() {
        mEtMessage.setText("");
    }

    @Override
    public int getHeaderWidth() {
        return ivHead.getWidth();
    }

    @Override
    public int getHeaderHeight() {
        return ivHead.getHeight();
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNullTip() {

    }

    @Override
    public void hideNullTip() {

    }

    @Override
    public void myFinish() {
        finish();
    }

    @Override
    public void showTopInfo(ShareBean shareBean) {
        tvContent.setText(shareBean.getContent());
        tvUsername.setText(shareBean.getUsername());
        tvDate.setText(shareBean.getDate());
        tvLocation.setText(shareBean.getProvince()+shareBean.getCity()+shareBean.getDistrict()+shareBean.getDetail());
//        GetUrlImageUtils.getUrlImage(shareBean.getHeaderUrl(), new GetUrlImageUtils.UrlImageCallback() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                ivHead.setImageBitmap(CompressUtils.getInstance(ShareCommentActivity.this).compress(bytes,ivHead.getWidth(),ivHead.getHeight(),30));
//            }
//
//            @Override
//            public void onFailed(String error) {}
//        });
    }

    @Override
    public void showHeaderImage(Bitmap bitmap) {
        ivHead.setImageBitmap(bitmap);
    }

    @Override
    public void addTopImage(View view) {
        mLinearLayout_TopImage.addView(view);
    }

    @Override
    @OnClick({R.id.ivAddImage,R.id.ivSend,R.id.ivBack,R.id.etMessage})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivAddImage:
                hideInputMethod(view);
                showCameraAndAlbum();
                break;
            case R.id.ivSend:
                mShareCommentPresenter.sendComment(mEtMessage.getText()+"");
                break;
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivCamera:
                break;
            case R.id.etMessage:
                hideCameraAndAlbum();
                break;
        }
    }

    /**
     * 打开相机
     */
    public void openCamera(){
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),mShareCommentPresenter.getFileName());
            file.createNewFile();
            if(Build.VERSION.SDK_INT >= 23){
                uri = FileProvider.getUriForFile(this,"com.example.ljh.wechat.fileprovider",file);
            }else{
                uri = Uri.fromFile(file);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(intent,TAKE_PHOTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            bitmap = null;
            bitmap = CompressUtils.getInstance(ShareCommentActivity.this).compress(uri,this,0,0,50);
//            Message message = handler.obtainMessage();
////            message.obj = "getPhotoTrue";
////            message.sendToTarget();
            mShareCommentPresenter.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    hideCameraAndAlbum();
                    showImageAndDelete();
                }
            });
        }
    }

    /**
     * 显示图片和删除按钮
     */
    public void showImageAndDelete(){
//        mLinearLayout_TopImage.setVisibility(View.VISIBLE);
//        mIvDelete.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏图片和删除按钮
     */
    public void hideImageAndDelete(){
//        mLinearLayout_TopImage.setVisibility(View.GONE);
//        mIvDelete.setVisibility(View.GONE);
    }

    /**
     * 显示相机和相册
     */
    public void showCameraAndAlbum() {
        mLayout_addImage.setVisibility(View.VISIBLE);
    }
    /**
     * 隐藏相机和相册
     */
    public void hideCameraAndAlbum(){
        mLayout_addImage.setVisibility(View.GONE);
    }

    @Override
    public void hideInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
