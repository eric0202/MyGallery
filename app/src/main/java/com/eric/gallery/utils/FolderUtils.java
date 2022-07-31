package com.eric.gallery.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.eric.gallery.FoldersActivity;
import com.eric.gallery.models.Folder;

import java.util.ArrayList;
import java.util.List;

public class FolderUtils {
    public static List<Folder> getFolders(Context context){
        List<Folder> folders = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri  = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection ;
        Bundle bundle = new Bundle();
        Cursor cursor;

        projection = new String[]{
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_ID
        };




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            bundle.putString(ContentResolver.QUERY_ARG_SQL_GROUP_BY,MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            bundle.putString(ContentResolver.QUERY_ARG_SQL_SORT_ORDER,"date_added desc");
            cursor = contentResolver.query(uri,projection,bundle,null);
            Log.e("cursor 30",cursor.toString());
        }else{
            cursor = contentResolver.query(uri,projection,"0=0) group by (bucket_display_name",null,"date_added desc");
            Log.e("cursor -",cursor.toString());
        }

        if (cursor != null && cursor.moveToFirst()) {
            do{
                Folder folder = new Folder(cursor.getString(1),cursor.getString(0),cursor.getInt(2));
                folders.add(folder);
            }while (cursor.moveToNext());
            cursor.close();
        }

        return folders;
    }
}
