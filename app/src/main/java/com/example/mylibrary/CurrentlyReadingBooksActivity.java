package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylibrary.adapter.AllBooksRecViewAdapter;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {

    private RecyclerView recViewCurrentlyReading;
    private AllBooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_books);

        recViewCurrentlyReading = findViewById(R.id.recViewCurrentlyReading);

        adapter = new AllBooksRecViewAdapter(this);
        adapter.setBooks(Utils.getInstance().getCurrentlyReadingBooks());

        recViewCurrentlyReading.setAdapter(adapter);
        recViewCurrentlyReading.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Make sure back button is navigate user to main menu
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}