package com.example.ljh.vr.utils;

import android.text.TextUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtils {
    private static final String KEY_SHA = "SHA";
    private static final int COUNT = 3;

    public static String ShaEncode(String data){
        BigInteger bigInteger = null;
        try {
            byte dataBytes[] = data.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(dataBytes);
            bigInteger = new BigInteger(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bigInteger+"";
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5s(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String md5 = md5(string);
        for (int i = 0; i < COUNT; i++) {
            md5 = md5(md5);
        }
        return md5(md5);
    }
}
