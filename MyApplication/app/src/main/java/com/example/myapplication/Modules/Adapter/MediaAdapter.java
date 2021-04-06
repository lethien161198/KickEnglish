package com.example.myapplication.Modules.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Models.Pronunciation.MediaDTO;
import com.example.myapplication.R;

import java.util.List;


public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {
    private Context mContext;
    private List<MediaDTO> mData;
    private IClickItem mIClickItem;

    public interface IClickItem {
        void sendMedia(MediaDTO mediaDTO);
    }

    public MediaAdapter(Context mContext, List<MediaDTO> mData, IClickItem iClickItem) {
        this.mContext = mContext;
        this.mData = mData;
        this.mIClickItem = iClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_media, parent, false);
        return new MediaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String url = BuildConfig.BASE_URL + mData.get(position).getSmallThumbnailUrl();
        LoadImage.Load(mContext, url, holder.img);

        holder.watch.setOnClickListener(v -> {
            MediaDTO mediaDTO = mData.get(position);
            mIClickItem.sendMedia(mediaDTO);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView img;
        Button watch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgmedia);
            //LoadImage()
            watch = itemView.findViewById(R.id.watch);

        }
    }
}
