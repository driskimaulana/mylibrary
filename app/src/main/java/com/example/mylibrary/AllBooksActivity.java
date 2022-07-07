package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bumptech.glide.util.Util;
import com.example.mylibrary.adapter.AllBooksRecViewAdapter;
import com.example.mylibrary.model.Book;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView recViewAllBooks;
    private AllBooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        initView();

        adapter = new AllBooksRecViewAdapter(this);

        adapter.setBooks(Utils.getInstance().getAllBooks());

        recViewAllBooks.setAdapter(adapter);

//        recViewAllBooks.setLayoutManager(new GridLayoutManager(this, 2));
        recViewAllBooks.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initView()
    {
        recViewAllBooks = findViewById(R.id.recViewAllBooks);
    }
}