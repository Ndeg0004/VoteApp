package com.example.voteapp.model;

public class Vote {

    public String key;
    public String pollId;
    public String userId;
public int option;
    public long timestamp;

    public Vote() {}

    public Vote(String key, String pollId, String userId, int option, long timestamp) {
        this.key = key;
        this.pollId = pollId;
        this.userId = userId;
        this.option = option;
        this.timestamp = timestamp;
    }
}
