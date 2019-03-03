package com.example.ljh.vr.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

public class RoundTransformation extends BitmapTransformation {
    public static final String TOP = "top";
    public static final String BOTTOM = "bottom";
    public static final String ALL = "all";

    private float radius = 0f;
    private String type;

    public RoundTransformation() {
        this(ALL,4);
    }

//    public RoundTransformation(Context context,String type){
//        this(context)
//    }

    public RoundTransformation(String type,int px) {
//        super(context);
        this.type = type;
        this.radius = px;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null)
            return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        switch (type){
            case ALL:
                canvas.drawRoundRect(rectF, radius, radius, paint);
                break;
            case TOP:
//                canvas.drawRoundRect(new RectF(0, 0, source.getWidth(), source.getHeight()), paint);
                break;
            case BOTTOM:
                canvas.drawRoundRect(new RectF(0, source.getHeight() - 2 * radius, source.getWidth(), source.getWidth()), radius, radius, paint);
                break;
        }
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
