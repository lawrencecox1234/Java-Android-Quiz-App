package com.lawrence123.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    String[] options;
    Context context;
    ItemClicked itemClicked;

    public HomeAdapter(String[] optionsList, Context context, ItemClicked itemClicked){
        options = optionsList;
        this.context = context;
        this.itemClicked = itemClicked;

    }
    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.option_field,parent,false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.description.setText(options[position]);
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    class HomeHolder extends RecyclerView.ViewHolder{
        TextView description;

        public HomeHolder(@NonNull final View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.txt_note_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClicked.onClick(getAdapterPosition());
                }
            });

        }
    }
}

