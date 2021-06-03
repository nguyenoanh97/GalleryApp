package com.example.galleryapp;

import android.provider.ContactsContract;

public interface OnItemClickListener {
    void onItemClick(ContactsContract.CommonDataKinds.Note note);
}
