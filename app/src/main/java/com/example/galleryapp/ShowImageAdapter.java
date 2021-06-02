package com.example.galleryapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShowImageAdapter extends RecyclerView.Adapter<ShowImageAdapter.Show_img_holder>{
    private Context mContext;
    private List<User> mListUser;

    public ShowImageAdapter(Context mContext, int simple_list_item_1, List<String> ds) {
        this.mContext = mContext;
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
        User user = mListUser.get(position);
        if (user == null) {
            return;
        }
        holder.img.setImageResource(user.getResouceImage());
        holder.tvName.setText(user.getName());

    }

    @Override
    public int getItemCount() {
        if(mListUser != null) {
            return mListUser.size();
        }
        return 0;
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
