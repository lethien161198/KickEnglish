package com.example.myapplication.Modules.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mData;

    public RecentAdapter(Context mContext, List<String> search) {
        this.mContext = mContext;
        this.mData = search;
    }

    @NonNull
    @Override
    public RecentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_recent, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.search.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView search;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            search = itemView.findViewById(R.id.stringSearch);

        }

    }
}
