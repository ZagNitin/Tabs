package com.example.tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private List<Photos> photoList;

    public void setData(List<Photos> photoList){
        this.photoList= photoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_image_layout,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
    Photos photos = photoList.get(position);
    String title = photos.getTitle();
    String url = photos.getUrl();

    holder.title.setText(title);
        Picasso.with(holder.pic.getContext()).load(url)
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;
        TextView title;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.textView15);
            pic= (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
