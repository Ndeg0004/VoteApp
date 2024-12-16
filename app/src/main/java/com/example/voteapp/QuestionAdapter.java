package com.example.voteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private ArrayList<Question> questionList;

    public QuestionAdapter(MainActivity mainActivity, ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.textViewQuestion.setText(question.getQuestion());
        holder.textViewOption1.setText("Option 1: " + question.getOption1());
        holder.textViewOption2.setText("Option 2: " + question.getOption2());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion, textViewOption1, textViewOption2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            textViewOption1 = itemView.findViewById(R.id.textViewOption1);
            textViewOption2 = itemView.findViewById(R.id.textViewOption2);
        }
    }
}
