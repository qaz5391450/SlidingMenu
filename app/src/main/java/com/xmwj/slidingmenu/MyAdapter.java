package com.xmwj.slidingmenu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * created by yhao on 2017/8/18.
 */


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private List<String> mData;
    private Context mContext;

    private SlidingMenu mOpenMenu;

    public void holdOpenMenu(SlidingMenu slidingMenu) {
        mOpenMenu = slidingMenu;
    }

    public void closeOpenMenu() {
        if (mOpenMenu != null && mOpenMenu.isOpen()) {
            mOpenMenu.closeMenu();
        }
    }

    MyAdapter(List<String> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorItem));
        holder.menuText.setText(mData.get(position));
        holder.menuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeOpenMenu();
                boolean top;
                if (holder.menuText.getText().toString().equals("置顶")) {
                    holder.menuText.setText("取消置顶");
                    holder.imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorTopItem));
                    top = true;
                }else{
                    holder.menuText.setText("置顶");
                    holder.imageView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorItem));
                    top = false;
                }
                if (mOnMenuClickListener != null) {
                    mOnMenuClickListener.onClick(position, top);
                }
            }
        });
    }

    public interface OnMenuClickListener {
        void onClick(int position,boolean top);
    }

    private OnMenuClickListener mOnMenuClickListener;

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menuText;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            menuText = (TextView) itemView.findViewById(R.id.menuText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
