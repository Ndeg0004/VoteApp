package com.example.voteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class AddQuestionActivity extends AppCompatActivity {
    private EditText editTextQuestion, editTextOption1, editTextOption2;

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
        String option1 = editTextOption1.getText().toString().trim();
        String option2 = editTextOption2.getText().toString().trim();

        if (question.isEmpty() || option1.isEmpty() || option2.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // adding questions to API
        HashMap<String, String> postData = new HashMap<>();
        postData.put("question", question);
        postData.put("option1", option1);
        postData.put("option2", option2);

        new ApiClient().post("/api/polls", postData, response -> {
            Toast.makeText(this, "Question added successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK); // Refreshing the main page
            finish();
        }, error -> {
            Toast.makeText(this, "Error adding question", Toast.LENGTH_SHORT).show();
        });
    }
}
