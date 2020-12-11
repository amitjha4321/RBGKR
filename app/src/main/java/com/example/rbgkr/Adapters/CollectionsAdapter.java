package com.example.rbgkr.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rbgkr.Models.Collection;
import com.example.rbgkr.Models.Photo;
import com.example.rbgkr.R;
import com.example.rbgkr.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAdapter extends BaseAdapter {

    private Context context;
    private List<Collection> collections;

    public CollectionsAdapter(Context context, List<Collection> collections) {
        this.context=context;
        this.collections=collections;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collections.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_collection,viewGroup,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        ButterKnife.bind(this,view);
        Collection collection=collections.get(position);
        if (collection.getTitle()!=null){
            holder.title.setText(collection.getTitle());
        }
        holder.totalPhotos.setText(String.valueOf(collection.getTotalPhotos() + "photos"));

        Glide
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(holder.collectionPhoto);

        return view;
    }
    static class ViewHolder{
        public ViewHolder(View view){
            ButterKnife.bind(this,view);

        }
        @BindView(R.id.item_collection_title)
        TextView title;

        @BindView(R.id.item_collection_total_photos)
        TextView totalPhotos;

        @BindView(R.id.item_collection_photo)
        SquareImage collectionPhoto;


    }
}
