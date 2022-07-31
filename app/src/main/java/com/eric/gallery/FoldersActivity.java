package com.eric.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.eric.gallery.adaptors.FolderAdaptor;
import com.eric.gallery.models.Folder;
import com.eric.gallery.utils.FolderUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class FoldersActivity extends AppCompatActivity {

    private GridView gridView ;
    private FolderAdaptor adaptor;
    private List<Folder> data;




    GridView.OnItemClickListener onItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(FoldersActivity.this,PhotoListActivity.class);
            intent.putExtra("id",(int)data.get(i).getId());
            Log.e("original id: ", data.get(i).getId()+" ");
            startActivity(intent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);

        display();
        gridView.setOnItemClickListener(onItemClickListener);
    }

    private void display(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"permission allowed",Toast.LENGTH_LONG).show();

            data = FolderUtils.getFolders(this);
            adaptor = new FolderAdaptor(data,this);

            gridView = findViewById(R.id.folder_gridview);
            gridView.setAdapter(adaptor);

        }else{
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
//            Snackbar.make(mLayout, R.string.camera_access_required,
//                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Request the permission
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.CAMERA},
//                            PERMISSION_REQUEST_CAMERA);
//                }
//            }).show();
            Toast.makeText(this,"permission allowed",Toast.LENGTH_LONG).show();

        } else {
//            Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            Toast.makeText(this,"permission denied",Toast.LENGTH_LONG).show();
        }
    }

}