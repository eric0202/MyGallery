package com.eric.gallery.adaptors;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eric.gallery.R;
import com.eric.gallery.models.Folder;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FolderAdaptor extends BaseAdapter {

    public List<Folder> data;
    private Context context ;

    public FolderAdaptor(List<Folder> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.folder_item,viewGroup,false);
        }

        TextView textView = view.findViewById(R.id.list_folder_name);
        ImageView imageView = view.findViewById(R.id.list_folder_thumb);
        textView.setText(data.get(i).getName());
        Glide.with(view.getContext()).load(data.get(i).getPath()).into(imageView);
        return view;

    }
}
