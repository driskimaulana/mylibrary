package com.example.mylibrary.adapter;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.mylibrary.BookActivity;
import com.example.mylibrary.R;
import com.example.mylibrary.Utils;
import com.example.mylibrary.model.Book;

import java.util.ArrayList;

import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;

public class AllBooksRecViewAdapter extends RecyclerView.Adapter<AllBooksRecViewAdapter.ViewHolder> {

//    arraylist for books data
    ArrayList<Book> books = new ArrayList<Book>();
    Context mContext;

    String currentActivity;

    public AllBooksRecViewAdapter()
    {
//        empty constructors
    }

    public AllBooksRecViewAdapter(Context mContext, String currentActivity)
    {
        this.mContext = mContext;
        this.currentActivity = currentActivity;
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

//        set delete button visibility based on current activity
        switch (currentActivity){
            case "allBooks":
                holder.txtDelete.setVisibility(View.GONE);
                break;
            case "currentlyRead":
                holder.txtDelete.setVisibility(View.VISIBLE);

                holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure to remove " + books.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(mContext).deleteFromCurrentlyReadingBooks(books.get(position))){
                                    Toast.makeText(mContext, "Book Deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(mContext, "Delete failed. Try again later!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();

                    }
                });
                break;
            case "wantToRead":
                holder.txtDelete.setVisibility(View.VISIBLE);

                holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("Are you sure to delete " + books.get(position).getName() + " ?");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(mContext).deleteFromWantToReadBooks(books.get(position)))
                                {
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(mContext, "Remove failed. Try again later!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                    }
                });
                break;
            case "favoriteBooks":
                holder.txtDelete.setVisibility(View.VISIBLE);

                holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setMessage("Are you sure want to remove " + books.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(mContext).deleteFromFavoriteBooks(books.get(position))){
                                    Toast.makeText(mContext, "Book succesfull deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(mContext, "Remove failed. Try again later!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                    }
                });
                break;
            case "alreadyRead":
                holder.txtDelete.setVisibility(View.VISIBLE);

                holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setMessage("Are you sure want to delete " + books.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(mContext).deleteFromAlreadyReadBooks(books.get(position)))
                                {
                                    Toast.makeText(mContext, "Book deleted!", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }else {
                                    Toast.makeText(mContext, "Remove failed. Try again later!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                        builder.show();
                    }
                });
                break;
            default:
                break;
        }



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

        private TextView txtDelete;

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

            txtDelete = itemView.findViewById(R.id.txtDelete);


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
