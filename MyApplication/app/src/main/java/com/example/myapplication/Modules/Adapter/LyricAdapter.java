package com.example.myapplication.Modules.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.Lyric;
import com.example.myapplication.R;

import java.util.List;

public class LyricAdapter extends RecyclerView.Adapter<LyricAdapter.MyViewHolder> {
    private Context context;
    private List<Lyric> mData;
    public int index = -1;

    public interface onClickItem {
        void sendTime(long time);
    }

    private onClickItem onClickItem;

    public LyricAdapter(Context context, List<Lyric> mData, onClickItem onClickItem) {
        this.context = context;
        this.mData = mData;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_lyric, parent, false);
        return new LyricAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getContent());
        if (position == index) {
            holder.name.setTextColor(context.getResources().getColor(R.color.white));
            holder.name.setTextSize(20);

        } else {
            holder.name.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.name.setTextSize(18);
        }
        holder.name.setOnClickListener(v -> onClickItem.sendTime(mData.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textlyric);
        }

    }
}
