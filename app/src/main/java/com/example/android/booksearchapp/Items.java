package com.example.android.booksearchapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("items")
    public List<Book> bookList;
}
