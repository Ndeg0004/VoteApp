package com.example.voteapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.voteapp.firebase.realtimeDatabase.PollsManager;
import com.example.voteapp.firebase.realtimeDatabase.VoteManager;
import com.example.voteapp.model.Poll;

import org.json.JSONObject;

import java.util.HashMap;

import com.example.voteapp.databinding.ActivityVoteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VoteActivity extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("Polls");
    VoteManager voteManager = new VoteManager();
    private String questionId;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        mAuth = FirebaseAuth.getInstance();

        TextView textViewQuestion = findViewById(R.id.textViewQuestion);

        Button buttonVoteOption1 = findViewById(R.id.buttonVoteOption1);
        Button buttonVoteOption2 = findViewById(R.id.buttonVoteOption2);

        questionId = getIntent().getStringExtra("poll");
        DatabaseReference ref = database.child(questionId);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Poll poll = dataSnapshot.getValue(Poll.class);

                    if  (poll != null) {
                        textViewQuestion.setText(poll.question);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        });

        buttonVoteOption1.setOnClickListener(v -> submitVote(1));
        buttonVoteOption2.setOnClickListener(v -> submitVote(2));
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> onBackPressed());

    }

    private void submitVote(int option) {
        voteManager.addVote(questionId, mAuth.getCurrentUser().getUid(), option);

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
