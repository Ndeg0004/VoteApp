package com.example.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteapp.firebase.realtimeDatabase.PollsManager;
import com.example.voteapp.firebase.realtimeDatabase.RealtimeDatabaseManager;
import com.example.voteapp.model.Poll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_QUESTION_REQUEST_CODE = 1;
    private QuestionAdapter feedAdapter;

    private RealtimeDatabaseManager realtimeDatabaseManager;

  private final PollsManager pollsManager = new PollsManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();


    }


    @Override
    public void onStart() {
        super.onStart();
        listenForPollUpdates();

        RecyclerView recyclerViewQuestions = findViewById(R.id.recyclerViewQuestions);
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestions.setAdapter(feedAdapter);
    }
    private void initialize() {
        getRealtimeDatabaseManager();
        feedAdapter = new QuestionAdapter();

        feedAdapter.onPollItemClick().observe(this, poll -> onPollItemClick(poll));



        findViewById(R.id.fabAddQuestion).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
            startActivityForResult(intent, ADD_QUESTION_REQUEST_CODE);
        });
    }

    private void onPollItemClick(Poll poll) {
        Intent intent = new Intent(this, VoteActivity.class);
        intent.putExtra("poll", poll.key);
        this.startActivity(intent);
    }

    private void listenForPollUpdates() {
        realtimeDatabaseManager.onPollsValuesChange().observe(this, this::onPollsUpdate);
    }

    private void onPollsUpdate(List<Poll> polls) {
        feedAdapter.onFeedUpdate(polls);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Refresh the list when a new question added
            loadQuestions();
        }
    }

    private void getRealtimeDatabaseManager() {
        if (realtimeDatabaseManager == null) {
            realtimeDatabaseManager = new RealtimeDatabaseManager();
        }
    }

    private void loadQuestions() {

    }
}
