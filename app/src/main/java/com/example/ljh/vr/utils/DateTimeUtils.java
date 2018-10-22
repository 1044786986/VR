package com.example.ljh.vr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public static String getDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    public static String[] ymd2md(String string){
        if(string == null){
            return null;
        }
        String date[] = string.split(" ");
        if(date.length < 1){
            return null;
        }
        date[0] = date[0].substring(5);
        date[1] = date[1].substring(0,5);
        return date;
    }
}
