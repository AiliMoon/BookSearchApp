package com.example.android.booksearchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText inputTitleBookEditText;
    private ImageButton searchButton;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private int max_results = 20;

    BookApi service = RetroClient.getRetrofitInstance().create(BookApi.class);
    CustomAdapter.OnItemClickListener clickListener = book -> {
        String url = book.volumeInfo.infoLink;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    };

    private void openList() {
        inputTitleBookEditText = findViewById(R.id.inputBook);
        searchButton = findViewById(R.id.button_search);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        customAdapter = new CustomAdapter(clickListener);
        recyclerView.setAdapter(customAdapter);
        searchButton.setOnClickListener((View v) ->
                service.getBooks(inputTitleBookEditText.getText().toString(), max_results)
                .enqueue(new Callback<Items>() {
                    @Override
                    public void onResponse(@NonNull Call<Items> call, @NonNull Response<Items> response) {
                        if (response.body() != null) {
                            Log.e("TEST", response.body().bookList.toString());
                            customAdapter.setData(response.body().bookList);
                        } else if (inputTitleBookEditText.getText().toString().equals("")) {
                            customAdapter.clear();
                        }
                    }

                    @Override
                    public void onFailure(Call<Items> call, Throwable t) {
                        Log.e(MainActivity.class.getSimpleName(), "onFailure");
                        t.getMessage();
                    }
                }));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        openList();
    }
}