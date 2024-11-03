package com.lawrence123.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    String[] answers;
    Context context;
    ItemClicked itemClicked;

    public QuizAdapter(String[] answersList, Context context, ItemClicked itemClicked){
        answers = answersList;
        this.context = context;
        this.itemClicked = itemClicked;

    }
    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.option_field,parent,false);
        return new QuizHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizHolder holder, int position) {
        holder.description.setText(answers[position]);
    }

    @Override
    public int getItemCount() {
        return answers.length;
    }

    class QuizHolder extends RecyclerView.ViewHolder{
        TextView description;

        public QuizHolder(@NonNull final View itemView) {
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

