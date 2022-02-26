package com.niksaen.progersim.myClass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.niksaen.progersim.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    Context context;
    int[] avatars = new int[]{
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3,
            R.drawable.avatar4,
    };
    private int currentAvatar;

    public RecyclerAdapter(Context context,int currentAvatar){
        this.context = context;
        this.currentAvatar = currentAvatar;
    }

    public int getCurrentAvatar(){return currentAvatar;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,null);
        return new ViewHolder(view);
    }

    View buff;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(avatars[i]);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAvatar = avatars[viewHolder.getAdapterPosition()];
                v.setBackgroundColor(Color.WHITE);
                if(buff != null){
                    buff.setBackgroundColor(Color.TRANSPARENT);
                }
                buff = v;
            }
        });
    }

    @Override
    public int getItemCount() {
        return avatars.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view;
        }
    }
}
