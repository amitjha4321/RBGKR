package com.example.rbgkr.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.rbgkr.Models.Photo;
import com.example.rbgkr.R;
import com.example.rbgkr.Utils.Functions;
import com.example.rbgkr.Utils.GlideApp;
import com.example.rbgkr.Webservices.ApiInterface;
import com.example.rbgkr.Webservices.ServiceGenerator;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreenPhotoActivity extends AppCompatActivity {
    private final String TAG = FullScreenPhotoActivity.class.getSimpleName();

    @BindView(R.id.activity_fullscreen_photo_photo)
    ImageView fullscreenPhoto;

    @BindView(R.id.activity_fullscreen_photo_user_avatar)
    CircleImageView userAvatar;

    @BindView(R.id.activity_fullscreen_photo_fab_menu)
    FloatingActionMenu fabMenu;

    @BindView(R.id.activity_fullscreen_photo_fab_favorite)
    FloatingActionButton fabFavorite;

    @BindView(R.id.activity_fullscreen_photo_fab_set_wallpaper)
    FloatingActionButton fabWallpaper;

    @BindView(R.id.activity_fullscreen_photo_username)
    TextView username;

    Bitmap photoBitmap;

    private Unbinder unbinder;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unbinder= ButterKnife.bind(this);

        Intent intent=getIntent();
        String photoId=intent.getStringExtra("photoId");
        getPhoto(photoId);
    }

    public void getPhoto(String photoId){
        ApiInterface apiInterface= ServiceGenerator.createService(ApiInterface.class);
        Call<Photo> call =apiInterface.getPhoto(photoId);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "success");
                    Photo photo= response.body();
                    updateUI(photo);
                }else {
                    Log.e(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void updateUI(Photo photo){
        try {
            username.setText(photo.getUser().getUsername());
            GlideApp.with(FullScreenPhotoActivity.this)
                    .load(photo.getUser().getProfilemage().getSmall())
                    .into(userAvatar);

            GlideApp.with(FullScreenPhotoActivity.this)
                    .asBitmap()
                    .load(photo.getUrl().getRegular())
                    .centerCrop()

                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            fullscreenPhoto.setImageBitmap(resource);
                            photoBitmap=resource;
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.activity_fullscreen_photo_fab_favorite)
    public void setFavFavorite(){
        fabMenu.close(true);

    }

    @OnClick(R.id.activity_fullscreen_photo_fab_set_wallpaper)
    public void setFavWallpaper(){
        fabMenu.close(true);
        if (photoBitmap!=null){
            if (Functions.setWallpaper(FullScreenPhotoActivity.this,photoBitmap)){
                Toast.makeText(FullScreenPhotoActivity.this,"Set Wallpaper Successfully",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(FullScreenPhotoActivity.this,"Set Wallpaper Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
