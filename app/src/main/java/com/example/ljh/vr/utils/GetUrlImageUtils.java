package com.example.ljh.vr.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取网络图片工具
 */
public class GetUrlImageUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    public interface UrlImageCallback{
        void onSuccess(byte[] bytes);
        void onFailed(String error);
    }

    public static void getUrlImage(final String img_url,final UrlImageCallback urlImageCallback){
        ThreadPoolUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                     HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(img_url).openConnection();
                     httpURLConnection.setReadTimeout(2000);
                     httpURLConnection.setConnectTimeout(2000);
                     httpURLConnection.setRequestMethod("GET");
                    if (httpURLConnection.getResponseCode() == 200) {
                        final InputStream inputStream = httpURLConnection.getInputStream();
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte bytes[] = new byte[1024];
                        int len=0;
                        while((len = inputStream.read(bytes)) != -1){
                            byteArrayOutputStream.write(bytes,0,len);
                        }
                        bytes = null;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                urlImageCallback.onSuccess(byteArrayOutputStream.toByteArray());
                            }
                        });
                        inputStream.close();
                        byteArrayOutputStream.close();
                    }else{
                        final int responseCode = httpURLConnection.getResponseCode();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                urlImageCallback.onFailed("网络错误:"+responseCode);
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        });
    }
}
