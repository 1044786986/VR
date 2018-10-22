package com.example.ljh.vr.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.ljh.vr.R;

/**
 * 封装显示提示消息的方法
 */
public class ShowTipUtils {
//    private static AlertDialog.Builder alertBuilder;
//    private static AlertDialog alertDialog;
    private static ProgressDialog progressDialog;
    /**
     * showToast
     * @param context
     * @param string
     */
    public static void toastShort(Context context,String string){
        if(context != null){
            Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
        }
    }

    public static void toastLong(Context context,String string){
        if(context != null) {
            Toast.makeText(context, string, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示对话提示框
     * @param context
     * @param string   内容
     * @param callBack 回调
     * @btCount 按钮数量判断是否设置"取消"按钮
     */
    public static void showAlertDialog(Context context,String string,int btCount,final AlertDialogCallback callBack) {
        if (context instanceof Activity) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Theme_AppCompat_Dialog);
            builder.setMessage(string);
            if(btCount >= 1){
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(callBack == null){
                            return;
                        }
                        callBack.positive();
                    }
                });
            }
            //btCount == 2则设置取消按钮
            if (btCount == 2) {
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(callBack == null){
                            return;
                        }
                        callBack.negative();
                    }
                });
            }
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public static void showSetting(final Context context, final AlertDialogCallback callback){
        String string = "应用缺少必要权限,某些功能可能无法使用,请到设置中心授权";
        ShowTipUtils.showAlertDialog(context, string, 2, new ShowTipUtils.AlertDialogCallback() {
            @Override
            public void positive() {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
                callback.positive();
            }

            @Override
            public void negative() {
                callback.negative();
            }
        });
    }

    public interface AlertDialogCallback {
        void positive();
        void negative();
    }

    public static void showProgressDialog(Context context, String message){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgressDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
