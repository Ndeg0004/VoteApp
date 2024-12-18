package com.example.voteapp.firebase.realtimeDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.voteapp.model.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PollsManager {
    private static final String POLLS_REFERENCE = "Polls";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ValueEventListener pollsValueEventListener;
    private ValueEventListener pollValueEventListener;
    private final MutableLiveData<List<Poll>> pollsValues = new MutableLiveData<>();
    public final MutableLiveData<Poll> pollValue = new MutableLiveData<>();



    // READS DATA FROM THE POLLS TABLE
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

    // ADDS POLLS TO THE POLLS TABLE
    public void addPoll(String question, String description) { //, Runnable onSuccessAction, Runnable onFailureAction
        DatabaseReference pollsReference = database.getReference(POLLS_REFERENCE);

        String key = pollsReference.push().getKey();
        if (key == null) key = "";

        Poll poll = createPoll(key, question, description);

        pollsReference.child(key).setValue(poll);
    }



    public interface DataStatus {
        void DataIsDeleted();
    }

    private Poll createPoll(String key, String question, String description) {
        long timestamp = getCurrentTime();
        return new Poll(key, question, description, timestamp);
    }
    public LiveData<List<Poll>> onPollsValuesChange() {
        listenForPollValueChanges();
        return pollsValues;
    }

    public void updatePollContent(String key, String content) {
        database.getReference(POLLS_REFERENCE)
                .child(key)
                .setValue(content);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
