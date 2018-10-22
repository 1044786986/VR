package com.example.ljh.vr._base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ljh.vr.R;
import com.githang.statusbar.StatusBarCompat;

public class StatusBarActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.statusBar));
    }
}
