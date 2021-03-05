package com.example.android.booksearchapp;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("title")
    private String title;
    @SerializedName("publishedDate")
    private String publishedDate;
    @SerializedName("smallThumbnail")
    private String smallThumbnail;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("averageRating")
    private double averageRating;

    @SerializedName("volumeInfo")
    public VolumeInfo volumeInfo;

    public Book(String title, String publishedDate, String smallThumbnail, int pageCount, double averageRating) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
        this.pageCount = pageCount;
        this.averageRating = averageRating;

    }
}











