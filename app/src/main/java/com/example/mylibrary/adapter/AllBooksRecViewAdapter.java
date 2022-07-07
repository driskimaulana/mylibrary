package com.example.mylibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mylibrary.BookActivity;
import com.example.mylibrary.R;
import com.example.mylibrary.model.Book;

import java.util.ArrayList;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

public class AllBooksRecViewAdapter extends RecyclerView.Adapter<AllBooksRecViewAdapter.ViewHolder> {

//    arraylist for books data
    ArrayList<Book> books = new ArrayList<Book>();
    Context mContext;

    public AllBooksRecViewAdapter()
    {
//        empty constructors
    }

    public AllBooksRecViewAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_books_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(books.get(position).getName());

//        get iamge from url
        Glide.with(mContext).asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imgBook);

//        action listiner for every card

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, books.get(position).getName() + " Clicked", Toast.LENGTH_SHORT).show();
//                navigate to book details page
                Intent intent = new Intent(mContext, BookActivity.class);
//                send data to another activity
                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });



//        is card expanded or not
        if(books.get(position).isExpanded())
        {
            holder.txtAuthor.setText(books.get(position).getAuthor());
            holder.txtDescription.setText(books.get(position).getShortDesc());
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedCard.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedCard.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        class ui attributes
        private ImageView imgBook;
        private TextView txtName;
        private CardView parent;

        private TextView txtAuthor, txtDescription;
        private ImageView upArrow, downArrow;
        private RelativeLayout expandedCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            initialize ui attributes
            imgBook = itemView.findViewById(R.id.imgBook);
            txtName = itemView.findViewById(R.id.txtBookName);
            parent = itemView.findViewById(R.id.parent);

            txtAuthor = itemView.findViewById(R.id.txtBookAuthor);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            upArrow = itemView.findViewById(R.id.upArrow);
            downArrow = itemView.findViewById(R.id.downArrow);
            expandedCard = itemView.findViewById(R.id.expandedCard);


//            action listener for arrow button
            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book selectedBook = books.get(getAdapterPosition());
                    selectedBook.setExpanded(!selectedBook.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Book selectedBook = books.get(getAdapterPosition());
                    selectedBook.setExpanded(!selectedBook.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

}
