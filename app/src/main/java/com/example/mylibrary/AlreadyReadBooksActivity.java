package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mylibrary.adapter.AllBooksRecViewAdapter;

public class AlreadyReadBooksActivity extends AppCompatActivity {

    private RecyclerView recViewAlreadRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_books);

//        initialize recycler view
        recViewAlreadRead = findViewById(R.id.recViewAlreadyRead);

        AllBooksRecViewAdapter adapter = new AllBooksRecViewAdapter(this, "alreadyRead");
        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
        recViewAlreadRead.setAdapter(adapter);

        recViewAlreadRead.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * make sure the back button is navigate user to main activity
     * navigate to main activity class if back is pressed
     */
    @Override
    public void onBackPressed() {
//        navigate to main activity if back is pressed
        Intent intent = new Intent(this, MainActivity.class);
//        make sure create a new back stack, so that back button is working the way it should be
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}