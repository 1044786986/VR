package com.example.ljh.vr._base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.ljh.vr.utils.ShowTipUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class PermissionManagerActivity extends StatusBarActivity{
    public static final int RESULT_CODE = 10000;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 0;
    public String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final int permissionCodes[] = {CODE_WRITE_EXTERNAL_STORAGE};
    private PermissionResultCallback permissionResultCallback;

    public void applyPermission(String permissions[],PermissionResultCallback callback){
        permissionResultCallback = callback;
        permissions = checkPermissions(permissions);
        if(permissions != null){
            ActivityCompat.requestPermissions(this,permissions,RESULT_CODE);
        }else{
            callback.onSuccess();
        }
    }

    public void applyNecessaryPermission(PermissionResultCallback callback){
        permissionResultCallback = callback;
        permissions = checkPermissions(permissions);
        if (permissions != null) {
            ActivityCompat.requestPermissions(this, permissions, RESULT_CODE);
        } else {
            permissionResultCallback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RESULT_CODE){
            if(!checkPermissionResult(grantResults)){
                String string = "应用缺少必要权限,某些功能可能无法使用,请到设置中心授权";
                ShowTipUtils.showAlertDialog(this, string, 2, new ShowTipUtils.AlertDialogCallback() {
                    @Override
                    public void positive() {
                        toSetting();
                    }

                    @Override
                    public void negative() {
                        permissionResultCallback.onFailed();
                    }
                });
            }else{
                permissionResultCallback.onSuccess();
            }
        }
    }

    private void toSetting(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    private String[] checkPermissions(String permissions[]){
        List<String> list = new ArrayList<>();
        for(String s:permissions){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ContextCompat.checkSelfPermission(this,s) != PackageManager.PERMISSION_GRANTED){
                    list.add(s);
//                    return false;
                }
            }
        }
        if(list.size() == 0){
            return null;
        }

        String temPermission[] = new String[list.size()];
        for(int i=0;i<list.size();i++){
            temPermission[i] = list.get(i);
            KLog.i("temPermission = " + temPermission[i]);
//            permissions = temPermission;
        }
        return temPermission;
    }

    private boolean checkPermissionResult(int grantResults[]){
        if(grantResults.length <= 0){
            return false;
        }
        for(int grant:grantResults){
            if(grant != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public interface PermissionResultCallback {
        void onSuccess();
        void onFailed();
    }
}
