package com.example.ljh.vr._base;

import com.example.ljh.vr.login.LoginBean;
import com.example.ljh.vr.login.RegisterResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Retrofit服务
 */
public interface IRetrofit {
    @FormUrlEncoded
    @POST("login.php")
    Observable<LoginBean.LoginResponse> login(@Field("username") String username,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Observable<RegisterResponseBean> register(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("email") String email);

    @FormUrlEncoded
    @POST("msgPassword.php")
    Observable<BaseBean> sendMsg(@Field("username") String username);
}
