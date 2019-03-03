package com.example.ljh.vr.ui;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljh.vr.utils.CompressUtils;
import com.socks.library.KLog;

public class HeaderAndFooter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int HEADER_KEY = 10000;
    private static final int FOOTER_KEY = 20000;
    private boolean hasMore = true;   // 变量，是否有更多数据
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示

    private SparseArrayCompat<View> headerMap = new SparseArrayCompat<>();
    private SparseArrayCompat<View> footerMap = new SparseArrayCompat<>();

    private RecyclerView.Adapter innerAdapter;

    public HeaderAndFooter(RecyclerView.Adapter adapter){
        innerAdapter = adapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(headerMap.get(viewType) != null){
            return new HeaderViewHolder(headerMap.get(viewType));
        }else if(footerMap.get(viewType) != null){
            return new FooterViewHolder(footerMap.get(viewType));
        }
        return innerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(isHeaderPos(position)){
            return;
        }
        if(isFooterPos(position)){
            return;
        }
        innerAdapter.onBindViewHolder(holder,position-2);
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getFooterCount() + getRealItemCount();
    }

    /**
     * 根据position返回viewType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(isHeaderPos(position)){
            return headerMap.keyAt(position);
        }else if(isFooterPos(position)){
            return footerMap.keyAt(position - getHeaderCount() - getRealItemCount());
        }
        return innerAdapter.getItemViewType(position - getHeaderCount());
    }

    /**
     * 增加头部item
     * @param view
     */
    public void addHeaderView(View view){
        headerMap.put(headerMap.size() + HEADER_KEY,view);
    }

    /**
     * 增加底部Item
     * @param view
     */
    public void addFooterView(View view){
        footerMap.put(footerMap.size() + FOOTER_KEY,view);
    }

    /**
     * 获取头部集合的大小
     * @return
     */
    public int getHeaderCount(){
        return headerMap.size();
    }

    /**
     * 获取底部集合的大小
     * @return
     */
    public int getFooterCount(){
        return footerMap.size();
    }

    /**
     * 判断当前Item是否是头部
     * @param position
     * @return
     */
    private boolean isHeaderPos(int position){
        return position < getHeaderCount();
    }

    /**
     * 判断当前item是否是尾部
     * @param position
     * @return
     */
    private boolean isFooterPos(int position){
        return position >= getHeaderCount() + getRealItemCount();
    }

    /**
     * 获取正常内容的大小
     * @return
     */
    public int getRealItemCount(){
        return innerAdapter.getItemCount();
    }

    public void setChildAdapter(RecyclerView.Adapter adapter){
        innerAdapter = adapter;
    }

    /**
     *  解决网格布局问题
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        innerAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if(headerMap.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }else if(footerMap.get(viewType) != null){
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
