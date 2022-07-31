package com.eric.gallery.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;


import java.util.ArrayList;
import java.util.List;

public class PhotoUtils {
    public static List<String> getPhotos(Context context, int bucketId){
        List<String> photos = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri  = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection ;
        Bundle bundle = new Bundle();
        Cursor cursor;

        projection = new String[]{
                MediaStore.Images.Media.DATA,
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bundle.putString(ContentResolver.QUERY_ARG_SQL_SELECTION, "bucket_id=?");
            bundle.putStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, new String[]{String.valueOf(bucketId)});
            bundle.putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER,"date_added desc");
            cursor = contentResolver.query(uri, projection, bundle, null);
        }else{
            cursor = contentResolver.query(uri,projection,"bucket_id=?",new String[]{String.valueOf(bucketId)},"date_added desc");
        }

        if (cursor != null && cursor.moveToFirst()) {
            do{
                String photo = cursor.getString(0);
                photos.add(photo);
            }while (cursor.moveToNext());
            cursor.close();
        }

        return photos;
    }
}
