package com.example.voteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_QUESTION_REQUEST_CODE = 1;
    private ArrayList<Question> questionList;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewQuestions = findViewById(R.id.recyclerViewQuestions);
        questionList = new ArrayList<>();
        adapter = new QuestionAdapter(this, questionList);

        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestions.setAdapter(adapter);

        findViewById(R.id.fabAddQuestion).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddQuestionActivity.class);
            startActivityForResult(intent, ADD_QUESTION_REQUEST_CODE);
        });

        loadQuestions();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Yeni bir soru eklendiğinde listeyi yenile
            loadQuestions();
        }
    }

    private void loadQuestions() {
        // API'den tüm soruları al
        new ApiClient().get("/api/polls", response -> {
            try {
                questionList.clear();
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Question question = new Question(
                            jsonObject.getInt("id"),
                            jsonObject.getString("question"),
                            jsonObject.getString("option1"),
                            jsonObject.getString("option2")
                    );
                    questionList.add(question);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show();
        });
    }
}
