package com.example.myapplication.Modules.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.R;

import java.util.List;

public class PronunAdapter extends RecyclerView.Adapter<PronunAdapter.MyViewHolder> {
    private Context mContext;
    private List<MediaDTO> mData;
    private List<MediaDTO> mDataOld;
    private Listener listener;

    public interface Listener {
        void send(MediaDTO mediaDTO);
    }

    public PronunAdapter(Context mContext, List<MediaDTO> mData, Listener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataOld = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PronunAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_pronun, parent, false);
        return new PronunAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PronunAdapter.MyViewHolder holder, int position) {
        String url = BuildConfig.BASE_URL + mData.get(position).getSmallThumbnailUrl();
        LoadImage.Load(mContext, url, holder.img);
        holder.title.setText(mData.get(position).getTitle());
        holder.level.setText(mData.get(position).getSkillLevel());
        holder.img.setOnClickListener(v -> listener.send(mData.get(position)));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        TextView title, level;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageMedia);
            title = itemView.findViewById(R.id.titleMedia);
            level = itemView.findViewById(R.id.levelMedia);

        }
    }
}