package com.example.ljh.vr.info;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ljh.vr.R;
import com.example.ljh.vr._application.KeyApp;
import com.example.ljh.vr._base.BasePresenter;
import com.example.ljh.vr._base.BaseView;
import com.example.ljh.vr._base.MyRetrofitCallback;
import com.example.ljh.vr.utils.CompressUtils;
import com.example.ljh.vr.utils.GetUrlImageUtils;
import com.example.ljh.vr.utils.ShowTipUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoPresenter extends BasePresenter implements InfoContract.InfoPresenter {
    private InfoContract.InfoView mInfoView;
    private InfoContract.InfoModel mInfoModel;

    private List<String> mImgUrlList;
    private InfoVpAdapter mInfoVpAdapter;

    private int mCurX = 0;
    private String mCurId;

    public InfoPresenter(BaseView baseView) {
        super(baseView);
        mInfoView = (InfoContract.InfoView) baseView;
        mInfoModel = new InfoModel();
    }

    @Override
    public void initVpAdapter(ViewPager viewPager) {
        mImgUrlList = new ArrayList<>();
        mInfoVpAdapter = new InfoVpAdapter(mInfoView.getMyContext(),mImgUrlList,this);
        viewPager.setAdapter(mInfoVpAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mInfoView.setPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void getData(Intent intent) {
        mCurId = intent.getStringExtra(KeyApp.INTENT_KEY_INFO);
        mInfoModel.getData(mCurId, new MyRetrofitCallback() {
            @Override
            public void onSuccess(Object o) {
                if(checkViewNull()){
                    return;
                }
                mInfoView.hideProgressBar();
                InfoBean infoBean = (InfoBean) o;
                mInfoView.showData(infoBean);

                /**
                 * 解析introduce
                 */
                analysisIntroduce(infoBean.getIntroduce());

                /**
                 * 填入viewPager
                 */
                for(int i=0;i<infoBean.getImgUrls().size();i++){
                    mImgUrlList.add(infoBean.getImgUrls().get(i));
                }
                mInfoVpAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String error) {
                if(checkViewNull()){
                    return;
                }
                mInfoView.hideProgressBar();
            }
        });
    }

    @Override
    public void analysisIntroduce(String introduce) {
         String introduces[] = introduce.split("<img>|</img>");
         List<String[]> introduceList = new ArrayList<>();
         for(String s : introduces){
             String content[] = new String[2];
             content[1] = s;
             if(s.contains("http://") || s.contains("https://")){
//                 content[0] = "img";
                 final ImageView iv = LayoutInflater.from(mInfoView.getMyContext()).
                         inflate(R.layout.iv_info_introduce,null,false).findViewById(R.id.imageView);

                 mInfoView.addIntroduce(iv);
                 GetUrlImageUtils.getUrlImage(s, new GetUrlImageUtils.UrlImageCallback() {
                     @Override
                     public void onSuccess(byte[] bytes) {
                         iv.setImageBitmap(CompressUtils.getInstance(mInfoView.getMyContext()).compress(bytes,iv.getWidth(),iv.getHeight(),0));
                     }

                     @Override
                     public void onFailed(String error) {

                     }
                 });
             }else{
//                 content[0] = "text";
                 TextView tv = LayoutInflater.from(mInfoView.getMyContext()).
                         inflate(R.layout.tv_info_intruduce,null,false).findViewById(R.id.textView);
                 tv.setText(s);
                 mInfoView.addIntroduce(tv);
             }
//             introduceList.add(content);
         }

//         for(String[] s : introduceList){
//
//         }
    }

    @Override
    public void scrollViewListener(int newY, int oldY) {
        float difference = newY - oldY;
        float alpha = mInfoView.getHeader().getAlpha();
        if(mCurX < mInfoView.getHeader().getHeight() && difference > 0){
            mInfoView.getHeader().setAlpha(alpha+difference/100);
        }
        if(difference < 0){
            mInfoView.getHeader().setAlpha(alpha+difference/100);
        }
        mCurX -= difference;
    }

    @Override
    public void toAlbum() {
        if((mCurId+"").equals("")){
            ShowTipUtils.toastShort(mInfoView.getMyContext(),"暂时没找到资源");
            return;
        }
        Intent intent = new Intent(mInfoView.getMyContext(),AlbumActivity.class);
        intent.putExtra(KeyApp.INTENT_KEY_ALBUM,mCurId);
        mInfoView.getMyContext().startActivity(intent);
    }
}
