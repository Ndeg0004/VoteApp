package com.example.voteapp;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class VoteActivity extends AppCompatActivity {
    private int questionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        TextView textViewQuestion = findViewById(R.id.textViewQuestion);
        Button buttonVoteOption1 = findViewById(R.id.buttonVoteOption1);
        Button buttonVoteOption2 = findViewById(R.id.buttonVoteOption2);

        questionId = getIntent().getIntExtra("id", -1);

        // uploading questions and options from API
        new ApiClient().get("/api/polls/" + questionId, response -> {
            try {
                JSONObject questionData = new JSONObject(response);
                textViewQuestion.setText(questionData.getString("question"));
                buttonVoteOption1.setText(questionData.getString("option1"));
                buttonVoteOption2.setText(questionData.getString("option2"));
            } catch (Exception e) {
                Toast.makeText(this, "Error loading question", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(this, "Error loading question", Toast.LENGTH_SHORT).show();
        });

        buttonVoteOption1.setOnClickListener(v -> submitVote(1));
        buttonVoteOption2.setOnClickListener(v -> submitVote(2));
    }

    private void submitVote(int option) {
        // sending poll
        new ApiClient().post("/api/vote", new HashMap<String, String>() {{
            put("question_id", String.valueOf(questionId));
            put("option", String.valueOf(option));
        }}, response -> {
            Toast.makeText(this, "Vote submitted", Toast.LENGTH_SHORT).show();
            finish();
        }, error -> {
            Toast.makeText(this, "Error submitting vote", Toast.LENGTH_SHORT).show();
        });
    }
}
