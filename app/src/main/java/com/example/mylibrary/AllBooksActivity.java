package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

//        list of book
        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book(1, "IQ84", "Haruki Murakami", 1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg"
        ,"A work of maddening brilliance", "Long Descriptions"));
        books.add(new Book(2, "Life of Pi", "Yann Martel", 460, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631251689l/4214._SY475_.jpg"
        , "Life of Pi is a fantasy adventure novel by Yann Martel", "Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor \"Pi\" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker. "));

        adapter.setBooks(books);

        recViewAllBooks.setAdapter(adapter);

//        recViewAllBooks.setLayoutManager(new GridLayoutManager(this, 2));
        recViewAllBooks.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initView()
    {
        recViewAllBooks = findViewById(R.id.recViewAllBooks);
    }
}