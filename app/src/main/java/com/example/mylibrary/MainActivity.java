package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSeeAll, btnAlreadyRead, btnYourFavourites, btnWishlist, btnCurrentlyReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initialize ui elements
        initViews();

//        action listener for btnSeeAll
        btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                create new route for changing activity using intent
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);

//                start new activity
                startActivity(intent);
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingBooksActivity.class);
                startActivity(intent);
            }
        });

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WantToReadBooksActivity.class);
                startActivity(intent);
            }
        });

        btnYourFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });

//        initiliaze data inside utils class
        Utils.getInstance();
    }


    private void initViews() {
//        initialize ui by id
        btnSeeAll = findViewById(R.id.btnSeeAll);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnYourFavourites = findViewById(R.id.btnYourFavourites);
        btnWishlist = findViewById(R.id.btnWishlist);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
    }
}
