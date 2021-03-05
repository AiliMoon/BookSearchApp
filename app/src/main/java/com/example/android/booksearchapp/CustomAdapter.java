package com.example.android.booksearchapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Book> bookList;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public CustomAdapter(OnItemClickListener listener) {

        onItemClickListener = listener;
        bookList = new ArrayList<>();

    }

    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Book book = bookList.get(position);
        holder.bind(bookList.get(position), onItemClickListener);
        holder.bookTitle.setText(book.volumeInfo.title);

        if (book.volumeInfo.authors != null) {
            holder.authors.setText(String.valueOf(book.volumeInfo.authors));
        } else {
            holder.authors.setText(R.string.no_info);
        }

        holder.bookPublishedDate.setText("Published date: " + book.volumeInfo.publishedDate);
        if (book.volumeInfo.pageCount != 0) {
            holder.pageCount.setText(book.volumeInfo.pageCount + " pages");
        } else {
            holder.pageCount.setText("N/A");
        }

        holder.rating.setRating(Float.parseFloat(String.valueOf(book.volumeInfo.averageRating)));


        if (book.volumeInfo.imageLinks == null) {
            Glide.with(holder.imageView.getContext())
                    .load(R.drawable.book)
                    .into(holder.imageView);
        } else {
            Glide.with(holder.imageView.getContext())
                    .load(book.volumeInfo.imageLinks)
                    .into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView bookTitle;
        private final TextView bookPublishedDate;
        private final TextView authors;
        private final TextView pageCount;
        private final RatingBar rating;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_book_cover);
            bookTitle = itemView.findViewById(R.id.textView_book_title);
            bookPublishedDate = itemView.findViewById(R.id.textView_book_publishedDate);
            authors = itemView.findViewById(R.id.textView_book_authors);
            pageCount = itemView.findViewById(R.id.textView_book_pageCount);
            rating = itemView.findViewById(R.id.textView_book_rating);

        }

        public void bind(final Book book, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(book));
        }
    }

    public void clear() {
        bookList.clear();
        notifyDataSetChanged();
    }

    public void setData(List<Book> bookList) {
        this.bookList.clear();
        this.bookList.addAll(bookList);
        notifyDataSetChanged();
    }

}
