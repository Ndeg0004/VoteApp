package com.example.voteapp.firebase.authenticationManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

public class AuthenticationManager {
    private final FirebaseAuth firebaseAuth;

    // Constructor
    public AuthenticationManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    // Start the sign-in flow


    // Check if the user is signed in
    public boolean isUserSignedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    // Get the current user
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }


}