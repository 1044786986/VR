package com.example.ljh.vr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class TransformationUtils {
    private static TransformationUtils mTransformationUtils;
    private static final String[] CHINA_NUMBER = {"零","一","二","三","四","五","六","七","八","九"} ;
    private static final String[] ARAB_NUMBER = {"0","1","2","3","4","5","6","7","8","9"};

    public static TransformationUtils getInstance(){
        synchronized (TransformationUtils.class){
            if(mTransformationUtils == null){
                mTransformationUtils = new TransformationUtils();
            }
        }
        return mTransformationUtils;
    }

    public static String duration2min(long duration){
//        KLog.i(duration+"");
        StringBuilder time = new StringBuilder();
        long second = duration / 1000;
        long minute = 0;
        long hour = 0;
        if(second >= 60){
            minute = second / 60;
            second = second % 60;
        }

        if(minute >= 60){
            hour = minute / 60;
            minute = minute % 60;
        }

        if(hour < 10 && hour > 0){
            time.append("0");
            time.append(hour);
            time.append(":");
        }else if(hour >=10){
            time.append(hour);
            time.append(":");
        }

        if(minute < 10){
            time.append("0");
        }
        time.append(minute);
        time.append(":");

        if(second < 10){
            time.append("0");
        }
        time.append(second);
        return time+"";
    }

    public static String second2min(long second){
        StringBuilder time = new StringBuilder();
        long minute = 0;
        long hour = 0;
        if(second >= 60){
            minute = second / 60;
            second = second % 60;
        }

        if(minute >= 60){
            hour = minute / 60;
            minute = minute % 60;
        }

        if(hour < 10 && hour > 0){
            time.append("0");
            time.append(hour);
            time.append(":");
        }else if(hour >=10){
            time.append(hour);
            time.append(":");
        }

        if(minute < 10){
            time.append("0");
        }
        time.append(minute);
        time.append(":");

        if(second < 10){
            time.append("0");
        }
        time.append(second);
        return time+"";
    }

    public static String ChinaToArabNumber(String s){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){
            for(int j=0;j<CHINA_NUMBER.length;j++){
                if((s.charAt(i)+"").equals(CHINA_NUMBER[j]) || (s.charAt(i)+"").equals(ARAB_NUMBER[j])){
                    result.append(ARAB_NUMBER[j]);
                    break;
                }
            }
        }
        return result+"";
    }

    public static void ChinaToArabNumber(final String s, final OnChinaToArabCallback callback){
        if(mChinaToArabRunnable == null) {
            synchronized (TransformationUtils.class) {
                mChinaToArabRunnable = new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < s.length(); i++) {
                            for (int j = 0; j < CHINA_NUMBER.length; j++) {
                                if ((s.charAt(i) + "").equals(CHINA_NUMBER[j]) || (s.charAt(i) + "").equals(ARAB_NUMBER[j])) {
                                    result.append(ARAB_NUMBER[j]);
                                    break;
                                }
                            }
                        }
                        callback.onSuccess(result+"");
                    }
                };
            }
        }
        ThreadPoolUtils.getInstance().execute(mChinaToArabRunnable);
    }
    private static Runnable mChinaToArabRunnable;

    public interface OnChinaToArabCallback{
        void onSuccess(String result);
    }


    public byte[] bitmap2bytes(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public Bitmap bytes2bitmap(byte bytes[]){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    public String ymdhsi2ymd(String s){
        if(s == null || s == ""){
            return s;
        }
        String strings[] = s.split(" ");
        return strings[0];
    }
}
