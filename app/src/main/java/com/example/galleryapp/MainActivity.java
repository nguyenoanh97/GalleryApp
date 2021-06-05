package com.example.galleryapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.galleryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.os.LocaleListCompat.create;
import static com.example.galleryapp.R.id.imv_them;

public class MainActivity extends AppCompatActivity implements OnItemClickListener{
    private static final String TAG = "MainActivity";
    ImageView image;
    ActivityMainBinding binding;
    //Tạo List chuỗi chứa data
    List<String> imagePaths = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    View.OnClickListener clickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        image = (ImageView) findViewById(imv_them) ;
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void xem_anh(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
        }


        // Gồm những thứ cần lấy
        String[] projection = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA,

        };
//      Con trỏ lấy dữ liệu ảnh
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);
        Log.d(TAG, "xem_anh: cursor " + (cursor == null) + " size " + cursor.getCount());
        //nếu có dữ liệu mới đọc
        if (cursor.getCount() > 0) {
            // đưa con trỏ về đầu
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                Log.d(TAG, "xem_anh: data " + imagePath);
                // cho dữ liệu vào mảng khai báo chứa data
                imagePaths.add(imagePath);
                Log.d(TAG, "xem_anh: test " +imagePaths);
                cursor.moveToNext();
            }
            cursor.close();

        }

        ShowImageAdapter adapter = new ShowImageAdapter(this, imagePaths);
        binding.rcvUser.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        binding.btnGridOne.setOnClickListener(clickListener);
        binding.btnGridTwo.setOnClickListener(clickListener);
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId())
                {
                    case R.id.btn_grid_one:

                        gridLayoutManager = new GridLayoutManager(MainActivity.this, 4);
                        gridLayoutManager.setSpanCount(4);
                        gridLayoutManager.requestLayout();


                        binding.rcvUser.setLayoutManager(gridLayoutManager);
                        adapter.notifyDataSetChanged();
//                        gridLayoutManager.setSpanCount(gridLayoutManager.setSpanCount());

                        binding.rcvUser.setAdapter(adapter);
                        break;


                    case R.id.btn_grid_two:
//                        gridLayoutManager = new GridLayoutManager(MainActivity.this, 5);
                        gridLayoutManager.setSpanCount(5);
                        gridLayoutManager.requestLayout();

                        binding.rcvUser.setLayoutManager(gridLayoutManager);
                        adapter.notifyDataSetChanged();
//                        notifychangeadapter();

                        binding.rcvUser.setAdapter(adapter);
                        break;
                }
                Log.d(TAG, "thay đổi grid" + v.getId());

            }
        };
        binding.rcvUser.setLayoutManager(gridLayoutManager);

        binding.rcvUser.setAdapter(adapter);

    }


    @SuppressLint("ResourceType")
    @Override
    public void onItemClick(View view, int position, boolean isLongClick) {
        Log.d(TAG, "onItemClick: "+imagePaths.get(position));


        ImageView image;
        image = new ImageView(this);

image.setImageBitmap(BitmapFactory.decodeFile(imagePaths.get(position)));

        AlertDialog.Builder aDialog = new AlertDialog.Builder(this)
                .setView(image)
                .setPositiveButton(android.R.string.ok,null)
                .setNegativeButton(android.R.string.cancel, null);
        create();
        aDialog.show();



    }




}
