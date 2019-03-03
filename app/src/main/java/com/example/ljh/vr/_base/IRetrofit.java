package com.example.ljh.vr._base;

import com.example.ljh.vr.home.HomeResBean;
import com.example.ljh.vr.info.AlbumResUrlBean;
import com.example.ljh.vr.info.InfoBean;
import com.example.ljh.vr.info.InfoResBean;
import com.example.ljh.vr.login.LoginBean;
import com.example.ljh.vr.login.RegisterResponseBean;
import com.example.ljh.vr.select_city.HotCityBean;
import com.example.ljh.vr.share.ShareCommentResBean;
import com.example.ljh.vr.share.ShareResBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Retrofit服务
 */
public interface IRetrofit {

    @GET("hotCity/getHotCity")
    Observable<List<String>> getHotCity();

    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginBean.LoginResponse> login(@Field("username") String username,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Observable<BaseBean> register(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("code") String code);

    @FormUrlEncoded
    @POST("msgPassword.php")
    Observable<BaseBean> sendMsg(@Field("username") String username);


    @GET("home/getRecommendHomeData")
    Observable<HomeResBean> getRecommendData();

    @FormUrlEncoded
    @POST("home.php")
    Observable<HomeResBean> getCityData(@Field("city") String city,@Field("action") String action);

    @FormUrlEncoded
    @POST("home/getHomeDataInfo")
    Observable<InfoResBean> getInfo(@Field("id") int id);

    @FormUrlEncoded
    @POST("home/getAlbumNormalImgUrls")
    Observable<AlbumResUrlBean>getAlbumNormalImgUrls(@Field("id") String id);

    @FormUrlEncoded
    @POST("home/getAlbumVrImgUrls")
    Observable<AlbumResUrlBean>getAlbumVrImgUrls(@Field("id") String id);

    @FormUrlEncoded
    @POST("home/getAlbumVideoUrls")
    Observable<AlbumResUrlBean>getAlbumVideoUrls(@Field("id") String id);

//    @FormUrlEncoded
    @GET("share/getShareData")
    Observable<ShareResBean>getShareData();

    @FormUrlEncoded
    @POST("share/loadShareData")
    Observable<ShareResBean>loadShareData(@Field("shareId") String shareId);

    @FormUrlEncoded
    @POST("share.php")
    Observable<ShareCommentResBean>getShareComment(@Field("action") String action, @Field("share_id") String shareId);

    @FormUrlEncoded
    @POST("share.php")
    Observable<BaseBean>sendShareComment(@Field("action") String action, @Field("share_id") String shareId,
                                         @Field("username") String username,@Field("content") String text);

    @FormUrlEncoded
    @POST("sendVerificationCode.php")
    Observable<BaseBean> sendVerificationCode(@Field("username") String username);


}
