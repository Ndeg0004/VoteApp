package com.example.voteapp.firebase.realtimeDatabase;

import com.example.voteapp.model.Poll;
import com.example.voteapp.model.Vote;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VoteManager {

    private static final String VOTES_REFERENCE = "Votes";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public void addVote(String pollId, String userId, int option) {
        DatabaseReference ref = database.getReference(VOTES_REFERENCE);

        String key = ref.push().getKey();
        if (key == null) key = "";

        Vote vote = createVote(key, pollId, userId, option);

        ref.child(key).setValue(vote);
    }

    private Vote createVote(String key, String pollId, String userId, int option) {
        long timestamp = getCurrentTime();
        return new Vote(key, pollId, userId, option, timestamp);
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
