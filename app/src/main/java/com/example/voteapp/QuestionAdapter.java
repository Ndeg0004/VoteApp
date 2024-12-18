package com.example.voteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteapp.model.Poll;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private final List<Poll> polls = new ArrayList<>();
    private final MutableLiveData<Poll> onItemClickLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question, viewGroup, false);
        return new ViewHolder(v, onItemClickLiveData);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setItem(polls.get(i));
    }

    @Override
    public int getItemCount() {
        return polls.size();
    }

    public void onFeedUpdate(List<Poll> polls) {
        this.polls.clear();
        this.polls.addAll(polls);
        notifyDataSetChanged();
    }

    public LiveData<Poll> onPollItemClick() {
        return onItemClickLiveData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Poll poll;

        public ViewHolder(@NonNull View itemView, MutableLiveData<Poll> onItemClickLiveData) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (poll != null) {
                    onItemClickLiveData.postValue(poll);
                }
            });
        }

        public void setItem(Poll poll) {
            this.poll = poll;

            TextView question = itemView.findViewById(R.id.textViewQuestion);
            question.setText(poll.question);
        }

    }
}
