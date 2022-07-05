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
