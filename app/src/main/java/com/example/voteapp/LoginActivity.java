package com.example.voteapp;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        EditText usernameInput = findViewById(R.id.username_input);
        EditText passwordInput = findViewById(R.id.password_input);
        Button loginBtn = findViewById(R.id.login_btn);
        TextView forgotPass = findViewById(R.id.forgotpass_btn);

        loginBtn.setOnClickListener(
                v -> {
                    String username = usernameInput.getText().toString();
                    String password = passwordInput.getText().toString();

                    signIn(username, password);
                }
        );
    }

    private void signIn(String email, String password) {
        try {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, " Auth failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateUI(FirebaseUser user) {
        if (user != null) {
            user.getUid();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

}
