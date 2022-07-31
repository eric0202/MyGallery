package com.eric.gallery.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eric.gallery.R;

import java.util.List;

public class PhotoAdaptor extends BaseAdapter {

    private List<String> data;
    private Context context;

    public PhotoAdaptor(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.photo_item,viewGroup,false);
        }
        ImageView imageView = view.findViewById(R.id.list_photo_thumb);
        Glide.with(view.getContext()).load(data.get(i)).into(imageView);
        return view;
    }
}
