package com.example.galleryapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.Show_img_holder> implements OnItemClickListener{
    private Context mContext;
    private List<User> mListUser;
    private List<String> imagePaths;
    private OnItemClickListener listener;


//    public ShowImageAdapter(Context mContext, int simple_list_item_1, List<String> ds) {
//        this.mContext = mContext;
//    }

    public ShowImageAdapter(Context c, List<String> imagePaths, OnItemClickListener listener) {
        this.imagePaths = imagePaths;
        mContext = c;
        this.listener = listener;
    }

    public void setData(List<User> list) {
        this.mListUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Show_img_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);

        return new Show_img_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Show_img_holder holder, int position) {

        String pathName  = imagePaths.get(position);
        Log.d("TAG", "onBindViewHolder: position = "+position+" - pathName = "+pathName);

        //4000x3000
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        Log.d("TAG", "onBindViewHolder: "+" - "+bitmap.getWidth()+" - "+bitmap.getHeight());



        //130x130
        holder.img.setImageBitmap(bitmap);




        /*User user = mListUser.get(position);
        if (user == null) {
            return;
        }
        holder.img.setImageResource(user.getResouceImage());
        holder.tvName.setText(user.getName());*/


//        holder.img.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(mListUser.get(position))));

    }

    @Override
    public int getItemCount() {
        if(imagePaths != null) {
            return imagePaths.size();
        }
        return 0;
    }

    @Override
    public void onItemClick(ContactsContract.CommonDataKinds.Note note) {

    }

    public class Show_img_holder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tvName;

        public Show_img_holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imv_them);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }

}
