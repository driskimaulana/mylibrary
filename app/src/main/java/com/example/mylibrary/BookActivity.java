package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.mylibrary.model.Book;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

//    class attribute
    private TextView txtName, txtAuthor, txtPages, txtShortDesc, txtLongDesc;
    private ImageView imgCurrentBook;
    private Button btnCurrently, btnAlready, btnFav, btnWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

//        initialize ui
        initView();

//        get intent that use to navigate to THIS activity in order to get data from
//        previous activity
        Intent intent = getIntent();

        if(null != intent)
        {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if(bookId != -1){
//                thereis book id in this navigation
                Book book = Utils.getInstance().getBookById(bookId);
                if(book != null)
                {
                    setData(book);
                    manageAlreadyReadBookButton(book);
                    manageCurrentlyReadingBookButton(book);
                    manageWantToReadBookButton(book);
                    manageAddToFavoriteBookButton(book);
                }
            }
        }

    }

    /**
     * manage add to favorite button
     * disable button if book already in the list
     * add book to favorite book list
     * navigate user to favorite book activity
     * @param book
     */
    private void manageAddToFavoriteBookButton(Book book) {

        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();

        boolean isExist = false;
        for(Book b: favoriteBooks)
        {
            if(b.getId() == book.getId()) isExist = true;
        }

        if(isExist)
        {
            btnFav.setEnabled(false);
        }else {
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addFavoriteBook(book))
                    {
                        Toast.makeText(BookActivity.this, "Book successfully added to favorite list!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Failed to add book. Try again later!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    /**
     * manage want to read button
     * Add book to want to read list
     * Disable button if book already in list
     * Navigate to WatToReadActivity
     * @param book
     */
    private void manageWantToReadBookButton(Book book) {

        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean isExist = false;
        for (Book b: wantToReadBooks) {
            if(b.getId() == book.getId()) isExist = true;
        }

        if(isExist)
        {
            btnWish.setEnabled(false);
        }
        else{
            btnWish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addWantToReadBook(book))
                    {
                        Toast.makeText(BookActivity.this, "Book successfully added to Want To Read list!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Failed to add book. Try again later!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    /**
     * Manage currently reading button
     * Disable button if the book is already in the list
     * Add book to currently read book list
     * navigate to currently reading book activity
     * @param book
     */
    private void manageCurrentlyReadingBookButton(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean isExist = false;

        for (Book b: currentlyReadingBooks) {
            if(b.getId() == book.getId()) isExist = true;
        }

        if(isExist == true)
        {
            btnCurrently.setEnabled(false);
        }else {
            btnCurrently.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addCurrentlyReadingBook(book))
                    {
                        Toast.makeText(BookActivity.this, "Book Successfully added to Currently Reading lists!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Failed to add book. Try again later!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Manage Already read button
     * Disable button if the book is already in the list
     * Add book to Already read book data lists
     * @param book
     */
    private void manageAlreadyReadBookButton(Book book) {

        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean isExist = false;
        for (Book b: alreadyReadBooks) {
            if (b.getId() == book.getId()) isExist = true;
        }

//        disable button if book already exist in alreadyReadBooks data
        if(isExist == true)
        {
            btnAlready.setEnabled(false);
        }else {
            btnAlready.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addAlreadyReadBook(book))
                    {
                        Toast.makeText(BookActivity.this, "Book successfully added to Alread Read", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Failed to add book, try again later!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void initView()
    {
        txtName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtBookAuthor);
        txtLongDesc = findViewById(R.id.txtBookLongDesc);
        txtPages = findViewById(R.id.txtBookPages);
        txtShortDesc = findViewById(R.id.txtBookShortDesc);

        imgCurrentBook = findViewById(R.id.imgCurrentBook);

        btnAlready = findViewById(R.id.btnAlready);
        btnWish = findViewById(R.id.btnWish);
        btnFav = findViewById(R.id.btnFav);
        btnCurrently = findViewById(R.id.btnCurrently);
    }

    public void setData(Book book)
    {
        txtName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtLongDesc.setText(book.getLongDesc());
        txtShortDesc.setText(book.getShortDesc());
        txtPages.setText(String.valueOf(book.getPages()));

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgCurrentBook);
    }

    public void addToAlreadyRead()
    {

    }

    public void addToFavourites()
    {

    }

    public void addToWishlist()
    {

    }

    public void addToCurrentlyRead()
    {


        
    }
}
