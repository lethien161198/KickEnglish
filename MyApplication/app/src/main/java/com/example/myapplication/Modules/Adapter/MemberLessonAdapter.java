package com.example.myapplication.Modules.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.Common.LoadImage;
import com.example.myapplication.Models.Home.MemberLessonDTO;
import com.example.myapplication.R;

import java.util.List;

public class MemberLessonAdapter extends RecyclerView.Adapter<MemberLessonAdapter.MyViewHolder> {
    private Context context;
    private List<MemberLessonDTO> memberLessonDTOList;

    public MemberLessonAdapter(Context context, List<MemberLessonDTO> memberLessonDTOList) {
        this.context = context;
        this.memberLessonDTOList = memberLessonDTOList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.item_memberlesson, parent, false);
        return new MemberLessonAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String url = BuildConfig.BASE_URL + memberLessonDTOList.get(position).getSmallThumbnailUrl();
        holder.name.setText(memberLessonDTOList.get(position).getTitle());
        LoadImage.Load(context, url, (ImageView) holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return memberLessonDTOList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_mem);
            img = itemView.findViewById(R.id.img_mem);
            cardView = itemView.findViewById(R.id.cardview_memberlesson);


        }

    }
}
