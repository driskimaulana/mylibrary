package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.util.Util;
import com.example.mylibrary.adapter.AllBooksRecViewAdapter;

public class FavoriteBooksActivity extends AppCompatActivity {

    private RecyclerView recViewFavorite;
    private AllBooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);

        recViewFavorite = findViewById(R.id.recViewFavorite);

        adapter = new AllBooksRecViewAdapter(this, "favoriteBooks");

        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());

        recViewFavorite.setAdapter(adapter);
        recViewFavorite.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * make sure that back button is navigate user to main activity or main page
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}