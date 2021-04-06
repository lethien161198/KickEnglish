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
import com.example.myapplication.Models.Home.Artist;
import com.example.myapplication.R;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {
    private Context mContext;
    private List<Artist> mData;

    private IClickItem mIClickItem;

    public interface IClickItem {
        void sendIdArtist(int id);
    }

    public ArtistAdapter(Context mContext, List<Artist> mData, IClickItem iClickItem) {
        this.mContext = mContext;
        this.mData = mData;
        this.mIClickItem = iClickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_artist, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String url = BuildConfig.BASE_URL + mData.get(position).getSmallThumbnailUrl();
        holder.name.setText(mData.get(position).getName());
        //holder.img.setImage;

        LoadImage.Load(mContext, url, holder.img);
        holder.img.setOnClickListener(v -> {
            int id = mData.get(position).getId();
            mIClickItem.sendIdArtist(id);
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_artist);
            img = itemView.findViewById(R.id.img_artist);


        }

    }
}
