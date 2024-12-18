package com.example.voteapp.model;

import java.util.UUID;

public class Poll {
    public String key;
    public String question;
    public String description;
    private String pollId;

    public long timestamp;

    public Poll() {}

    public Poll(String key, String question, String description, long timestamp) {
        this.key = key;
        this.question = question;
        this.description = description;
        this.timestamp = timestamp;
        this.pollId = pollId;
    }


}
