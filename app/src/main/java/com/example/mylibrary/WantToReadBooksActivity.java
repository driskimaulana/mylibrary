package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylibrary.adapter.AllBooksRecViewAdapter;

public class WantToReadBooksActivity extends AppCompatActivity {

    RecyclerView recViewWantToRead;
    AllBooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_books);

        recViewWantToRead = findViewById(R.id.recViewWantToRead);
        adapter = new AllBooksRecViewAdapter(this);

        adapter.setBooks(Utils.getInstance().getWantToReadBooks());

        recViewWantToRead.setAdapter(adapter);
        recViewWantToRead.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * make sure that back button is navigate user to main activity or main menu
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}