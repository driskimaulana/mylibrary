package com.example.mylibrary;

import com.example.mylibrary.model.Book;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

//    data for every books category
    private ArrayList<Book> allBooks;
    private ArrayList<Book> alreadyReadBooks;
    private ArrayList<Book> wantToReadBooks;
    private ArrayList<Book> currentlyReadingBooks;
    private ArrayList<Book> favoriteBooks;

    private Utils() {

        if (null == allBooks)
        {
            allBooks = new ArrayList<>();
            initData();
        }

        if(null == alreadyReadBooks)
        {
            alreadyReadBooks = new ArrayList<>();
        }

        if(null == wantToReadBooks)
        {
            wantToReadBooks = new ArrayList<>();
        }

        if(null == currentlyReadingBooks)
        {
            currentlyReadingBooks = new ArrayList<>();
        }

        if(null == favoriteBooks)
        {
            favoriteBooks = new ArrayList<>();
        }

    }

    public void initData()
    {
//        TODO: add initial data for all books
        allBooks.add(new Book(1, "IQ84", "Haruki Murakami", 1350, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1483103331l/10357575.jpg"
                ,"A work of maddening brilliance", "Long Descriptions"));
        allBooks.add(new Book(2, "Life of Pi", "Yann Martel", 460, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1631251689l/4214._SY475_.jpg"
                , "Life of Pi is a fantasy adventure novel by Yann Martel", "Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor \"Pi\" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker. "));


    }

    public ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public static Utils getInstance() {
        if (instance == null)
        {
            instance = new Utils();
            return  instance;
        }else {
            return  instance;
        }
    }

    public Book getBookById(int bookId)
    {
        for (Book b: allBooks) {
            if(bookId == b.getId()) return b;
        }

        return  null;
    }

    public boolean addAlreadyReadBook(Book book)
    {
        return alreadyReadBooks.add(book);
    }

    public boolean addCurrentlyReadingBook(Book book)
    {
        return currentlyReadingBooks.add(book);
    }

    public boolean addWantToReadBook(Book book)
    {
        return  wantToReadBooks.add(book);
    }

    public boolean addFavoriteBook(Book book)
    {
        return  favoriteBooks.add(book);
    }
}
