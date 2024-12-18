package com.example.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voteapp.firebase.realtimeDatabase.PollsManager;

import java.util.HashMap;

public class AddQuestionActivity extends AppCompatActivity {
    private EditText editTextQuestion, editTextOption1, editTextOption2;
    private final PollsManager pollsManager = new PollsManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        editTextQuestion = findViewById(R.id.editTextQuestion);
        editTextOption1 = findViewById(R.id.editTextOption1);
        editTextOption2 = findViewById(R.id.editTextOption2);

        findViewById(R.id.buttonSave).setOnClickListener(this::saveQuestion);
    }

    public void saveQuestion(View view) {
        String question = editTextQuestion.getText().toString().trim();


        if (question.isEmpty() ) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        pollsManager.addPoll(question, question);

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);

    }
}
