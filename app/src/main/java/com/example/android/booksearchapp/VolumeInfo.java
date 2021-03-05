package com.example.android.booksearchapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    public String title;

    @SerializedName("authors")
    public List<String> authors;

    @SerializedName("averageRating")
    public double averageRating;

    @SerializedName("publishedDate")
    public String publishedDate;

    @SerializedName("pageCount")
    public int pageCount;

    @SerializedName("infoLink")

    public String infoLink;

    @SerializedName("imageLinks")
    public ImageLinks imageLinks;
}
