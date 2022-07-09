package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mylibrary.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_KEY = "already_read_books";
    private static final String FAVORITE_KEY = "favorite_books";
    private static final String CURRENTLY_READING_KEY = "currently_reading";
    private static final String WANT_TO_READ_KEY = "want_to_read";

    private static Utils instance;

    private SharedPreferences sharedPreferences;

//    data for every books category
//    private ArrayList<Book> allBooks;
//    private ArrayList<Book> alreadyReadBooks;
//    private ArrayList<Book> wantToReadBooks;
//    private ArrayList<Book> currentlyReadingBooks;
//    private ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks())
        {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if(null == getAlreadyReadBooks())
        {
            editor.putString(ALREADY_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getWantToReadBooks())
        {
            editor.putString(WANT_TO_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getCurrentlyReadingBooks())
        {
            editor.putString(CURRENTLY_READING_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if(null == getFavoriteBooks())
        {
            editor.putString(FAVORITE_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }

    public void initData()
    {

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "IQ84", "Haruki Murakami", 1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg"
                ,"A work of maddening brilliance", "Long Descriptions"));
        books.add(new Book(2, "Life of Pi", "Yann Martel", 460, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631251689l/4214._SY475_.jpg"
                , "Life of Pi is a fantasy adventure novel by Yann Martel", "Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor \"Pi\" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker. "));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));

        editor.commit();

    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();

//        get type of destination of deserialization which is type of ArrayList<Book>
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

//        get allbooks from sharedPreferences using gson library
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_KEY, null), type);
        return books;
    }

    public static Utils getInstance(Context context) {
        if (instance == null)
        {
            instance = new Utils(context);
            return  instance;
        }else {
            return  instance;
        }
    }

    public Book getBookById(int bookId)
    {
        if(null != getAllBooks())
        {
            ArrayList<Book> books = getAllBooks();
            for (Book b: books) {
                if(bookId == b.getId()) return b;
            }
        }

        return  null;
    }

    public boolean addAlreadyReadBook(Book book)
    {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(books != null)
        {
            if (books.add(book))
            {
//        commit data changes to sharedpreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(ALREADY_READ_KEY);
                editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                editor.commit();

                return true;
            }

        }

        return false;
    }

    public boolean addCurrentlyReadingBook(Book book)
    {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(books != null)
        {
            if (books.add(book))
            {
//        commit data changes to sharedpreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(CURRENTLY_READING_KEY);
                editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                editor.commit();

                return true;
            }

        }

        return false;
    }

    public boolean addWantToReadBook(Book book)
    {
        ArrayList<Book> books = getWantToReadBooks();
        if(books != null)
        {
            if (books.add(book))
            {
//        commit data changes to sharedpreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(WANT_TO_READ_KEY);
                editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                editor.commit();

                return true;
            }

        }

        return false;
    }

    public boolean addFavoriteBook(Book book)
    {
        ArrayList<Book> books = getFavoriteBooks();
        if(books != null)
        {
            if (books.add(book))
            {
//        commit data changes to sharedpreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(FAVORITE_KEY);
                editor.putString(FAVORITE_KEY, gson.toJson(books));
                editor.commit();

                return true;
            }

        }

        return false;
    }

    public boolean deleteFromCurrentlyReadingBooks(Book book)
    {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null != books)
        {
            for (Book b: books) {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(CURRENTLY_READING_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromWantToReadBooks(Book book)
    {
        ArrayList<Book> books = getWantToReadBooks();
        if(null != books)
        {
            for (Book b: books) {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromFavoriteBooks(Book book)
    {
        ArrayList<Book> books = getFavoriteBooks();
        if(null != books)
        {
            for (Book b: books) {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(FAVORITE_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromAlreadyReadBooks(Book book)
    {
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null != books)
        {
            for (Book b: books) {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
