package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.model.Book;

public class BookActivity extends AppCompatActivity {

//    class attribute
    private TextView txtName, txtAuthor, txtPages, txtShortDesc, txtLongDesc;
    private ImageView imgCurrentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Book book = new Book(2, "Life of Pi", "Yann Martel", 460, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631251689l/4214._SY475_.jpg"
                , "Life of Pi is a fantasy adventure novel by Yann Martel", "Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor \"Pi\" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker. \n" +
                "Yann Martel is the author of Life of Pi, the #1 international bestseller and winner of the 2002 Man Booker (among many other prizes). He is also the award-winning author of The Facts Behind the Helsinki Roccamatios (winner of the Journey Prize), Self, Beatrice & Virgil, and 101 Letters to a Prime Minister. Born in Spain in 1963, Martel studied philosophy at Trent University, worked at odd jobs—tree planter, dishwasher, security guard—and traveled widely before turning to writing. He lives in Saskatoon, Canada, with the writer Alice Kuipers and their four children.\n"+
                "It is not so much that The Life of Pi, is particularly moving (although it is). It isn’t even so much that it is written with language that is both delicate and sturdy all at once (which it is, as well). And it’s certainly not that Yann Martel’s vision filled passages are so precise that you begin to feel the salt water on your skin (even though they are). It is that, like Bohjalian and Byatt and all of the great Houdini’s of the literary world, in the last few moments of your journey – after you’ve felt the emotions, endured the moments of heartache, yearned for the resolution of the characters’ struggle – that you realize the book is not what you thought it was. The story transforms, instantly, and forever.\n" +
                "\n" +
                "And in those last few chapters, you suddenly realize that the moral has changed as well.");

//        initialize ui
        initView();

        setData(book);
    }

    public void initView()
    {
        txtName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtBookAuthor);
        txtLongDesc = findViewById(R.id.txtBookLongDesc);
        txtPages = findViewById(R.id.txtBookPages);
        txtShortDesc = findViewById(R.id.txtBookShortDesc);

        imgCurrentBook = findViewById(R.id.imgCurrentBook);
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
}
