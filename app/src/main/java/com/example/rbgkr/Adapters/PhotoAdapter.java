package com.example.rbgkr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rbgkr.Activities.FullScreenPhotoActivity;
import com.example.rbgkr.Models.Photo;
import com.example.rbgkr.R;
import com.example.rbgkr.Utils.GlideApp;
import com.example.rbgkr.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context context;
    private List<Photo> photos;

    public PhotoAdapter(Context context, List<Photo> photos){
        this.context=context;
        this.photos=photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo=photos.get(position);
        holder.username.setText(photo.getUser().getUsername());

        GlideApp
                .with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(holder.photo);

        GlideApp
                .with(context)
                .load(photo.getUser().getProfilemage().getSmall())
                .into(holder.userAvatar);


    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_photo_user_avatar)
        CircleImageView userAvatar;

        @BindView(R.id.item_photo_username)
        TextView username;

        @BindView(R.id.item_photo_photo)
        SquareImage photo;

        @BindView(R.id.item_photo_layout)
        FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_photo_layout)
        public void setFrameLayout(){
        //if user click to item photo then FullscreenPhoto activity will be opened...
            int position=getAdapterPosition();
            String photoId= photos.get(position).getId();
            Intent intent=new Intent(context, FullScreenPhotoActivity.class);
            intent.putExtra("photoId",photoId);
            context.startActivity(intent);
        }
    }
}
