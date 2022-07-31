package com.eric.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.eric.gallery.adaptors.PhotoAdaptor;
import com.eric.gallery.utils.PhotoUtils;

import java.util.List;

public class PhotoListActivity extends AppCompatActivity {

    private List<String> data;
    private GridView gridView;
    private PhotoAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        display(getIntent().getIntExtra("id",0));
    }

    private void display(int id){
        Log.e("display: ", id+" ");
        data = PhotoUtils.getPhotos(this,id);
        Log.e("data: ", data.toString());
        adaptor = new PhotoAdaptor(data,this);
        gridView = findViewById(R.id.photoList_gridview);
        gridView.setAdapter(adaptor);
    }
}