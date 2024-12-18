package com.example.voteapp.firebase.realtimeDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.voteapp.firebase.authenticationManager.AuthenticationManager;

import com.example.voteapp.model.Poll;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RealtimeDatabaseManager {

    private static final String POLLS_REFERENCE = "Polls";

    private final AuthenticationManager authenticationManager = new AuthenticationManager();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;


    private final MutableLiveData<List<Poll>> pollsValues = new MutableLiveData<>();

    private ValueEventListener pollsValueEventListener;

    public void addPoll(String question, String description) { //, Runnable onSuccessAction, Runnable onFailureAction
        DatabaseReference pollsReference = database.getReference(POLLS_REFERENCE);

        String key = pollsReference.push().getKey();
        if (key == null) key = "";

        Poll post = createPoll(key, question, description);

        pollsReference.child(key).setValue(post);
                /*.addOnSuccessListener(task -> onSuccessAction.run())
                .addOnFailureListener(e -> onFailureAction.run());*/
    }

    private Poll createPoll(String key, String question, String description) {
        long timestamp = getCurrentTime();
        return new Poll(key, question, description, timestamp);
    }
    public LiveData<List<Poll>> onPollsValuesChange() {
        listenForPollValueChanges();
        return pollsValues;
    }

    public void removePollsValuesChangesListener() {
        database.getReference(POLLS_REFERENCE).removeEventListener(pollsValueEventListener);
    }


    public void updatePollContent(String key, String content) {
        database.getReference(POLLS_REFERENCE)
                .child(key)
                .setValue(content);
    }

    public void deletePoll(String key) {
        database.getReference(POLLS_REFERENCE)
                .child(key)
                .removeValue();
    }


    private void listenForPollValueChanges() {
       // mAuth = FirebaseAuth.getInstance();
        //String userId = mAuth.getCurrentUser().getUid();

        pollsValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Poll> polls = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Poll poll = snapshot.getValue(Poll.class);
                        if (poll != null) polls.add(poll);
                    }
                }
                pollsValues.postValue(polls);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
            }
        };

        database.getReference(POLLS_REFERENCE).addValueEventListener(pollsValueEventListener);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }


}
