package com.example.converse.model;

public class MessageResponse {
    private String reply;
    
    public MessageResponse(String reply) {
        this.reply = reply;
    }

    // Getter／Setter
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
