package com.example.ljh.vr.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ljh.vr.R;
import com.example.ljh.vr.utils.ThreadPoolUtils;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LauncherFragment extends Fragment{
    private Unbinder mUnBinder;
    private Handler handler = new Handler(Looper.getMainLooper());
    private LauncherFinish launcherFinishCallback;
    private boolean isTimerFinish = false;  //是否计时完成

    public interface LauncherFinish{
        void onLauncherFinish();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launcher,container,false);
        mUnBinder = ButterKnife.bind(this,view);
        launcherFinishCallback = (LauncherFinish)getContext();
        startTimer();
        return view;
    }


    private void startTimer(){
        ThreadPoolUtils.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    isTimerFinish = true;
//                    if(MainPresenter.isPermission){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                launcherFinishCallback.onLauncherFinish();
                            }
                        });
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isTimerFinish(){
        return isTimerFinish;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
