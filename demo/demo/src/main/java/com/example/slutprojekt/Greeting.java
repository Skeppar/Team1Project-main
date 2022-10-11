package com.example.slutprojekt;

public class Greeting {

    private String content;
    private String sender;

    public  String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Greeting() {

    }

    public Greeting(String content,String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }


}
