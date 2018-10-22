package com.example.ljh.vr.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GroupRvDecoration extends RecyclerView.ItemDecoration{
    private Rect mRect;
    private int mPos = -1;
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(parent.getAdapter() instanceof GroupRvAdapter && parent.getChildCount() > 0){
            GroupRvAdapter adapter = (GroupRvAdapter) parent.getAdapter();
            View fistView = parent.getChildAt(0);
            int firstAdapterPosition = parent.getChildAdapterPosition(fistView);
//            int headerPos = ;
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    private int getHeaderViewPos(int adapterFirstVisible,GroupRvAdapter adapter){
        for(int i=adapterFirstVisible;i>=0;i--){
            if(adapter.isPinnedPosition(i)){
                return i;
            }
        }
        return -1;
    }


}
