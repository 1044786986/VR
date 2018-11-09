package com.example.ljh.vr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;

import com.socks.library.KLog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 压缩图片工具
 */
public class CompressUtils {
    private  final int maxSize = 200;   //限定图片最大200kb
    private final int bitMaxSize = 1000;
    private static final int QUALITY = 50;
    private  int screenWidth;           //屏幕X大小
    private  int screenHeight;          //Y大小
//    private Handler handler = new Handler(Looper.getMainLooper());
    private static CompressUtils compressUtils;

    public CompressUtils(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

    public static CompressUtils getInstance(Context context){
        if(compressUtils == null){
            synchronized(CompressUtils.class){
                compressUtils = new CompressUtils(context);
                return compressUtils;
            }
        }
        return compressUtils;
    }

    public Bitmap compress(Bitmap bitmap,int width,int height,int size){
        if(size == 0){
            size = maxSize;
        }
        if(width == 0){
            width = screenWidth;
        }
        if(height == 0){
            height = screenHeight;
        }
        Log.i("aaa","width = " + width + "  height = " + height + "bitmap = " + bitmap);
        Bitmap bitmapResult = null;
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        int scale = 1;
        int quality = 100;

        if(bWidth > width && bWidth >= bHeight){
            scale = bWidth / width;
        }else if(bHeight > height && bHeight >= bWidth){
            scale = bHeight / height;
        }
        if(scale <= 0){
            scale = 1;
        }
        bitmapResult = Bitmap.createBitmap(bWidth/scale,bHeight/scale,Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapResult);
        Rect rectDst = new Rect(0,0,bWidth/scale,bHeight/scale);
        canvas.drawBitmap(bitmap,null,rectDst,null);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapResult.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length > size){
            byteArrayOutputStream.reset();
            quality -= 10;
            bitmapResult.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
            KLog.i("byteArrayOutputStream = " + byteArrayOutputStream.size());
            if(quality <= QUALITY){
                break;
            }
        }
        if(bitmap != null){
            bitmap.recycle();
        }

        try {
            byteArrayOutputStream.reset();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KLog.i("aaa","compress.size = " + bitmapResult.getByteCount() / 1024);
        return bitmapResult;
    }

    public Bitmap compress(byte[] bytes,int width,int height,int size){
        if(size == 0){
            size = maxSize;
        }
        if(width == 0){
            width = screenWidth;
        }
        if(height == 0){
            height = screenHeight;
        }
        Bitmap bitmapResult = null;
        int quality = 100;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = width;
        options.outHeight = height;
        bitmapResult = BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapResult.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length > size){
            byteArrayOutputStream.reset();
            quality -= 10;
            bitmapResult.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
            KLog.i("byteArrayOutputStream = " + byteArrayOutputStream.size());
            if(quality <= QUALITY){
                break;
            }
        }
        try {
            byteArrayOutputStream.reset();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KLog.i("aaa","compress.size = " + bitmapResult.getByteCount() / 1024);
        return bitmapResult;
    }

    public Bitmap compress(String filePath,int width,int height,int size){
        measure(width,height,size);
        KLog.i("aaa","compress.width = " + width + "    " + height + "  " + size);
//        if(size == 0){
//            size = maxSize;
//        }
//        if(width == 0){
//            width = screenWidth;
//        }
//        if(height == 0){
//            height = screenHeight;
//        }
        Bitmap bitmapResult = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = width;
        options.outHeight = height;
        bitmapResult = BitmapFactory.decodeFile(filePath,options);
        compressQuality(bitmapResult,size);
        return bitmapResult;
    }

    public Bitmap compress(Uri uri,Context context,int width,int height,int size){
        if(uri == null){
            return null;
        }
        Bitmap bitmapResult = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = width;
        options.outHeight = height;
        try {
            bitmapResult = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri),null,options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmapResult;
    }

    private void measure(int width,int height,int size){
        if(size == 0){
            size = maxSize;
        }
        if(width == 0){
            width = screenWidth;
        }
        if(height == 0){
            height = screenHeight;
        }
    }

    private void compressQuality(Bitmap bitmap,int size){
        int quality = 100;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length > size){
            byteArrayOutputStream.reset();
            quality -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,byteArrayOutputStream);
            KLog.i("byteArrayOutputStream = " + byteArrayOutputStream.size());
            if(quality <= QUALITY){
                break;
            }
        }
        try {
            byteArrayOutputStream.reset();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
