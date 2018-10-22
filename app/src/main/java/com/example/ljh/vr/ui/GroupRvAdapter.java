package com.example.ljh.vr.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class GroupRvAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public abstract boolean isPinnedPosition(int pos);

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindViewHolder(holder,position);
    }
}
